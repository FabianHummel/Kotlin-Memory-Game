package spg.view.panes

import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TextArea
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import spg.control.DialogHandler
import spg.control.utility.ComboBoxInspector
import spg.control.utility.PlayerProfileFactory
import spg.model.GameStorage
import spg.model.PlayerProfile
import spg.view.dialog.CustomDialogBase

class LoadGamePane(
	val dialog: CustomDialogBase<PlayerProfile>
) : BorderPane() {

	var isEmpty: Boolean = true
		private set
	val profile: ComboBox<PlayerProfile>
	private val textArea = TextArea().apply {
		this.isEditable = false
	}

	init {
		this.padding = Insets(10.0)

		this.center = VBox(
			ComboBox<PlayerProfile>().apply {
				profile = this
				this.maxWidth = Double.MAX_VALUE
				this.promptText = "Choose an existing profile"
				this.cellFactory = PlayerProfileFactory()
				this.valueProperty().addListener(
					ComboBoxInspector(
						textArea
					)
				)

				PlayerProfile.loadAll().also {
					isEmpty = it.isEmpty()
					this.items = FXCollections.observableArrayList(
						it
					)

					GameStorage.INSTANCE.profiles.addAll(
						it
					)
				}
			},

			textArea
		).apply {
			this.spacing = 10.0
		}

		this.bottom = HBox(
			Button("Confirm").apply {
				this.userData = "button-confirm-load"
				this.onAction = DialogHandler(
					this@LoadGamePane
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