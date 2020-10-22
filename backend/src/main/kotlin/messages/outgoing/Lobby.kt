package messages.outgoing

import dto.IdDTO
import dto.NameDTO
import kotlinx.serialization.Serializable

const val OUT_ID = "ID_MESSAGE"

@Serializable
class IDMessage(override val payload: IdDTO): OutgoingMessage(OUT_ID)