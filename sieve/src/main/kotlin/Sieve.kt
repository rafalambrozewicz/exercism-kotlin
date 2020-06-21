object Sieve {

    fun primesUpTo(limit: Int): List<Int> {
        var values = (2..limit).toMutableList()
        val result = mutableListOf<Int>()
        while (values.isNotEmpty()) {
            val prime = values[0]
            result.add(prime)
            values = values.filter { it % prime != 0 }.toMutableList()
        }

        return result
    }
}