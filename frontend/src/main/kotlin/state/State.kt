package state

import org.reduxkotlin.createThreadSafeStore
import reducer.rootReducer

data class State(
    val name: String = ""
)


object AppStore {
    val store = createThreadSafeStore(rootReducer, State())
}
