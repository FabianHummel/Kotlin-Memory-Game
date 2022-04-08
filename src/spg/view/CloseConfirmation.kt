package spg.view

import javafx.scene.control.Alert

class CloseConfirmation : Alert(AlertType.CONFIRMATION) {
	init {
		title = "Close"
		headerText = "Close Confirmation"
		contentText = "Are you sure you want to close the application?"
	}
}