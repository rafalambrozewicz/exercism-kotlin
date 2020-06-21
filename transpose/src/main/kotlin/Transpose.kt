object Transpose {

    private const val SPACE = ' '
    private const val NULL_CHAR = 0.toChar()
    private const val EMPTY_STRING = ""

    fun transpose(input: List<String>): List<String> {
        val inputIndexToLength = input.map { it.length }
        val toPadWithNullLength = inputIndexToLength.max() ?: 0
        val normalizedInput = input.mapIndexed { i, s ->
            val toPadWithSpaceLength = inputIndexToLength.drop(i+1).max() ?: 0
            s.padEnd(toPadWithSpaceLength, SPACE).padEnd(toPadWithNullLength, NULL_CHAR) }

        return (0 until toPadWithNullLength)
                .map { index -> normalizedInput
                        .map { it[index] }
                        .joinToString(separator = EMPTY_STRING) }
                .map { s -> s.filter { it != NULL_CHAR }}
    }
}
