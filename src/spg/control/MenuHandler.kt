package spg.control

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.MenuItem
import spg.model.GameStorage
import spg.view.Application
import spg.view.dialog.LoadGameDialog
import spg.view.dialog.NewGameDialog
import spg.view.panes.VersionAlert
import kotlin.system.exitProcess

class MenuHandler : EventHandler<ActionEvent> {
	override fun handle(event: ActionEvent) {

		var userdata = ""
		if (event.source is MenuItem) {
			userdata = ( event.source as MenuItem ).userData as String
		}

		if (event.source is Button) {
			userdata = ( event.source as Button ).userData as String
		}

		when ( userdata ) {
			"menu-exit" -> {
				exitProcess(0)
			}

			"menu-version" -> {
				VersionAlert().showAndWait()
			}

			"menu-save" -> {
				GameStorage.INSTANCE.save()
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

			"menu-restart" -> {
				Application.reinitialize()
			}
		}
	}
}