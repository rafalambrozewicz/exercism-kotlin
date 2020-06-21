object PascalsTriangle {

    fun computeTriangle(rows: Int): List<List<Int>> {
        require(rows >= 0) { "Rows cannot be negative" }
        if (rows == 0) { return emptyList() }

        val result = List(rows) { mutableListOf<Int>() }
        result[0].add(1)
        for (row in 1 until rows) {
            for(i in 0..row) {
                val v = result[row-1].getOrElse(i-1) { 0 } + result[row-1].getOrElse(i) { 0 }
                result[row].add(v)
            }
        }

        return result
    }
}