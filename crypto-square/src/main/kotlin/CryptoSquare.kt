import kotlin.math.ceil
import kotlin.math.sqrt

object CryptoSquare {

    fun ciphertext(plaintext: String): String {

        if (plaintext.isEmpty()) { return plaintext }

        val normalizedText = plaintext
                .replace(" ", "")
                .filter { it.isLetter() || it.isDigit() }
                .toLowerCase()

        val c = ceil(sqrt(normalizedText.length.toDouble())).toInt()
        val r = if (c*(c-1) >= normalizedText.length) (c-1) else c

        val paddedNormalizedText = normalizedText.padEnd(c * r, ' ')
        val chunkedPaddedNormalizedText = paddedNormalizedText.chunked(c)

        return (0 until c).joinToString(separator = " ") { i ->
            chunkedPaddedNormalizedText.map { it[i] }.joinToString(separator = "")
        }
    }

}
