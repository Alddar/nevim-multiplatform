package reducer

import action.CreateLobby
import action.PlayerJoin
import model.Lobby
import model.Player
import model.Server
import org.reduxkotlin.Reducer

val playerReducer: Reducer<List<Player>> = { state, action ->
    when(action) {
        is PlayerJoin -> state + action.player
        else -> state
    }
}

val lobbiesReducer: Reducer<List<Lobby>> =  { state, action ->
    when (action) {
        is CreateLobby -> state + action.lobby
        else -> state
    }
}

val serverReducer: Reducer<Server> = { state, action ->
    Server(
        lobbies = lobbiesReducer(state.lobbies, action),
    )
}

