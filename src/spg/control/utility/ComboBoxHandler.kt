package spg.control.utility

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import spg.model.PlayerProfile

class ComboBoxHandler(
	private val area : TextArea
) : ChangeListener<PlayerProfile> {
	override fun changed(value : ObservableValue<out PlayerProfile>?, oldValue : PlayerProfile?, newValue : PlayerProfile?) {
		if (newValue != null) {
			area.text =
				"Player: ${newValue.name}\n" +
				"Attempts: ${newValue.attempts}\n" +
				"Board Size: ${newValue.boardX} x ${newValue.boardY}"
		}
	}
}