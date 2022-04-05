package spg.view

import javafx.animation.Interpolator
import javafx.animation.RotateTransition
import javafx.animation.TranslateTransition
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.transform.Rotate
import javafx.util.Duration
import spg.control.MemoryCardHandler
import spg.control.MemoryHandler
import spg.model.GameStorage

class MemoryCard(
	memory : MemoryHandler,
	private val image : Image,
	val posX : Int,
	val posY : Int
) : BorderPane() {
	private var flipped : Boolean = false

	init {
		this.prefWidth = 50.0
		this.prefHeight = 50.0
		this.background = Background(
			BackgroundFill(
				Color.TEAL,
				CornerRadii(3.0),
				Insets.EMPTY
			)
		)

		this.userData = "card"
		this.onMouseClicked = MemoryCardHandler(
			memory
		)
	}

	fun isFlipped() : Boolean {
		return flipped
	}

	fun flip() {
		flipped = flipped.not()
		when (flipped) {
			true -> {
				jump()
				rotateTo(90.0) {
					applyImage()
					rotateTo(180.0) {

					}
				}
			}
			false -> {
				jump()
				rotateTo(270.0) {
					applyBackground()
					rotateTo(360.0) {
						resetRotation()
					}
				}
			}
		}
	}

	private fun rotateTo(angle : Double, handler : EventHandler<ActionEvent>? = null) {
		RotateTransition().apply {
			this.duration = Duration.seconds(0.2)
			this.node = this@MemoryCard
			this.toAngle = angle
			this.interpolator = Interpolator.EASE_BOTH
			this.axis = Rotate.Y_AXIS
			this.onFinished = handler
		}.play()
	}

	private fun applyImage() {
		this.background = Background(
			BackgroundImage(
				image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT
			)
		)
	}

	private fun applyBackground() {
		this.background = Background(
			BackgroundFill(
				GameStorage.INSTANCE.boardColor,
				CornerRadii(3.0),
				Insets.EMPTY
			)
		)
	}

	private fun resetRotation() {
		this.rotate = 0.0
	}

	private fun jump() {
		TranslateTransition().apply {
			this.duration = Duration.seconds(0.2)
			this.node = this@MemoryCard
			this.toY = -6.5
			this.interpolator = Interpolator.EASE_OUT
			this.onFinished = EventHandler {
				this@MemoryCard.fall()
			}
		}.play()
	}

	private fun fall() {
		TranslateTransition().apply {
			this.duration = Duration.seconds(0.2)
			this.node = this@MemoryCard
			this.toY = 0.0
			this.interpolator = Interpolator.LINEAR
		}.play()
	}
}