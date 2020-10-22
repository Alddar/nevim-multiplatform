package model

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: String,
    val name: String
)

@Serializable
data class Lobby(
    val id: String,
    val players: List<Player>
)

data class Server (
    val lobbies: List<Lobby> = listOf(),
)