package spg.view.dialog

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.stage.WindowEvent
import spg.model.PlayerProfile
import spg.view.panes.NewGamePane

class NewGameDialog : CustomDialogBase<PlayerProfile>() {

	override fun content() : Node {
		return NewGamePane(
			this@NewGameDialog
		)
	}

	override fun onClose(event : WindowEvent) {
		this@NewGameDialog.hide()
	}

	override fun onCancel(event : ActionEvent) {
		this@NewGameDialog.dialogPane.scene.window.hide()
	}
}