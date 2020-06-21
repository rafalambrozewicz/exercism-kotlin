import kotlin.random.Random

class DndCharacter {

    val strength: Int = ability()
    val dexterity: Int = ability()
    val constitution: Int = ability()
    val intelligence: Int = ability()
    val wisdom: Int = ability()
    val charisma: Int = ability()
    val hitpoints: Int = 10 + modifier(constitution)

    companion object {

        fun ability(): Int = List(4) { rollTheDice() }.sorted().drop(1).sum()

        private fun rollTheDice(): Int = Random.Default.nextInt(6) + 1

        fun modifier(score: Int): Int = kotlin.math.floor(((score - 10.0) / 2.0)).toInt()
    }
}