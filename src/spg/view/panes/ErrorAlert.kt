package spg.view.panes

import javafx.scene.control.Alert
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.Modality

class ErrorAlert(
	private val message : String
) : Alert(AlertType.ERROR) {
	init {
		this.initModality(
			Modality.APPLICATION_MODAL
		)

		this.title = "Error"
		this.headerText = message
//		this.contentText = ""
		this.graphic = ImageView(
			Image(
				"application/error.png"
			)
		).apply {
			this.fitWidth = 60.0
			this.fitHeight = 60.0
		}

		this.showAndWait()
	}
}