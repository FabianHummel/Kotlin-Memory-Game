package spg.control

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.MenuItem
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.Modality
import spg.model.GameStorage
import spg.view.Application
import spg.view.GameView
import spg.view.dialog.LauncherDialog
import spg.view.dialog.LoadGameDialog
import spg.view.dialog.NewGameDialog
import spg.view.panes.VersionAlert
import kotlin.system.exitProcess

class MenuHandler : EventHandler<ActionEvent> {
	override fun handle(event: ActionEvent) {
		when ( (event.source as MenuItem).userData ) {
			"menu-exit" -> {
				exitProcess(0)
			}

			"menu-version" -> {
				VersionAlert().showAndWait()
			}

			"menu-new" -> {
				NewGameDialog().apply {
					this.title = "New Game"
					this.showAndWait()
				}
			}

			"menu-load" -> {
				LoadGameDialog().apply {
					this.title = "Load Game"
					this.showAndWait()
				}
			}

			"menu-save" -> {
				GameStorage.INSTANCE.save()
			}
		}
	}
}