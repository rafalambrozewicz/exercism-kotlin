import java.lang.IllegalArgumentException
import kotlin.Exception

class ZebraPuzzle {

    private val solutions: Set<Solution>

    init {
        var possibleSolutions = setOf<Solution>()

        val sentences = listOf(
                norwegian atPosition first, // (10. The Norwegian lives in the first house.)
                norwegian nextTo blue, // (15. The Norwegian lives next to the blue house.)
                milk atPosition third, // (9. Milk is drunk in the middle house.)
                iovy onRightOf green, // (6. The green house is immediately to the right of the ivory house.)
                green with coffee, // (4. Coffee is drunk in the green house.)
                english with red, // (2. The Englishman lives in the red house.)
                spaniard with dog, // (3. The Spaniard owns the dog.)
                ukrainian with tea, // (5. The Ukrainian drinks tea.)
                gold_old with snails, // (7. The Old Gold smoker owns snails.)
                kools with yellow, // (8. Kools are smoked in the yellow house.)
                chesterfields nextTo fox, // (11. The man who smokes Chesterfields lives in the house next to the man with the fox.)
                kools nextTo horse, // (12. Kools are smoked in the house next to the house where the horse is kept.)
                lucky_strike with orange_juice, // (13. The Lucky Strike smoker drinks orange juice.)
                japanese with parliaments // (14. The Japanese smokes Parliaments.)
        )

        sentences.forEach {
            val solutions = it.solutions()

            if (possibleSolutions.isEmpty()) {
                possibleSolutions = solutions
            } else {
                val updatedPossibleSolutions = mutableSetOf<Solution>()
                possibleSolutions.forEach { ps ->
                    solutions.forEach { s ->
                        try {
                            val ms = ps.merge(s)
                            updatedPossibleSolutions.add(ms)
                        } catch (e: MergeConflict) {
                            // silently dismiss conflicting solutions
                        }
                    }
                }
                possibleSolutions = updatedPossibleSolutions
            }
        }

        solutions = possibleSolutions.map { it.normalize() }.toSet()
    }

    fun drinksWater(): String {
        check(solutions.size == 1) { "Multiple solutions found, aborting!" }

        val nationality = solutions.first().houses.find { it.d == water }!!.n
        return nationality.toString().split("@")[0].capitalize()
    }

    fun ownsZebra(): String {
        check(solutions.size == 1) { "Multiple solutions found, aborting!" }

        val nationality = solutions.first().houses.find { it.a == zebra }!!.n
        return nationality.toString().split("@")[0].capitalize()
    }
}

class MergeConflict : Exception()

data class Solution(val houses: List<House>) {
    fun merge(s: Solution): Solution {
        val result = Solution(listOf(
                this.houses[0].merge(s.houses[0]),
                this.houses[1].merge(s.houses[1]),
                this.houses[2].merge(s.houses[2]),
                this.houses[3].merge(s.houses[3]),
                this.houses[4].merge(s.houses[4])))
        if ((result.houses.mapNotNull { it.n }.distinct().size != result.houses.mapNotNull { it.n }.size) ||
                (result.houses.mapNotNull { it.c }.distinct().size != result.houses.mapNotNull { it.c }.size) ||
                (result.houses.mapNotNull { it.d }.distinct().size != result.houses.mapNotNull { it.d }.size) ||
                (result.houses.mapNotNull { it.s }.distinct().size != result.houses.mapNotNull { it.s }.size) ||
                (result.houses.mapNotNull { it.a }.distinct().size != result.houses.mapNotNull { it.a }.size)) {
            throw MergeConflict()
        }

        return result
    }

    fun normalize(): Solution {
        val nationalities = listOf(english, spaniard, ukrainian, norwegian, japanese)
        val colors = listOf(red, green, iovy, yellow, blue)
        val drinks = listOf(coffee, tea, milk, orange_juice, water)
        val smokes = listOf(gold_old, kools, chesterfields, lucky_strike, parliaments)
        val animals = listOf(dog, snails, fox, horse, zebra)


        val missingNationality = (nationalities - houses.mapNotNull { it.n }).let {
            check(it.size < 2) { "Multiple nationalities missing!" }
            it.firstOrNull()
        }
        val missingColor = (colors - houses.mapNotNull { it.c }).let {
            check(it.size < 2) { "Multiple nationalities missing!" }
            it.firstOrNull()
        }
        val missingDrink = (drinks - houses.mapNotNull { it.d }).let {
            check(it.size < 2) { "Multiple drinks missing!" }
            it.firstOrNull()
        }
        val missingSmoke = (smokes - houses.mapNotNull { it.s }).let {
            check(it.size < 2) { "Multiple smokes missing!" }
            it.firstOrNull()
        }
        val missingAnimal = (animals - houses.mapNotNull { it.a }).let {
            check(it.size < 2) { "Multiple animals missing!" }
            it.firstOrNull()
        }

        return Solution(houses.map { it
                .addIfMissing(missingNationality)
                .addIfMissing(missingColor)
                .addIfMissing(missingDrink)
                .addIfMissing(missingSmoke)
                .addIfMissing(missingAnimal)})
    }
}

data class House(val n: Nationality? = null,
                 val c: Color? = null,
                 val d: Drink? = null,
                 val s: Smoke? = null,
                 val a: Animal? = null) {
    fun withFeature(f: Feature): House {
        require(f !is Position) { "'Position' cannot be used as 'f'" }
        return when (f) {
            is Nationality -> if (n != null && n != f) throw MergeConflict() else this.copy(n= f)
            is Color -> if (c != null && c != f) throw MergeConflict() else this.copy(c = f)
            is Drink -> if (d != null && d != f) throw MergeConflict() else this.copy(d = f)
            is Smoke -> if (s != null && s != f) throw MergeConflict() else this.copy(s = f)
            is Animal -> if (a != null && a != f) throw MergeConflict() else this.copy(a = f)
            else -> throw IllegalArgumentException("'Position' cannot be used as 'f'")
        }
    }

    fun merge(h: House): House {
        return House(
                n = if (this.n == null || h.n == null || this.n == h.n) this.n ?: h.n else throw MergeConflict(),
                c = if (this.c == null || h.c == null || this.c == h.c) this.c ?: h.c else throw MergeConflict(),
                d = if (this.d == null || h.d == null || this.d == h.d) this.d ?: h.d else throw MergeConflict(),
                s = if (this.s == null || h.s == null || this.s == h.s) this.s ?: h.s else throw MergeConflict(),
                a = if (this.a == null || h.a == null || this.a == h.a) this.a ?: h.a else throw MergeConflict()
        )
    }

    fun addIfMissing(f: Feature?): House {
        return when {
            f is Nationality && n == null -> this.copy(n= f)
            f is Color && c == null -> this.copy(c = f)
            f is Drink && d == null -> this.copy(d = f)
            f is Smoke && s == null -> this.copy(s = f)
            f is Animal && a == null -> this.copy(a = f)
            else -> this
        }
    }
}

abstract class Feature

sealed class Position: Feature() { abstract val id: Int }
object first: Position() { override val id = 0 }
object second: Position() { override val id = 1 }
object third: Position() { override val id = 2 }
object fourth: Position() { override val id = 3 }
object fifth: Position() { override val id = 4 }

sealed class Nationality: Feature()
object english: Nationality(); object spaniard: Nationality(); object ukrainian: Nationality(); object norwegian: Nationality(); object japanese: Nationality()

sealed class Color: Feature()
object red: Color(); object green: Color(); object iovy: Color(); object yellow: Color(); object blue: Color()

sealed class Drink: Feature()
object coffee: Drink(); object tea: Drink(); object milk: Drink(); object orange_juice: Drink(); object water: Drink()

sealed class Smoke: Feature()
object gold_old: Smoke(); object kools: Smoke(); object chesterfields: Smoke(); object lucky_strike: Smoke(); object parliaments: Smoke()

sealed class Animal: Feature()
object dog: Animal(); object snails: Animal(); object fox: Animal(); object horse: Animal(); object zebra: Animal()

enum class Relationship {
    AT_POSITION, WITH, ON_THE_LEFT_OF, ON_THE_RIGHT_OF, NEXT_TO
}

infix fun Feature.atPosition(p: Position): Sentence = Sentence(this, Relationship.AT_POSITION, p)
infix fun Feature.with(f: Feature): Sentence = Sentence(this, Relationship.WITH, f)
infix fun Feature.onLeftOf(f: Feature): Sentence = Sentence(this, Relationship.ON_THE_LEFT_OF, f)
infix fun Feature.onRightOf(f: Feature): Sentence = Sentence(this, Relationship.ON_THE_RIGHT_OF, f)
infix fun Feature.nextTo(f: Feature): Sentence = Sentence(this, Relationship.NEXT_TO, f)

data class Sentence(val f1: Feature, val r: Relationship, val f2: Feature) {
    fun solutions(): Set<Solution> {
        return when (r) {
            Relationship.AT_POSITION -> setOf(Solution(List(5) { if ((f2 as Position).id == it) House().withFeature(f1) else House() }))

            Relationship.WITH -> (List(5) { i -> Solution(List(5) { if (it == i) House().withFeature(f1).withFeature(f2) else House() }) }).toSet()

            Relationship.ON_THE_LEFT_OF -> List(4) { i -> Solution(List(5) {
                when (it) {
                    i -> House().withFeature(f1)
                    i + 1 -> House().withFeature(f2)
                    else -> House()
                }
            })}.toSet()

            Relationship.ON_THE_RIGHT_OF -> List(4) { i -> Solution(List(5) {
                when (it) {
                    i -> House().withFeature(f2)
                    i + 1 -> House().withFeature(f1)
                    else -> House()
                }
            })}.toSet()

            Relationship.NEXT_TO -> List(4) { i -> Solution(List(5) {
                when {
                    (i == it)  -> House().withFeature(f1)
                    (i + 1 == it) -> House().withFeature(f2)
                    else -> House()
                }
            }) }.toSet() + List(4) { i -> Solution(List(5) {
                when {
                    (i == it)  -> House().withFeature(f2)
                    (i + 1 == it) -> House().withFeature(f1)
                    else -> House()
                }
            }) }.toSet()
        }
    }
}