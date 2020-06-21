object PigLatin {

    fun translate(phrase: String): String = phrase.split(" ").joinToString(separator = " ") { it.piggify() }

    private fun String.piggify(): String {
        return when {
            this.matches("^[bcdfghjklmnprstvxz]{0,}qu.*".toRegex()) ->
                this.substringAfter("qu") + this.substringBefore("qu") + "quay"

            (this.length == 2 && this[1] == 'y') -> "y" + this[0] + "ay"

            else -> {
                this.replace("^(.*?)(?=(a|e|i|o|u|yt|xr|xt)).*".toRegex()) {
                    it.groupValues[0].removePrefix(it.groupValues[1]) + it.groupValues[1] + "ay"
                }
            }
        }
    }
}
