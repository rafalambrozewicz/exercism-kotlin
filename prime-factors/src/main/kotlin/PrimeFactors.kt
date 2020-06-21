import kotlin.math.ceil
import kotlin.math.sqrt

object PrimeFactorCalculator {

    fun primeFactors(int: Int): List<Int> {
        val factors = mutableListOf<Int>()

        var number = int
        for (d in 2..ceil(sqrt(int.toDouble())).toInt()) {
            if (number == 1) { break }
            while (number % d == 0) {
                factors.add(d)
                number /= d
            }
        }

        return factors
    }

    fun primeFactors(long: Long): List<Long> {
        val factors = mutableListOf<Long>()

        var number = long
        for (d in 2..long) {
            if (number == 1L) { break }
            while (number % d == 0L) {
                factors.add(d)
                number /= d
            }
        }

        return factors
    }
}