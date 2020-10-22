package messages.incoming

import dto.NameDTO
import kotlinx.serialization.Serializable

const val IN_NAME = "NAME_MESSAGE"

@Serializable
class NameMessage(override val payload: NameDTO) : IncomingMessage(IN_NAME)
