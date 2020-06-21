import kotlin.random.Random

data class Cipher(val key: String = fallbackRandomKey()) {

    init {
        require(key.isNotEmpty()) { "key cannot be empty" }
        require(!key.any { it.isDigit() }) { "key cannot contain digits" }
        require(!key.any { it.isUpperCase() }) { "key cannot contain upper cased letter" }
    }

    fun encode(s: String): String {
        val normalizedKey = key.padEnd(length = s.length, padString = key)
        return s.zip(normalizedKey) { c1, c2 ->
            val validShift = (c1 - 'a') + (c2 - 'a')
            'a' + (validShift % 26) }
                .joinToString(separator = "")
    }

    fun decode(s: String): String {
        val normalizedKey = key.padEnd(length = s.length, padString = key)
        return s.zip(normalizedKey) { c1, c2 ->
            val shift = (c1 - 'a') - (c2 - 'a')
            val validShift = if (shift<0) shift + 26 else shift
            'a' + (validShift % 26) }
                .joinToString(separator = "")
    }
}

private fun String.padEnd(length: Int, padString: String): String {
    var key = this
    do {
        key += padString
    } while (key.length < length)

    return key.take(length)
}

private fun fallbackRandomKey(): String = (0 until 100)
        .map { 'a' + Random.nextInt(25) }
        .joinToString(separator = "")