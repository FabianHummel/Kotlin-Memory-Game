package spg.view.dialog

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.Dialog
import javafx.stage.Modality
import javafx.stage.WindowEvent

abstract class CustomDialogBase<T> : Dialog<T>() {
	init {
		this.initModality(
			Modality.APPLICATION_MODAL
		)

		this.dialogPane.scene.window.run {
			this.onCloseRequest = EventHandler {
				onClose(it)
			}
		}

		this.isResizable = false
		this.dialogPane.apply {
			this.prefWidth = 250.0
			this.prefHeight = 320.0
			this.content = content()
		}
	}

	abstract fun content() : Node

	abstract fun onClose(event: WindowEvent)

	abstract fun onCancel(event: ActionEvent)
}