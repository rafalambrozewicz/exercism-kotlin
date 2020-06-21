class ScrabbleScore {

    companion object {
        fun scoreWord(word: String): Int = word.toUpperCase()
                .toCharArray()
                .fold(initial = 0) { acc, char -> acc + score(char) }

        private fun score(char: Char): Int = when (char) {
            in CHARS_FOR_ONE_POINT -> 1
            in CHARS_FOR_TWO_POINTS -> 2
            in CHARS_FOR_THREE_POINTS -> 3
            in CHARS_FOR_FOUR_POINTS -> 4
            in CHARS_FOR_FIVE_POINTS -> 5
            in CHARS_FOR_EIGHT_POINTS -> 8
            in CHARS_FOR_TEN_POINTS -> 10
            else -> 0
        }

        private val CHARS_FOR_ONE_POINT = listOf('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T')
        private val CHARS_FOR_TWO_POINTS = listOf('D', 'G')
        private val CHARS_FOR_THREE_POINTS = listOf('B', 'C', 'M', 'P')
        private val CHARS_FOR_FOUR_POINTS = listOf('F', 'H', 'V', 'W', 'Y')
        private val CHARS_FOR_FIVE_POINTS = listOf('K')
        private val CHARS_FOR_EIGHT_POINTS = listOf('J', 'X')
        private val CHARS_FOR_TEN_POINTS = listOf('Q', 'Z')
    }
}