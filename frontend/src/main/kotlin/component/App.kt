package component

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h1
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        browserRouter {
            switch {
                route("/", NameComponent::class, exact = true)
                route("/lobby", LobbyComponent::class)
            }
        }
    }
}

