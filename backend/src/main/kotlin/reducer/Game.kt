package reducer

import action.GameStarted
import model.GameState
import org.reduxkotlin.Reducer

val startedReducer: Reducer<Boolean> =  { state, action ->
    when (action) {
        is GameStarted -> true
        else -> state
    }
}

//fun gameStateReducer(state: GameState, action: Any) =

val gameStateReducer: Reducer<GameState> = { state, action ->
    GameState(
        started = startedReducer(state.started, action),
        players = playerReducer(state.players, action)
    )
}

