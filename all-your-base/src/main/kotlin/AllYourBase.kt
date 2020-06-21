import kotlin.math.pow

class BaseConverter(private val base: Int, private val digits: IntArray) {

    init {
        require(base >= 2) { "Bases must be at least 2." }
        require(digits.isNotEmpty()) { "You must supply at least one digit." }
        require( digits.first() != 0 || digits.size == 1) { "Digits may not contain leading zeros." }
        require(digits.all { it >= 0 }) { "Digits may not be negative." }
        require(digits.all { it < base }) { "All digits must be strictly less than the base." }
    }

    fun convertToBase(newBase: Int): IntArray {
        require(newBase >= 2) { "Bases must be at least 2." }

        val result = mutableListOf<Int>()
        var noInDec = digits.reversed()
                .mapIndexed { exponent, value -> value *  base.pow(exponent) }
                .sum()
        if (noInDec == 0) { result.add(0, 0) }

        while (noInDec != 0) {
            result.add(0, noInDec % newBase)
            noInDec /= newBase
        }

        return result.toIntArray()
    }

    private fun Int.pow(exponent: Int): Int = this.toDouble().pow(exponent).toInt()
}
