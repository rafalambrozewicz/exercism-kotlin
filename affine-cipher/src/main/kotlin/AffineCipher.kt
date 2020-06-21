object AffineCipher {

    private const val m = 26

    fun encode(input: String, a: Int, b: Int): String {
        val (g, x ,y) = xgcd(a, m)
        if (g != 1) { throw IllegalArgumentException("Error: a and m must be coprime.") }

        return input.filter { it.isLetter() || it.isDigit() }
                .map {
                    if (it.isDigit()) {
                        it
                    } else {
                        val v = ((a*(it.toLowerCase() - 'a') + b) % m) + 'a'.toInt()
                        v.toChar()
                    }
                }
                .joinToString(separator = "")
                .chunked(5)
                .joinToString(separator = " ")
    }

    private fun xgcd(a: Int, b: Int): Triple<Int, Int, Int> {
        //  ax + by = gcd(a, b)
        var a = a
        var b = b
        val aa = intArrayOf(1, 0)
        val bb = intArrayOf(0, 1)
        var q: Int = 0
        while (true) {
            q = a / b
            a = a % b
            aa[0] = aa[0] - q * aa[1]
            bb[0] = bb[0] - q * bb[1]
            if (a == 0) {
                return Triple(b, aa[1], bb[1])
            }
            q = b / a
            b = b % a
            aa[1] = aa[1] - q * aa[0]
            bb[1] = bb[1] - q * bb[0]
            if (b == 0) {
                return Triple(a, aa[0], bb[0])
            }
        }
    }

    fun decode(input: String, a: Int, b: Int): String {
        val (g, x ,y) = xgcd(a, m)
        if (g != 1) { throw IllegalArgumentException("Error: a and m must be coprime.") }
        val positiveX = if (x<0) x + m else x

        return input.replace("\\s+".toRegex(), "")
                .map {
                    if (it.isDigit()) {
                        it
                    } else {
                        val letterNo  = (it.toLowerCase() - 'a')
                        val positiveLetterNumberMinusB = ((letterNo - b)  % m + m ) % m
                        val v = ((positiveX*positiveLetterNumberMinusB) % m) + 'a'.toInt()
                        v.toChar()
                    }
        }.joinToString(separator = "")
    }
}