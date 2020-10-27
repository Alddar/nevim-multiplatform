package middleware

import action.CreateLobby
import action.JoinLobby
import action.NewPlayer
import dto.IdDTO
import dto.LobbyDTO
import dto.LobbyListDTO
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import messages.outgoing.IDMessage
import messages.outgoing.LobbyIdMessage
import messages.outgoing.UpdateLobbyListMessage
import messages.outgoing.UpdateLobbyMessage
import model.Lobby
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

val sendLobbyId = middleware<Server> { _, next, action ->
    val result = next(action)
    if (action is CreateLobby) {
        GlobalScope.launch {
            action.lobby.players.first().session?.send(
                Frame.Text(
                    Json.encodeToString(
                        LobbyIdMessage(IdDTO(action.lobby.id))
                    )
                )
            )
        }
    }
    result
}

val updateLobbyList = middleware<Server> { store, next, action ->
    val result = next(action)
    if (action is CreateLobby || action is NewPlayer || action is JoinLobby) {
        GlobalScope.launch {
            store.getState().players.forEach { (_, player) ->
                player.session?.send(
                    Frame.Text(
                        Json.encodeToString(
                            UpdateLobbyListMessage(LobbyListDTO(store.getState().lobbies.map{
                                val lobby = it.value
                                LobbyListDTO.Lobby(lobby.id, lobby.name, lobby.maxPlayers, lobby.players.size)
                            }.toTypedArray()))
                        )
                    )
                )
            }
        }
    }
    result
}

fun updateLobby(store: Store<Server>, lobby: Lobby) {
    GlobalScope.launch {
        store.getState().lobbies[lobby.id]?.apply {
            val lobbyDTO = LobbyDTO(
                LobbyDTO.Lobby(
                    this.id,
                    this.name,
                    this.maxPlayers,
                    this.players.map {
                        LobbyDTO.Lobby.Player(it.id, it.name ?: "", it.ready)
                    }.toTypedArray()
                ))
            players.forEach{
                it.session?.send(Frame.Text(Json.encodeToString(
                    UpdateLobbyMessage(lobbyDTO)
                )))
            }
        }
    }
}

val updateLobby = middleware<Server> { store, next, action ->
    val result = next(action)
    when(action) {
        is CreateLobby -> updateLobby(store, action.lobby)
        is JoinLobby -> updateLobby(store, action.lobby)
    }
    result
}