package reducer

import org.reduxkotlin.Reducer
import state.State

data class SetName(val name: String)

val nameReducer: Reducer<String> = { state, action -> when(action) {
    is SetName -> action.name
    else -> state
}
}

val rootReducer: Reducer<State> = { state, action ->
    State(
        name = nameReducer(state.name, action)
    )
}