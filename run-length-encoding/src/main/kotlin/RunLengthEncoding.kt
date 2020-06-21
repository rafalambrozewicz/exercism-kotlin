object RunLengthEncoding {

    fun encode(input: String): String {
        val letterToCount = mutableListOf<Pair<Char, Int>>()
        var currentLetter: Char? = null
        var currentCount = 0

        input.forEach {
            if (currentLetter != it && currentCount !=0) {
                letterToCount.add(currentLetter!! to currentCount)
                currentLetter = it
                currentCount = 0
            }
                currentLetter = it
                currentCount++
        }

        if (currentLetter != null && currentCount !=0) {
            letterToCount.add(currentLetter!! to currentCount)
        }

        return letterToCount
                .map {
                    when {
                        (it.second == 1) -> it.first
                        else -> "${it.second}${it.first}"
                    }
                }.joinToString(separator = "")
    }

    fun decode(input: String): String {
        val letterToCount = mutableListOf<Pair<Char, Int>>()

        var currentCountText: String = ""
        input.forEach {
            if (it.isDigit()) {
                currentCountText += it
            } else if (it.isLetter() || it == ' ') {
                letterToCount.add(it to (currentCountText.toIntOrNull() ?: 1))
                currentCountText = ""
            }
        }

        return letterToCount.joinToString(separator = "") { "${it.first}".repeat(it.second) }
    }
}
