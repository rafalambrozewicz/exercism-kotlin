object Series {

    fun slices(n: Int, s: String): List<List<Int>> {
        require(n <= s.length) { "Slice size cannot be grater than input string length" }
        require(n > 0) { "Slice size cannot be less or equal zero!" }
        require(s.isNotBlank()) { "Input string cannot be blank!" }

        return s.map { it - '0' }.windowed(n)
    }
}