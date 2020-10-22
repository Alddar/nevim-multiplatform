package action

import model.Lobby
import model.Player

data class NewPlayer(val player: Player)
data class CreateLobby(val lobby: Lobby)