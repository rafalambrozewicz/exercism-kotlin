class Hamming {
    companion object {
        fun compute(left: String, right: String): Int {
            if (left.length != right.length) { throw IllegalArgumentException("left and right strands must be of equal length.") }

            var differencesCount = 0
            left.forEachIndexed { index, c -> if (!c.equals(right[index])) differencesCount++ }

            return differencesCount
        }
    }
}