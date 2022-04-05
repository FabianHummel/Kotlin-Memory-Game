package spg.model

import javafx.scene.paint.Color

class GameStorage {
	companion object {
		lateinit var INSTANCE : GameStorage
	}

	lateinit var player : PlayerProfile
	var boardData = BoardData()
	var boardColor = Color.PURPLE

	init {
		INSTANCE = this
	}
}