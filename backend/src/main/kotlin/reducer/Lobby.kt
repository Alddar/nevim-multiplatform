package reducer

import action.CreateLobby
import action.JoinLobby
import action.NewPlayer
import model.Lobby
import model.Player
import model.Server
import org.reduxkotlin.Reducer

val playersReducer: Reducer<Map<String, Player>> = { state, action ->
    when(action) {
        is NewPlayer -> state + (action.player.id to action.player)
        else -> state
    }
}

val lobbiesReducer: Reducer<Map<String, Lobby>> =  { state, action ->
    when (action) {
        is CreateLobby -> state + (action.lobby.id to action.lobby)
        is JoinLobby -> state.mapValues {(id, lobby) ->
            if(id == action.lobby.id && lobby.maxPlayers > lobby.players.size) lobby.copy(players = lobby.players + action.player)
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

