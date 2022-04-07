package spg.model

import javafx.scene.paint.Color
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class GameStorage {
	companion object {
		lateinit var INSTANCE : GameStorage
	}

	val boardData = BoardData()
	val profiles = ArrayList<PlayerProfile>()

	lateinit var player : PlayerProfile
	var boardColor : Color = Color.web(
		"#a64151"
	) // Later applied through a settings menu

	init {
		INSTANCE = this
	}

	fun save() {
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
}