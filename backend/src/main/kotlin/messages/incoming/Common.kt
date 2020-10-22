package messages.incoming

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
class HelperMessage(public val type: String, public val payload: JsonObject) {}

const val TYPE_ERROR = "ERROR"

@Serializable
class ErrorMessage() : IncomingMessage(TYPE_ERROR) {
    override val payload: String = TYPE_ERROR
}

@Serializable
abstract class IncomingMessage(val type: String) {
    abstract val payload: Any

    companion object {
        fun createFromString(message: String): IncomingMessage {
            val typed = Json.decodeFromString<HelperMessage>(message)

            return when(typed.type) {
                IN_NAME -> Json.decodeFromString<NameMessage>(message)
                else -> ErrorMessage()
            }
        }
    }
}