package spg.view

import javafx.animation.*
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.transform.Rotate
import javafx.util.Duration
import jdk.jfr.TransitionFrom
import spg.control.MemoryCardHandler
import spg.control.MemoryHandler
import spg.model.CardData
import spg.model.GameStorage
import java.security.SecureRandom

class MemoryCard(
	memory : MemoryHandler,
	val data : CardData
) : BorderPane() {
	private var flipped : Boolean = false

	init {
		this.prefWidth = 50.0
		this.prefHeight = 50.0
		this.background = Background(
			BackgroundFill(
				GameStorage.INSTANCE.boardColor,
				CornerRadii(3.0),
				Insets.EMPTY
			)
		)

		this.border = Border(
			BorderStroke(
				GameStorage.INSTANCE.boardColor.brighter(),
				BorderStrokeStyle.SOLID,
				CornerRadii(3.0),
				BorderWidths(3.0)
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
					this@MemoryCard.rotate = 270.0
					rotateTo(360.0) {
						resetRotation()
					}
				}
			}
			false -> {
				jump()
				rotateTo(90.0) {
					applyBackground()
					this@MemoryCard.rotate = 270.0
					rotateTo(360.0) {
						resetRotation()
					}
				}
			}
		}
	}

	fun consume() {
		flipped = flipped.not()
		rotate()
		scale()
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
				Image("bilder/${data.identifier}.jpg"),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				BackgroundSize(
					this.width,
					this.height,
					true,
					true,
					true,
					true
				)
			)
		)

		this.clip = Rectangle(
			this.width,
			this.height
		).apply {
			this.arcWidth = 10.0
			this.arcHeight = 10.0
		}
	}

	private fun applyBackground() {
		this.background = Background(
			BackgroundFill(
				GameStorage.INSTANCE.boardColor,
				CornerRadii(3.0),
				Insets.EMPTY
			)
		)

		this.clip = null
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

	private fun scale(handler : EventHandler<ActionEvent>? = null) {
		scaleOut(
			handler
		)
	}

	private fun scaleOut(handler : EventHandler<ActionEvent>? = null) {
		ScaleTransition().apply {
			this.duration = Duration.seconds(0.2)
			this.node = this@MemoryCard
			this.byX = 0.1
			this.byY = 0.1
			this.onFinished = EventHandler {
				scaleIn(handler)
			}
			this.interpolator = Interpolator.EASE_OUT
		}.play()
	}

	private fun scaleIn(handler : EventHandler<ActionEvent>? = null) {
		ScaleTransition().apply {
			this.duration = Duration.seconds(0.4)
			this.node = this@MemoryCard
			this.toX = 0.0
			this.toY = 0.0
			this.onFinished = handler
			this.interpolator = Interpolator.SPLINE(
				.88, .0, .32, .8
			)
		}.play()
	}

	private fun rotate() {
		RotateTransition().apply {
			this.duration = Duration.seconds(0.6)
			this.node = this@MemoryCard
			this.byAngle = SecureRandom().nextDouble() * 45.0 - (45.0 / 2.0)
			this.interpolator = Interpolator.SPLINE(
				.4, .1, .27, .99
			)
			this.axis = Rotate.Z_AXIS
		}.play()
	}
}