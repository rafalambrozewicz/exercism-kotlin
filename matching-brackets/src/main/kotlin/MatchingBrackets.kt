object MatchingBrackets {

    private val BRACKETS = listOf('(', ')', '[', ']', '{', '}')

    fun isValid(input: String): Boolean {
        return input.filter { BRACKETS.contains(it) }.fold(listOf<Char>(), { acc, lastBracket ->
            val preLastBracket = acc.lastOrNull()

            if ((preLastBracket == '(' && lastBracket == ')') ||
                (preLastBracket == '[' && lastBracket == ']') ||
                (preLastBracket == '{' && lastBracket == '}')) {
                acc.dropLast(1)
            } else {
                acc + lastBracket
            }
        }).isEmpty()
    }
}