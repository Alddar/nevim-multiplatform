package dto

import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@Serializable
class Player(
    val name: String,
    val id: String
)

@Serializable
class Lobby(
    val name: String,
    val id: String,
    val players: Array<Player>
)

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
)

@Serializable
class CreateLobbyDTO(
    val name: String
)

@Serializable
class LobbiesDTO(
    val lobbies: Array<Lobby>
)