package messages.outgoing

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import messages.incoming.ErrorMessage
import messages.incoming.HelperMessage
import messages.incoming.IN_NAME
import messages.incoming.NameMessage

@Serializable
abstract class OutgoingMessage(val type: String) {
    abstract val payload: Any
}