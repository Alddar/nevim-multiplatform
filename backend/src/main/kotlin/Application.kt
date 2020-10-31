import action.CreateLobby
import action.JoinLobby
import action.LeaveLobby
import action.NewPlayer
import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.sessions.*
import io.ktor.util.*
import io.ktor.websocket.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.serialization.*
import messages.incoming.*
import model.Server
import org.reduxkotlin.createThreadSafeStore
import reducer.serverReducer
import middleware.*
import model.Lobby
import model.Player
import org.reduxkotlin.applyMiddleware

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val store = createThreadSafeStore(
    serverReducer, Server(), applyMiddleware(
        sendId,
        updateLobbyList,
        updateLobby,
        leaveLobby,
    )
)

@Serializable
data class PlayerSession(
    val id: String
)

@KtorExperimentalAPI
@ExperimentalCoroutinesApi
fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, module = Application::module).start()
}

@ExperimentalCoroutinesApi
@KtorExperimentalAPI
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
        get("/") {
            call.respond("It WORKS!")
        }
        webSocket("/") {
            val currentSession = call.sessions.get<PlayerSession>()

            if (currentSession == null) {
                close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No session"))
                return@webSocket
            }

            val session = this

            try {
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        val text = frame.readText()
                        val message = IncomingMessage.createFromString(text)

                        println("Incoming $text")
                        when (message) {
                            is NameMessage -> {
                                store.dispatch(
                                    NewPlayer(
                                        Player(currentSession.id, message.payload.name, session)
                                    )
                                )
                            }
                            is CreateLobbyMessage -> {
                                store.dispatch(
                                    CreateLobby(
                                        Lobby(
                                            generateNonce(),
                                            message.payload.name,
                                            message.payload.maxPlayers,
                                            listOf(store.state.players.getValue(currentSession.id))
                                        )
                                    )
                                )
                            }
                            is JoinLobbyMessage -> {
                                store.dispatch(
                                    JoinLobby(
                                        store.getState().players[currentSession.id] ?: error("Player not found"),
                                        store.getState().lobbies[message.payload.id] ?: error("Lobby not found")
                                    )
                                )
                            }
                            is LeaveLobbyMessage -> {
                                store.dispatch(
                                    LeaveLobby(
                                        store.getState().players[currentSession.id] ?: error("Player not found"),
                                        store.getState().players[currentSession.id]?.lobby ?: error("Player not found")
                                    )
                                )
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
