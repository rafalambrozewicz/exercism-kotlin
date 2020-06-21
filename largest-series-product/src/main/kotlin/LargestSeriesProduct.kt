class Series(val series: String) {

    init { require(series.all { it.isDigit() }) }

    fun getLargestProduct(span: Int): Long {
        require(series.length >= span)
        val subSeries = series.split('0')
        return subSeries.map { it.largestProduct(span) }.max()?.toLong() ?: 0L
    }

    private fun String.largestProduct(length: Int): Int {
        return if (this.length < length) { 0 }
        else {
            val ints = this.toCharArray().map { it.toString().toInt() }
            val listOfInts = mutableListOf<List<Int>>()
            for (i in 0 .. ints.size-length) {
                listOfInts.add(ints.drop(i).take(length))
            }
            val products = listOfInts.map { it -> it.fold(1) { acc, element -> (acc * element)} }
            products.max() ?: 0
        }
    }
}