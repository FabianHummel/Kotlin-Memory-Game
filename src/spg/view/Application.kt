package spg.view

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import spg.model.GameStorage
import spg.model.PlayerProfile
import spg.view.dialog.LauncherDialog
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import kotlin.system.exitProcess

class Application : Application() {
	companion object {
		lateinit var STAGE : Stage
	}

	override fun start(primaryStage: Stage) {
		STAGE = primaryStage

		GameStorage()
		LauncherDialog().apply {
			this.title = "Game Launcher"
			this.showAndWait()
		}

		primaryStage.apply {
			this.scene = Scene(
				GameView(), 800.0, 600.0
			)
			this.show()
			this.onCloseRequest = EventHandler {
				println("Closing game...")
				exitProcess(0)
			}
		}
	}
}