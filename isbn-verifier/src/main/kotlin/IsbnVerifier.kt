class IsbnVerifier {

    fun isValid(number: String): Boolean {
        if (!number.matches("([0-9]-?[0-9]{3}-?[0-9]{5}-?[0-9X])".toRegex())) {
            return false
        }

        val multipliers = 10 downTo 1
        val numbers = number.filter { it.isDigit() || it == 'X' }
                .map {
                    when {
                        it.isDigit() -> it - '0'
                        it == 'X' -> 10
                        else -> throw IllegalStateException("Unexpected char of '$it' in provided ISBN")
                } }

        return multipliers.zip(numbers) { m, n -> m * n }.sum() % 11 == 0
    }
}
