package reducer

import action.PlayerJoin
import model.Player

fun playerReducer(state: List<Player>, action: Any) = when(action) {
    is PlayerJoin -> state + action.player
    else -> state
}