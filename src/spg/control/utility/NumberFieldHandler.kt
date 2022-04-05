package spg.control.utility

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.TextField

class NumberFieldHandler(
	private val field : TextField
) : ChangeListener<String> {
	override fun changed(value : ObservableValue<out String>, oldValue : String, newValue : String) {
		field.text = newValue.replace(
			Regex("[^\\d]"), ""
		)
	}
}