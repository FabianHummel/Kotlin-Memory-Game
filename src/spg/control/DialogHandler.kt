package spg.control

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import spg.model.GameStorage
import spg.model.PlayerProfile
import spg.view.panes.LoadGamePane
import spg.view.panes.NewGamePane

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

						ctx.dialog.dialogPane.scene.window.hide()
					} catch (e : Exception) {
						return
					}

					GameStorage.INSTANCE.player = profile
				}
			}

			"button-confirm-load" -> {
				( ctx as LoadGamePane ).run {
					val profile = ctx.profile.value
					if (profile != null) {
						GameStorage.INSTANCE.player = profile
						ctx.dialog.dialogPane.scene.window.hide()
					}
				}
			}
		}
	}
}