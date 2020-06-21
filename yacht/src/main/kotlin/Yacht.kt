object Yacht {

    fun solve(category: YachtCategory, vararg dices: Int): Int = when(category) {
        YachtCategory.YACHT -> if (dices.groupBy { it }.size == 1) 50 else 0
        YachtCategory.ONES -> dices.filter { it == 1 }.count()
        YachtCategory.TWOS -> dices.filter { it == 2 }.count() * 2
        YachtCategory.THREES -> dices.filter { it == 3 }.count() * 3
        YachtCategory.FOURS -> dices.filter { it == 4 }.count() * 4
        YachtCategory.FIVES -> dices.filter { it == 5 }.count() * 5
        YachtCategory.SIXES -> dices.filter { it == 6 }.count() * 6
        YachtCategory.FULL_HOUSE -> {
            val countToValue = dices
                    .groupBy { it }
                    .map { it.value.count() to it.key }
                    .toMap()
                    .toSortedMap()
                    .entries

            if (countToValue.count() == 2 &&
                    countToValue.elementAt(0).key == 2 &&
                    countToValue.elementAt(1).key == 3) {
                dices.sum()
            } else {
                0
            }
        }
        YachtCategory.FOUR_OF_A_KIND -> {
            val biggestCountToValue = dices
                    .groupBy { it }
                    .map { it.value.count() to it.key }
                    .toMap()
                    .toSortedMap()
                    .entries
                    .last()

            if (biggestCountToValue.key >= 4) {
                4 * biggestCountToValue.value
            } else {
                0
            }
        }
        YachtCategory.LITTLE_STRAIGHT -> if (dices.sorted() == listOf(1, 2, 3, 4, 5)) 30 else 0
        YachtCategory.BIG_STRAIGHT -> if (dices.sorted() == listOf(2, 3, 4, 5, 6)) 30 else 0
        YachtCategory.CHOICE -> dices.sum()
    }
}
