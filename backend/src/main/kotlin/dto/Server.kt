package dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class NameDTO(
    val name: String
)

@Serializable
class IdDTO(
    val id: String
)

@Serializable
class LobbyDTO(
    val lobby: Lobby
) {
    @Serializable
    class Lobby(
        val id: String,
        val name: String,
        val maxPlayers: Int,
        val players: Array<Player>
    ) {
        @Serializable
        class Player(
            val id: String,
            val name: String,
            val ready: Boolean
        )
    }
}

@Serializable
class CreateLobbyDTO(
    val name: String,
    val maxPlayers: Int,
)

@Serializable
class LobbyListDTO(
    val lobbies: Array<Lobby>
) {
    @Serializable
    class Lobby(
        val id: String,
        val name: String,
        val maxPlayers: Int,
        val players: Int
    )
}