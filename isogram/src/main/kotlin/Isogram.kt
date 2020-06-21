object Isogram {

    fun isIsogram(input: String): Boolean {
        return input.toLowerCase()
                .replace(" ", "")
                .replace("-", "")
                .groupBy { it }
                .map { it.value.size }
                .all { it == 1 }
    }
}
