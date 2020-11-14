import action.*
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
        ready,
    )
)

@Serializable
data class PlayerSession(
    val id: String
)

@KtorExperimentalAPI
@ExperimentalCoroutinesApi
fun main() {
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
        get("/ws") {
            call.respond("It WORKS!")
        }
        webSocket("/ws") {
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
                                println("Player joined, ${store.state.players.size} in lobby.")
                            }
                            is CreateLobbyMessage -> {
                                store.dispatch(
                                    CreateLobby(
                                        Lobby(
                                            generateNonce(),
                                            message.payload.name,
                                            message.payload.maxPlayers,
                                            listOf(currentSession.id)
                                        )
                                    )
                                )
                            }
                            is JoinLobbyMessage -> {
                                store.dispatch(
                                    JoinLobby(
                                        store.state.players[currentSession.id] ?: error("Player not found"),
                                        store.state.lobbies[message.payload.id] ?: error("Lobby not found")
                                    )
                                )
                            }
                            is LeaveLobbyMessage -> {
                                val player = store.state.players[currentSession.id] ?: error("Player not found")
                                val lobby = store.state.lobbies[player.lobby ?: error("Player has no lobby")]
                                    ?: error("Lobby not found")

                                store.dispatch(
                                    LeaveLobby(
                                        player,
                                        lobby
                                    )
                                )
                            }
                            is ReadyMessage -> {
                                val player = store.state.players[currentSession.id] ?: error("Player not found")
                                val lobby = store.state.lobbies[player.lobby ?: error("Player has no lobby")]
                                    ?: error("Lobby not found")
                                store.dispatch(
                                    Ready(player, lobby)
                                )
                            }
                        }
                    }
                }
            } finally {
                val player = store.state.players[currentSession.id] ?: error("Player not found")
                val lobby = store.state.lobbies[player.lobby ?: error("Player has no lobby")]
                    ?: error("Lobby not found")
                store.dispatch(
                    LeaveLobby(player, lobby)
                )
                store.dispatch(
                    Disconnect(player)
                )
                println("Player left, ${store.getState().players.size} remaining.")
            }
        }
    }
}
