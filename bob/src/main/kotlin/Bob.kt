object Bob {
    fun hey(input: String): String = when {
        input.isSilence() -> "Fine. Be that way!"
        input.isShouting() && input.isQuestion() -> "Calm down, I know what I'm doing!"
        input.isShouting() -> "Whoa, chill out!"
        input.isQuestion() -> "Sure."
        else -> "Whatever."
    }

    private fun String.isQuestion(): Boolean = this.trim().endsWith("?")

    private fun String.isShouting(): Boolean = (
            (this.filter { it.isLetter() }.count() == 0 && this.trim().endsWith("!")) ||
            (this.filter { it.isLetter() }.count() > 0 && this.filter { it.isLetter() }.all { it.isUpperCase() }))

    private fun String.isSilence(): Boolean = this.isBlank()
}