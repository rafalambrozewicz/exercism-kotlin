class Squares(val n: Long) {

    fun difference(): Long {
        return squareOfSum() - sumOfSquares()
    }

    fun squareOfSum(): Long {
        return (n * (n+1) / 2) * (n * (n+1) / 2)
    }

    fun sumOfSquares(): Long {
        return n * (n+1) * (2*n+1) / 6
    }
}