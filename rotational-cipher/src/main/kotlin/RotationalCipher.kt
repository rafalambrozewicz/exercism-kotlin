class RotationalCipher(private val rot: Int) {

    fun encode(text: String): String {
        return text.map {
            when {
                it.isUpperCase() -> ('A' + ((it - 'A' + rot) % 26))
                it.isLowerCase() -> ('a' + ((it - 'a' + rot) % 26))
                else -> it
            }
        }.joinToString(separator = "")
    }
}
