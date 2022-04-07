package spg.control

import javafx.animation.PauseTransition
import javafx.event.EventHandler
import javafx.scene.layout.GridPane
import javafx.util.Duration
import spg.model.CardData
import spg.model.GameStorage
import spg.view.MemoryCard

class MemoryHandler(
	private val grid : GridPane
) {
	private var revealed: Int = 0
	private val flipped = arrayOfNulls<MemoryCard>(2)

	init {
		init()
	}

	private fun clearFlipped() {
		flipped[0] = null
		flipped[1] = null
	}

	private fun unflipAll() {
		flipped.forEach {
			if (it != null && it.isFlipped() ) {
				it.flip()
			}
		}
	}

	private fun consumeAll() {
		flipped.forEach {
			if (it != null && it.isFlipped() ) {
				it.consume()
			}
		}
	}

	fun init() {
		var iterator = 0
		val storage = GameStorage.INSTANCE
		val board = storage.boardData


		iterator = 0
		var identifier = 0
		for (y in 0 until storage.player.boardY) {
			for (x in 0 until storage.player.boardX) {
				if (iterator++ % 2 == 0)
					identifier++

				board.addCard(
					CardData(
						identifier, x, y
					)
				)
			}
		}

		board.shuffle()

		iterator = 0
		for (y in 0 until storage.player.boardY) {
			for (x in 0 until storage.player.boardX) {
				board.getCard(iterator++).apply {
					this.posX = x
					this.posY = y
					grid.add(
						MemoryCard(
							this@MemoryHandler, this
						), x, y
					)
				}
			}
		}
	}

	fun contains(card: MemoryCard): Boolean {
		var contains = false
		flipped.forEach {
			if (it === card) {
				contains = true
			}
		}
		return contains
	}

	var locked : Boolean = false
	fun reveal(card : MemoryCard) {
		if (revealed < 2) {
			if (!contains(card)) {
				flipped[revealed++] = card
				card.flip()
			}
		}

		if (revealed >= 2 && !locked) {
			PauseTransition().apply {
				locked = true
				this.duration = Duration.seconds(1.0)
				this.onFinished = EventHandler {
					if (flipped[0]!!.data.identifier == flipped[1]!!.data.identifier) {
						consumeAll()
					} else {
						unflipAll()
					}

					revealed = 0
					locked = false
					clearFlipped()
				}
			}.play()
		}
	}
}