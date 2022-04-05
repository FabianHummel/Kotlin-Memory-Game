package spg.view

import javafx.geometry.Pos
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import spg.control.MemoryHandler
import spg.control.MenuHandler

class GameView : BorderPane() {
	companion object {
		lateinit var INSTANCE : GameView
	}

	init {
		INSTANCE = this
		setPrefSize(800.0, 600.0)

		this.top = MenuBar(
			Menu("File").apply {
				this.items.addAll(
					MenuItem("Exit").apply {
						userData = "menu-exit"
						onAction = MenuHandler()
					},
					MenuItem("Version").apply {
						userData = "menu-version"
						onAction = MenuHandler()
					}
				)
			},

			Menu("Game").apply {
				this.items.addAll(
					MenuItem("New Game").apply {
						userData = "menu-new"
						onAction = MenuHandler()
					},
					MenuItem("Load Game").apply {
						userData = "menu-load"
						onAction = MenuHandler()
					},
					MenuItem("Save Game").apply {
						userData = "menu-save"
						onAction = MenuHandler()
					}
				)
			},
		)

		this.center = GridPane().apply {
			MemoryHandler(
				this
			)
			this.alignment = Pos.CENTER
			this.hgap = 10.0
			this.vgap = 10.0
		}
	}
}