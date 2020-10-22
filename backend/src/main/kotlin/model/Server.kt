package model

import io.ktor.websocket.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Player(
    val id: String,
    val name: String? = null,
    @Transient
    val session: WebSocketServerSession? = null
)

@Serializable
data class Lobby(
    val id: String,
    val players: List<Player>
)

@Serializable
data class Server (
    val players: Map<String, Player> = mapOf(),
    val lobbies: Map<String, Lobby> = mapOf(),
)