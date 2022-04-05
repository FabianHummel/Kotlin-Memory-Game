package spg.view.panes

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import spg.control.DialogHandler
import spg.model.PlayerProfile
import spg.view.dialog.CustomDialogBase
import spg.view.utility.NumberField

class NewGamePane(
	val dialog: CustomDialogBase<PlayerProfile>
) : BorderPane() {

	val nameField : TextField
	val boardLength : NumberField
	val boardWidth : NumberField

	init {
		this.padding = Insets(10.0)

		this.center = VBox(
			TextField().apply {
				nameField = this
				this.promptText = "Player Name"
			},
			NumberField().apply {
				boardLength = this
				this.promptText = "Board Length"
			},
			NumberField().apply {
				boardWidth = this
				this.promptText = "Board Width"
			},
		).apply {
			this.spacing = 10.0
		}

		this.bottom = HBox(
			Button("Confirm").apply {
				this.userData = "button-confirm-new"
				this.onAction = DialogHandler(
					this@NewGamePane
				)
			},

			Button("Cancel").apply {
				this.userData = "button-cancel"
				this.onAction = EventHandler {
					dialog.onCancel(
						it
					)
				}
			}
		).apply {
			this.alignment = Pos.CENTER
			this.spacing = 10.0
		}
	}
}