package reducer

import action.*
import model.Lobby
import model.Player
import model.Server
import org.reduxkotlin.Reducer

val playersReducer: Reducer<Map<String, Player>> = { state, action ->
    when (action) {
        is NewPlayer -> state + (action.player.id to action.player)
        is JoinLobby -> state.mapValues { (id, player) ->
            if (id == action.player.id) player.copy(lobby = action.lobby)
            else player
        }
        is CreateLobby -> state.mapValues { (id, player) ->
            if (id == action.lobby.players.first().id) player.copy(lobby = action.lobby)
            else player
        }
        is LeaveLobby -> state.mapValues { (id, player) ->
            if (id == action.player.id) player.copy(lobby = null)
            else player
        }
        else -> state
    }
}

val lobbiesReducer: Reducer<Map<String, Lobby>> = { state, action ->
    when (action) {
        is CreateLobby -> state + (action.lobby.id to action.lobby)
        is DeleteLobby -> state - action.lobby.id
        is JoinLobby -> state.mapValues { (id, lobby) ->
            if (id == action.lobby.id && lobby.maxPlayers > lobby.players.size) lobby.copy(players = lobby.players + action.player)
            else lobby
        }
        is LeaveLobby -> state.mapValues { (id, lobby) ->
            if (id == action.lobby.id) lobby.copy(players = lobby.players.filter {it.id != action.player.id})
            else lobby
        }
        else -> state
    }
}

val serverReducer: Reducer<Server> = { state, action ->
    Server(
        lobbies = lobbiesReducer(state.lobbies, action),
        players = playersReducer(state.players, action)
    )
}

