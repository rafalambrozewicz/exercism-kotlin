object WordCount {

    fun phrase(phrase: String): Map<String, Int> {
        val normalizedPhrase = phrase
                .toLowerCase()
                .replace(",", ", ")
                .replace("[^a-z\\d\\s']".toRegex(), "")
                .replace("\\s+".toRegex(), " ")
                .trim()
                .split(" ")
                .joinToString(separator = " ") { it.trim('\'') }

        return normalizedPhrase
                .split(" ")
                .groupBy { it }
                .map { it.key to it.value.size }
                .toMap()
    }
}
