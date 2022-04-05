package spg.control.utility

import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.util.Callback
import spg.model.PlayerProfile

class PlayerProfileFactory : Callback<ListView<PlayerProfile>, ListCell<PlayerProfile>> {
	override fun call(view : ListView<PlayerProfile>) : ListCell<PlayerProfile> {
		return object : ListCell<PlayerProfile>() {
			override fun updateItem(item: PlayerProfile?, empty: Boolean) {
				super.updateItem(item, empty)
				if (item != null) {
					text = item.name
				}
			}
		}
	}
}