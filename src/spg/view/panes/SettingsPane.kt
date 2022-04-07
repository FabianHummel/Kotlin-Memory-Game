package spg.view.panes

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ColorPicker
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import spg.control.DialogHandler
import spg.model.PlayerProfile
import spg.view.dialog.CustomDialogBase
import spg.view.utility.NumberField

class SettingsPane(
	val dialog: CustomDialogBase<PlayerProfile>
) : BorderPane() {

	val colorField : ColorPicker

	init {
		this.padding = Insets(10.0)

		this.center = VBox(
			ColorPicker().apply {
				colorField = this
				this.promptText = "Board Color"
				this.maxWidth = Double.MAX_VALUE
			},
		).apply {
			this.spacing = 10.0
		}
	}
}