object CollatzCalculator {
    fun computeStepCount(number: Int): Int {
        if (number < 1) { throw IllegalArgumentException("Only natural numbers are allowed") }

        var numberOfSteps = 0
        var n = number

        while (n!=1) {
            n = if (n%2==0) { n/2 } else { 3*n+1 }

            numberOfSteps++
        }

        return numberOfSteps
    }
}