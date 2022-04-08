package spg.control

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import spg.model.GameStorage
import spg.model.PlayerProfile
import spg.view.Application
import spg.view.dialog.LauncherDialog
import spg.view.panes.ErrorAlert
import spg.view.panes.LoadGamePane
import spg.view.panes.NewGamePane
import java.util.*

class DialogHandler(private val ctx : Any) : EventHandler<ActionEvent> {
	override fun handle(event: ActionEvent) {
		when ( (event.source as Button).userData ) {
			"button-confirm-new" -> {
				( ctx as NewGamePane ).run {
					val profile : PlayerProfile
					try {
						profile = PlayerProfile(
							ctx.nameField.text,
							0,
							ctx.boardLength.text.toInt(),
							ctx.boardWidth.text.toInt()
						)

						if (profile.boardX % 2 == 1 && profile.boardY % 2 == 1) {
							ErrorAlert("The board size must be even.")
							throw InputMismatchException("The board size must be even.")
						}

						ctx.dialog.dialogPane.scene.window.hide()
					} catch (e : Exception) {
						return
					}

					GameStorage.INSTANCE.apply {
						this.player = profile
						if (ctx.dialog is LauncherDialog) {
							this.boardColor = ctx.dialog.settingsPane.colorField.value
						}
					}

					Application.reinitialize()
				}
			}

			"button-confirm-load" -> {
				( ctx as LoadGamePane ).run {
					val profile = ctx.profile.value
					if (profile != null) {
						ctx.dialog.dialogPane.scene.window.hide()
						GameStorage.INSTANCE.apply {
							this.player = profile
							if (ctx.dialog is LauncherDialog) {
								this.boardColor = ctx.dialog.settingsPane.colorField.value
							}
						}

						Application.reinitialize()
					}
				}
			}
		}
	}
}