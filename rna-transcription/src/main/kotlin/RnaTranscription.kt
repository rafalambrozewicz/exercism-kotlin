import java.lang.StringBuilder

fun transcribeToRna(dna: String): String  {
    val sb = StringBuilder()
    for (letter in dna) {
        when (letter) {
            'G' -> sb.append('C')
            'C' -> sb.append('G')
            'T' -> sb.append('A')
            'A' -> sb.append('U')
            else -> throw IllegalArgumentException("Invalid DNA provided")
        }
    }
    return sb.toString()
}
