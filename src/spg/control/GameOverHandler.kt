package spg.control

import javafx.animation.PauseTransition
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.util.Duration
import spg.view.Application

class GameOverHandler : EventHandler<ActionEvent> {
	override fun handle(event : ActionEvent?) {
		PauseTransition(Duration.seconds(1.0)).apply {
			this.onFinished = EventHandler {
				Application.gameOver()
			}
		}.play()
	}
}