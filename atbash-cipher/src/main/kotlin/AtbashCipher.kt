object Atbash {

    private const val CIPHER = "zyxwvutsrqponmlkjihgfedcba"

    fun encode(text: String): String {
        return  text.replace(" ", "")
                .replace(".", "")
                .replace(",", "")
                .toLowerCase()
                .toCharArray()
                .map { it -> if(it.isLetter()) { CIPHER[it - 'a'] } else it }
                .chunked(5)
                .map { it -> String(it.toCharArray()) }
                .joinToString(" ")
    }

    fun decode(text: String): String {
        return text.replace(" ", "")
                .toLowerCase()
                .map { it -> if(it.isLetter()) { CIPHER[it -'a' ]} else it  }
                .toCharArray()
                .joinToString("")
    }
}