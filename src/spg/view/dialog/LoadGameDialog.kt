package spg.view.dialog

import javafx.event.ActionEvent
import javafx.scene.Node
import javafx.stage.WindowEvent
import spg.model.PlayerProfile
import spg.view.panes.LoadGamePane

class LoadGameDialog : CustomDialogBase<PlayerProfile>() {

	override fun content() : Node {
		return LoadGamePane(
			this@LoadGameDialog
		)
	}

	override fun onClose(event : WindowEvent) {
		this@LoadGameDialog.hide()
	}

	override fun onCancel(event : ActionEvent) {
		this@LoadGameDialog.dialogPane.scene.window.hide()
	}
}