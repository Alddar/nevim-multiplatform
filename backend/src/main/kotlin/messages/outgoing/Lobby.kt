package messages.outgoing

import dto.IdDTO
import dto.LobbyDTO
import dto.LobbyListDTO
import kotlinx.serialization.Serializable

const val OUT_ID = "ID_MESSAGE"

@Serializable
class IDMessage(override val payload: IdDTO): OutgoingMessage(OUT_ID)

const val OUT_UPDATE_LOBBY_LIST = "UPDATE_LOBBY_LIST"

@Serializable
class UpdateLobbyListMessage(override val payload: LobbyListDTO): OutgoingMessage(OUT_UPDATE_LOBBY_LIST)

const val OUT_UPDATE_LOBBY = "UPDATE_LOBBY"

@Serializable
class UpdateLobbyMessage(override val payload: LobbyDTO): OutgoingMessage(OUT_UPDATE_LOBBY)