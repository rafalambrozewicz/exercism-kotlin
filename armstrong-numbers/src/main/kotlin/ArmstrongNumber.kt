import kotlin.math.pow

object ArmstrongNumber {

    fun check(input: Int): Boolean {
        return input == input.let {
            val digits = it.toString().chunked(1).map { d -> d.toDouble() }
            val power = it.toString().length

            digits.map { d -> d.pow(power) }.sum().toInt()
        }
    }
}
