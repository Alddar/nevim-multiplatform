package middleware

import action.*
import dto.IdDTO
import dto.LobbyDTO
import dto.LobbyListDTO
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import messages.outgoing.IDMessage
import messages.outgoing.UpdateLobbyListMessage
import messages.outgoing.UpdateLobbyMessage
import model.Server
import org.reduxkotlin.Store
import org.reduxkotlin.middleware

val sendId = middleware<Server> { _, next, action ->
    val result = next(action)
    if (action is NewPlayer) {
        GlobalScope.launch {
            action.player.session?.send(
                Frame.Text(
                    Json.encodeToString(
                        IDMessage(IdDTO(action.player.id))
                    )
                )
            )
        }
    }
    result
}

val updateLobbyList = middleware<Server> { store, next, action ->
    val result = next(action)
    when (action) {
        is CreateLobby, is NewPlayer, is JoinLobby, is UpdateLobby, is DeleteLobby -> {
            val lobbies = store.state.lobbies
            store.state.players.forEach { (_, player) ->
                GlobalScope.launch {
                    player.session?.send(
                        Frame.Text(
                            Json.encodeToString(
                                UpdateLobbyListMessage(LobbyListDTO(lobbies.map {
                                    val lobby = it.value
                                    LobbyListDTO.Lobby(lobby.id, lobby.name, lobby.maxPlayers, lobby.players.size)
                                }.toTypedArray()))
                            )
                        )
                    )
                }
            }
        }
    }
    result
}

fun updateLobby(store: Store<Server>, lobbyId: String) {
    store.state.lobbies[lobbyId]?.apply {
        val lobbyDTO = LobbyDTO(
            LobbyDTO.Lobby(
                this.id,
                this.name,
                this.maxPlayers,
                this.players.map {
                    val player = store.state.players[it] ?: error("Player from lobby not found")
                    LobbyDTO.Lobby.Player(player.id, player.name ?: "", player.ready)
                }.toTypedArray()
            )
        )
        players.forEach {
            GlobalScope.launch {
                store.state.players[it]?.session?.send(
                    Frame.Text(
                        Json.encodeToString(
                            UpdateLobbyMessage(lobbyDTO)
                        )
                    )
                )
            }
        }
    }
}

val updateLobby = middleware<Server> { store, next, action ->
    val result = next(action)
    when (action) {
        is CreateLobby -> updateLobby(store, action.lobby.id)
        is JoinLobby -> updateLobby(store, action.lobby.id)
        is UpdateLobby -> updateLobby(store, action.lobby.id)
    }
    result
}

val leaveLobby = middleware<Server> { store, next, action ->
    val result = next(action)
    when (action) {
        is LeaveLobby -> {
            val lobby = store.state.lobbies[action.lobby.id] ?: error("Lobby not found")

            if (lobby.players.isEmpty()) {
                store.dispatch(DeleteLobby(lobby))
            } else {
                store.dispatch(UpdateLobby(lobby))
            }
        }
    }
    result
}

val ready = middleware<Server> { store, next, action ->
    val result = next(action)
    when(action) {
        is Ready -> {
            updateLobby(store, action.lobby.id)
        }
    }
    result
}