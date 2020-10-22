package messages.incoming

import dto.CreateLobbyDTO
import dto.NameDTO
import kotlinx.serialization.Serializable

const val IN_NAME = "NAME_MESSAGE"

@Serializable
class NameMessage(override val payload: NameDTO) : IncomingMessage(IN_NAME)

const val IN_CREATE_LOBBY = "CREATE_LOBBY"

@Serializable
class CreateLobbyMessage(override val payload: CreateLobbyDTO): IncomingMessage(IN_CREATE_LOBBY)
