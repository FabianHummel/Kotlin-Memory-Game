package spg.model

import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.io.Serializable

data class PlayerProfile(
	val name: String,
	val attempts: Int,
	val boardX : Int,
	val boardY : Int
) : Serializable {
	companion object {
		@Suppress("UNCHECKED_CAST")
		fun loadAll() : List<PlayerProfile> {
			if (File("profiles.dat").exists()) {
				return ObjectInputStream(
					FileInputStream(
						File("profiles.dat")
					)
				).readObject() as List<PlayerProfile>
			}
			return emptyList()
		}
	}

	override fun toString() : String {
		return this.name
	}
}