import action.PlayerJoin
import dto.IdDTO
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
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import messages.incoming.IncomingMessage
import model.GameState
import model.Player
import model.Server
import org.reduxkotlin.createThreadSafeStore
import reducer.gameStateReducer
import reducer.serverReducer
import messages.incoming.NameMessage
import messages.outgoing.IDMessage

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val store = createThreadSafeStore(serverReducer, Server())

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
            val currentSession = call.sessions.get<PlayerSession>()

            if (currentSession == null) {
                close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No session"))
                return@webSocket
            }

            val session = this
//            store.subscribe {
//                GlobalScope.launch {
//                    session.send(Frame.Text(Json.encodeToString(store.state)))
//                }
//            }

            try {
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val text = frame.readText()
                        println(text)
                        val message = IncomingMessage.createFromString(text)

                        when(message) {
                            is NameMessage -> {
                                session.send(Frame.Text(Json.encodeToString(IDMessage(IdDTO(currentSession.id)))))
                            }
                        }
                    }
                }
            } finally {
                // left
            }
        }
    }
}
