data class MatrixCoordinate(val row: Int, val col: Int)

data class Matrix(private val rows: List<List<Int>>) {

    companion object {
        private const val INDEX_OFFSET = 1
    }

    val saddlePoints: Set<MatrixCoordinate>
        get() {
            if (rows.first().isEmpty()) { return  emptySet() }

            val maxForRow = rows.map { it.max()!! }

            val minForColumn = (rows[0].indices).map { i -> rows.map { it[i] } } .map { it.min()!! }

            val sp = mutableSetOf<MatrixCoordinate>()
            for (y in rows.indices) {
                for (x in rows[y].indices) {
                    if (rows[y][x] >= maxForRow[y] && rows[y][x] <= minForColumn[x]) {
                        sp.add(MatrixCoordinate(row = y + INDEX_OFFSET, col = x + INDEX_OFFSET))
                    }
                }
            }

            return sp
        }
}
