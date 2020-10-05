package component

import react.*
import react.dom.div
import state.AppStore
import state.State

interface LobbyState: RState {
    var state: State
}

class LobbyComponent : RComponent<RProps, LobbyState>() {
    override fun LobbyState.init() {
        state = AppStore.store.state
    }

    override fun RBuilder.render() {
        div {
            +"Name: ${state.state.name}"
        }
    }
}


fun RBuilder.lobby(handler: RProps.() -> Unit): ReactElement {
    return child(LobbyComponent::class) {
        this.attrs(handler)
    }
}