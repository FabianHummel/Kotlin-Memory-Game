package spg.view

import javafx.animation.FadeTransition
import javafx.animation.Interpolator
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.text.Font
import javafx.util.Duration
import spg.control.MenuHandler
import spg.model.GameStorage

class GameEndView : BorderPane() {
	init {
		this.opacity = 0.0
		FadeTransition().apply {
			this.fromValue = 0.0
			this.toValue = 1.0
			this.duration = Duration.seconds(1.0)
			this.node = this@GameEndView
			this.interpolator = Interpolator.EASE_BOTH
		}.play()

		this.center = Label(
			"Well done ${GameStorage.INSTANCE.player.name}!"
		).apply {
			this.font = Font.font(20.0)
		}

		this.bottom = HBox(
			Button("Save Game").apply {
				this.userData = "menu-save"
				this.onAction = MenuHandler()
			},

			Button("Restart").apply {
				this.userData = "menu-restart"
				this.onAction = MenuHandler()
			},

			Button("New Game").apply {
				this.userData = "menu-new"
				this.onAction = MenuHandler()
			},

			Button("Load Game").apply {
				this.userData = "menu-load"
				this.onAction = MenuHandler()
			}
		).apply {
			this.alignment = Pos.CENTER
			this.spacing = 20.0
			this.padding = Insets(10.0)
		}
	}
}
