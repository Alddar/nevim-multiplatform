package component

import kotlinx.html.InputType
import kotlinx.html.classes
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div
import react.dom.h1
import react.dom.input
import react.router.dom.routeLink
import reducer.SetName
import state.AppStore

class NameComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("w-full h-full flex items-center justify-center") {
            div("w-full md:w-4/6 lg:1/2 xl:w-1/3 h-48 space-y-3 flex flex-col items-center justify-evenly rounded bg-gray-200 border border-gray-500 px-5") {
                h1 {
                    +"Enter your name!"
                }
                input(InputType.text) {
                    attrs {
                        classes = setOf("w-full", "border", "border-gray-300", "rounded p-2")
                        onChangeFunction = {
                            val target = it.target as HTMLInputElement
                            AppStore.store.dispatch(SetName(target.value))
                        }
                    }
                }
                routeLink("/lobby", false, "w-full") {
                    input(InputType.button) {
                        attrs {
                            classes = setOf("button", "button-red", "w-full")
                            value = "Continue"
                        }
                    }
                }
            }
        }
    }
}


fun RBuilder.name(handler: RProps.() -> Unit): ReactElement {
    return child(NameComponent::class) {
        this.attrs(handler)
    }
}