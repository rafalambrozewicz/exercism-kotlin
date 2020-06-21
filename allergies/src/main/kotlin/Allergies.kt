class Allergies(private val score: Int) {

    fun getList(): List<Allergen> {
        return Allergen.values().filter { isAllergicTo(it) }
    }

    fun isAllergicTo(allergen: Allergen): Boolean {
        return (score and allergen.score) and allergen.score == allergen.score
    }
}
