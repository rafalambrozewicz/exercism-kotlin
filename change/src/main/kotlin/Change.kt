class ChangeCalculator(val denominations: List<Int>) {

    companion object {
        private const val INFINITE = Int.MAX_VALUE
        private const val ZERO_INDEX_COMPENSATION = 1
    }

    fun computeMostEfficientChange(grandTotal: Int): List<Int> {
        require(grandTotal >= 0) { "Negative totals are not allowed." }
        val sortedDenominations = denominations.sorted()

        val neededDenominations = mutableMapOf<Int, List<Int>>()
        neededDenominations[0] = listOf()
        val noOfNeededDenominations: MutableList<Int> = List(grandTotal + ZERO_INDEX_COMPENSATION) {
            when(it) {
                0 -> 0
                else -> INFINITE
            }
        }.toMutableList()

        for (denomination in sortedDenominations) {
            for (total in 0 .. (grandTotal - denomination)) {
                if (noOfNeededDenominations[total] < INFINITE &&
                        noOfNeededDenominations[total] + 1 < noOfNeededDenominations[total + denomination]) {
                    noOfNeededDenominations[total + denomination] = noOfNeededDenominations[total] + 1
                    neededDenominations[total + denomination] =
                            neededDenominations.getOrDefault(total, listOf()) + listOf(denomination)
                }
            }
        }

        return neededDenominations[grandTotal]
                ?: throw IllegalArgumentException("The total $grandTotal cannot be represented in the given currency.")
    }
}