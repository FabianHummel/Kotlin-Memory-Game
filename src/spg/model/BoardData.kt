package spg.model

class BoardData : ArrayList<CardData>() {

	fun addCard(card: CardData) {
		add(card)
	}

	fun delCard(card: CardData) {
		remove(card)
	}

	fun getCard(index: Int): CardData {
		return this[index]
	}
}