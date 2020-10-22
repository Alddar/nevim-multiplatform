package middleware

import action.NewPlayer
import dto.IdDTO
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import messages.outgoing.IDMessage
import model.Server
import org.reduxkotlin.middleware

val sendId = middleware<Server> { store, next, action ->
    val result = next(action)
    if (action is NewPlayer) {
        GlobalScope.launch {
            action.player.session?.send(
                Frame.Text(
                    Json.encodeToString(
                        IDMessage(IdDTO(action.player.id))
                    )
                )
            )
        }
    }
    result
}