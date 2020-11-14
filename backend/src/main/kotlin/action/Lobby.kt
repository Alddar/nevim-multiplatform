package action

import model.Lobby
import model.Player

data class NewPlayer(val player: Player)
data class Disconnect(val player: Player)
data class CreateLobby(val lobby: Lobby)
data class JoinLobby(val player: Player, val lobby: Lobby)
data class LeaveLobby(val player: Player, val lobby: Lobby)
data class DeleteLobby(val lobby: Lobby)
data class UpdateLobby(val lobby: Lobby)
data class Ready(val player: Player, val lobby: Lobby)