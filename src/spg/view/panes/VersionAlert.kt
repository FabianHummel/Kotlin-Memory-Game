package spg.view.panes

import javafx.scene.control.Alert
import javafx.scene.effect.InnerShadow
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import javafx.stage.Modality

class VersionAlert : Alert(AlertType.INFORMATION) {
	init {
		this.initModality(
			Modality.APPLICATION_MODAL
		)

		this.title = "Version"
		this.headerText = "Application Version"
		this.contentText = "Alpha 0.1"
		this.graphic = ImageView(
			Image(
				"application/logo.png"
			)
		).apply {
			this.fitWidth = 60.0
			this.fitHeight = 60.0
			this.effect = InnerShadow().apply {
				this.choke = 0.42
				this.width = 20.0
				this.height = 10.0
				this.radius = 9.5
				this.offsetX = 1.0
				this.offsetY = 1.0
				this.color = Color.web(
					"rgba(0, 0, 0, 0.25)"
				)
			}
		}
	}
}