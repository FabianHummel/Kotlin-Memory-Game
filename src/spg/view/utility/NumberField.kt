package spg.view.utility

import javafx.scene.control.TextField
import spg.control.utility.NumberFieldHandler

class NumberField(text : String) : TextField(text) {
	constructor() : this(
		""
	)

	init {
		textProperty().addListener(
			NumberFieldHandler(
				this
			)
		)
	}
}