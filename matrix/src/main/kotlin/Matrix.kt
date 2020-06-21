class Matrix(matrixAsString: String) {

    private val matrix = matrixAsString.lines()
            .map { l -> l.trim()
                    .split("\\s+".toRegex())
                    .map { n -> n.toInt() } }

    fun column(colNr: Int): List<Int> = matrix.map { c -> c[colNr-1] }

    fun row(rowNr: Int): List<Int> = matrix[rowNr-1]
}
