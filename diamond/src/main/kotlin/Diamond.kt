class DiamondPrinter {

    companion object {
        const val TWO_LETTERS_LENGTH = 2
    }

    fun printToList(c: Char): List<String> {
        require( ('A'..'Z').contains(c)) { "Input char of $c no in required [A-Z] range" }

        val output = mutableListOf<String>()
        val n = c - 'A'
        val totalWidthOrHeight = 2 * n + 1

        (0 until totalWidthOrHeight).forEach { index ->
            when {
                index.isFirstLine() || index.isLastLine(totalWidthOrHeight) -> {
                    val horizontalMargin = totalWidthOrHeight / 2
                    output.add( " ".repeat(horizontalMargin) + "A" + " ".repeat(horizontalMargin))
                }
                else -> {
                    if (index.beforeOrInCenter(totalWidthOrHeight)) {
                        val letter = 'A' + index
                        val centerMargin = 2 * (index-1) + 1
                        val sidesMargin = (totalWidthOrHeight - centerMargin - TWO_LETTERS_LENGTH) / 2

                        output.add( " ".repeat(sidesMargin) + letter + " ".repeat(centerMargin) + letter + " ".repeat(sidesMargin))
                    } else {
                        val letter = c - (index - totalWidthOrHeight / 2)
                        val sidesMargin = (index - totalWidthOrHeight / 2)
                        val centerMargin = totalWidthOrHeight - 2 * sidesMargin - TWO_LETTERS_LENGTH

                        output.add( " ".repeat(sidesMargin) + letter + " ".repeat(centerMargin) + letter + " ".repeat(sidesMargin))
                    }
                }
            }
        }

        return output
    }

    private fun Int.isFirstLine(): Boolean = (this == 0)

    private fun Int.isLastLine(totalSize: Int): Boolean = (this == (totalSize - 1))

    private fun Int.beforeOrInCenter(totalSize: Int): Boolean = (this <= (totalSize / 2))
}