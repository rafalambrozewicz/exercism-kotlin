import java.lang.IllegalArgumentException
import java.math.BigInteger

object Board {

    fun getGrainCountForSquare(n: Int): BigInteger {
        validate(n)
        return BigInteger.ONE shl (n-1)
    }

    private fun validate(n: Int) {
        if (n<1 || n>64) { throw IllegalArgumentException("Only integers between 1 and 64 (inclusive) are allowed") }
    }

    fun getTotalGrainCount(): BigInteger = (BigInteger.ONE shl 64) - BigInteger.ONE
}
