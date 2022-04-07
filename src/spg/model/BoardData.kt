package spg.model

class BoardData {
	private val board: MutableList<CardData> = ArrayList()

	fun addCard(card: CardData) {
		board.add(card)
	}

	fun delCard(card: CardData) {
		board.remove(card)
	}

	fun getCard(index: Int): CardData {
		return board[index]
	}

	fun shuffle() {
		board.shuffle()
	}

	fun forEach(action: (CardData) -> Unit) {
		board.forEach(action)
	}
}