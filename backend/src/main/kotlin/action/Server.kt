package action

import model.Lobby
import model.Player

data class PlayerJoin(val player: Player)

data class CreateLobby(val lobby: Lobby)
