package messages.incoming

import dto.CreateLobbyDTO
import dto.IdDTO
import dto.NameDTO
import kotlinx.serialization.Serializable

const val IN_NAME = "NAME_MESSAGE"

@Serializable
class NameMessage(override val payload: NameDTO) : IncomingMessage(IN_NAME)

const val IN_CREATE_LOBBY = "CREATE_LOBBY"

@Serializable
class CreateLobbyMessage(override val payload: CreateLobbyDTO): IncomingMessage(IN_CREATE_LOBBY)

const val IN_JOIN_LOBBY = "JOIN_LOBBY"

@Serializable
class JoinLobbyMessage(override val payload: IdDTO): IncomingMessage(IN_JOIN_LOBBY)
