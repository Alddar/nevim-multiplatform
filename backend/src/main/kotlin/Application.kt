import action.GameStarted
import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.util.*
import io.ktor.websocket.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import model.GameState
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.reduxkotlin.createThreadSafeStore
import reducer.gameStateReducer

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val store = createThreadSafeStore(gameStateReducer, GameState())

@Serializable
data class PlayerSession(
        val id: String
)

@KtorExperimentalAPI
@ExperimentalCoroutinesApi
fun Application.module() {
    install(WebSockets)
    install(Sessions) {
        cookie<PlayerSession>("SESSION")
    }

    intercept(ApplicationCallPipeline.Features) {
        if (call.sessions.get<PlayerSession>() == null) {
            call.sessions.set(PlayerSession(generateNonce()))
        }
    }

    routing {
        webSocket("/") {
            val session = this
            store.subscribe {
                GlobalScope.launch {
                    session.send(Frame.Text(Json.encodeToString(store.state)))
                }
            }

            try {
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val text = frame.readText()
                        if(text == "start") {
                            store.dispatch(GameStarted())
                        }
                    }
                }
            } finally {
                // left
            }
        }
    }
}
