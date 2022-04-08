package spg.view

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.ButtonType
import javafx.stage.Stage
import spg.model.GameStorage
import spg.view.dialog.LauncherDialog
import kotlin.system.exitProcess

class Application : Application() {
	companion object {
		lateinit var STAGE : Stage

		fun reinitialize() {
			GameStorage.reinitialize()
			STAGE.scene = Scene(
				GameView(), 800.0, 600.0
			)
		}

		fun gameOver() {
			GameView.INSTANCE.center = GameEndView()
		}
	}

	override fun start(primaryStage: Stage) {
		STAGE = primaryStage

		GameStorage()
		LauncherDialog().apply {
			this.title = "Game Launcher"
			this.showAndWait()
		}

		primaryStage.apply {
			STAGE = this
			this.isResizable = false
			this.show()
			this.onCloseRequest = EventHandler { event ->
				CloseConfirmation().showAndWait().ifPresent {
					if (it == ButtonType.OK) {
						println("Closing game...")
						exitProcess(0)
					} else {
						event.consume()
					}
				}
			}
		}
	}
}