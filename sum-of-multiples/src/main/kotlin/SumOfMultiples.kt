object SumOfMultiples {

    fun sum(divisors: Set<Int>, limit: Int) = (1 until limit).filter { n ->
        n.isDivisible(divisors.filter { d -> d > 0 }.toSet()) }.sum()

    private fun Int.isDivisible(divisors: Set<Int>): Boolean = divisors.any { this % it == 0 }
}