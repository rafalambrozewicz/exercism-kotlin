object Luhn {

    fun isValid(input: String): Boolean {
        input.replace(" ", "").let { s ->
            if (s.length < 2) return false
            if (s.any { !it.isDigit() }) return false

            return s.toCharArray()
                    .reversed()
                    .map { it.toString().toInt() }
                    .mapIndexed { index, int -> if (index.isOdd()) int.luhnDouble() else int }
                    .sum() % 10 == 0
        }
    }

    private fun Int.isOdd() : Boolean = (this % 2 == 1)

    private fun Int.luhnDouble(): Int = if (this*2>9) { this*2-9 } else { this*2 }
}