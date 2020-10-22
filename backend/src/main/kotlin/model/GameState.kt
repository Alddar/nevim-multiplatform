package model

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val id: Int,
    val suit: String,
    val value: Int,
    @Required
    var flipped: Boolean = false
)

@Serializable
data class Deck (
    @Required
    val cards: List<Card> = listOf()
)

@Serializable
data class GamePlayer(
    val name: String,
    @Required
    val hand: Array<Card?> = arrayOf(),
    @Required
    val holding: Card? = null
) {

    companion object {
        const val DEFAULT_HAND_SIZE = 4
    }
}

@Serializable
data class GameState (
    @Required
    var started: Boolean = false,

    @Required
    val players: List<GamePlayer> = listOf(),
    @Required
    val drawPile: Deck = Deck(),
    @Required
    val discardPile: Deck = Deck()
)
