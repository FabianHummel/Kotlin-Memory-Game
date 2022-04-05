package spg.control

import javafx.animation.Animation
import javafx.animation.PauseTransition
import javafx.event.EventHandler
import javafx.scene.image.Image
import javafx.scene.layout.GridPane
import javafx.util.Duration
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

	fun init() {
		grid.children.clear()
		for (y in 0 until 8) {
			for (x in 0 until 8) {
				grid.add(
					MemoryCard(this, Image(
						"bilder/1.jpg"
					), x, y ), x, y
				)
			}
		}
	}

	fun contains(card: MemoryCard): Boolean {
		var contains = false
		flipped.forEach {
			if (it != null && it.posX == card.posX && it.posY == card.posY) {
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
					revealed = 0
					locked = false
					unflipAll()
					clearFlipped()
				}
			}.play()
		}
	}
}