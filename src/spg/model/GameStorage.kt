package spg.model

import javafx.scene.paint.Color

class GameStorage {
	companion object {
		lateinit var INSTANCE : GameStorage
	}

	val boardData = BoardData()
	lateinit var player : PlayerProfile
	var boardColor = Color.web(
		"#a64151"
	) // Later applied through a settings menu

	init {
		INSTANCE = this
	}
}