package spg.model

import javafx.scene.paint.Color
import spg.control.GameOverHandler
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class GameStorage {
	companion object {
		lateinit var INSTANCE : GameStorage

		fun reinitialize() {
			INSTANCE.boardData = BoardData()
		}
	}

	var boardData = BoardData()
	val profiles = ArrayList<PlayerProfile>()

	lateinit var player : PlayerProfile
	var boardColor : Color = Color.web(
		"#a64151"
	)

	init {
		INSTANCE = this
		reinitialize()
	}

	fun contains(player : PlayerProfile) : Boolean {
		var contains = false
		profiles.forEach {
			if (it.name == player.name) {
				contains = true
			}
		}
		return contains
	}

	fun save() {
		if (contains(player)) {
			return
		}

		profiles.add(
			player
		)

		ObjectOutputStream(
			FileOutputStream(
				File(
					"profiles.dat"
				)
			)
		).use {
			it.writeObject(
				profiles
			)
		}
	}

	fun delCard(card : CardData) {
		boardData.delCard(
			card
		)

		if (boardData.isEmpty()) {
			GameOverHandler().handle(
				null
			)
		}
	}
}