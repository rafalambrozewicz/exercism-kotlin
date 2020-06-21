object Prime {

    fun nth(n: Int): Int {
        require(n>0) { "There is no zeroth prime." }

        val primes = mutableListOf<Int>()
        var counter = 2
        while(primes.size < n) {
            if (!counter.isDivisible(primes)) { primes.add(counter) }
            counter++
        }

        return primes[n-1]
    }

    private fun Int.isDivisible(divisors: List<Int>): Boolean {
        divisors.forEach { if (this%it == 0) return true }
        return false
    }
}