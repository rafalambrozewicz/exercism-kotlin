import java.lang.Integer.max
import java.lang.Integer.min

data class MinesweeperBoard(val inputBoard: List<String>) {

    fun withNumbers(): List<String> {
        val board = inputBoard.map { it.toCharArray() }

        return board.mapIndexed { y, chars ->
            chars.mapIndexed { x, c ->
                when {
                    c == '*' -> '*'
                    countMines(board, x, y) > 0 -> "${countMines(board, x, y)}".toCharArray().first()
                    else -> ' '
                }
            }.joinToString(separator = "")
        }
    }

    private fun countMines(board: List<CharArray>, pointX: Int, pointY: Int): Int {
        val startY = max(pointY-1, 0)
        val endY = min(pointY+1, board.size - 1)
        val startX = max(pointX-1, 0)
        val endX = min(pointX+1, board.first().size - 1)

        var minesCount = 0
        (startY..endY).forEach { y ->
            (startX..endX).forEach { x ->
                if (board[y][x] == '*') { minesCount++ }
            }
        }

        return minesCount
    }
}
