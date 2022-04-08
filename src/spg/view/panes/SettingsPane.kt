package spg.view.panes

import javafx.geometry.Insets
import javafx.scene.control.ColorPicker
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import spg.model.PlayerProfile
import spg.view.dialog.CustomDialogBase

class SettingsPane(
	val dialog: CustomDialogBase<PlayerProfile>
) : BorderPane() {
	val colorField : ColorPicker

	init {
		this.padding = Insets(10.0)

		this.center = VBox(
			ColorPicker().apply {
				colorField = this
				this.value = Color.rgb(
					(Math.random() * 64 + 64).toInt(),
					(Math.random() * 64 + 64).toInt(),
					(Math.random() * 64 + 64).toInt()
				)
				this.promptText = "Board Color"
				this.maxWidth = Double.MAX_VALUE
			},
		).apply {
			this.spacing = 10.0
		}
	}
}