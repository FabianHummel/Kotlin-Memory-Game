package spg.control

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import spg.view.MemoryCard

class MemoryCardHandler(private val memory : MemoryHandler) : EventHandler<MouseEvent> {
	override fun handle(event: MouseEvent) {
		val card = event.source as MemoryCard
		when ( card.userData ) {
			"card" -> {
				memory.reveal(
					card
				)
			}
		}
	}
}