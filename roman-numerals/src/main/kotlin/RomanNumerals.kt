object RomanNumerals {

    private val mapping = mapOf(
            0 to mapOf(1 to "I", 2 to "II", 3 to "III", 4 to "IV", 5 to "V", 6 to "VI", 7 to "VII", 8 to "VIII", 9 to "IX"),
            1 to mapOf(1 to "X", 2 to "XX", 3 to "XXX", 4 to "XL", 5 to "L", 6 to "LX", 7 to "LXX", 8 to "LXXX", 9 to "XC"),
            2 to mapOf(1 to "C", 2 to "CC", 3 to "CCC", 4 to "CD", 5 to "D", 6 to "DC", 7 to "DCC", 8 to "DCCC", 9 to "CM"),
            3 to mapOf(1 to "M", 2 to "MM", 3 to "MMM")
    )

    fun value(n: Int): String {
        require(n in 1..3999) { "Input ($n) not in required range of <1;3999>" }

        return n.toString()
                .reversed()
                .mapIndexed { i, c ->
                    mapping[i]?.getOrDefault(c - '0', defaultValue = "") ?: ""
                }.reversed()
                .joinToString(separator = "")
    }
}