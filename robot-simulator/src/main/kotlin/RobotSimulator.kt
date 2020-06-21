import java.lang.IllegalArgumentException

class Robot(
        gridPosition: GridPosition = GridPosition(x = 0, y = 0),
        orientation: Orientation = Orientation.NORTH
) {

    var gridPosition: GridPosition = gridPosition
        private set
    var orientation: Orientation = orientation
        private set

    fun simulate(instructions: String) {
        require(instructions.matches("[RLA]*".toRegex())) { "instructions contain illegal character(s)!" }
        instructions.toCharArray().forEach { go(it) }
    }

    private fun go(c: Char) {
        when (c) {
            'R' -> orientation = Orientation.values()[(orientation.ordinal + 1) % 4]
            'L' -> orientation = if (orientation.ordinal == 0) Orientation.values().last() else Orientation.values()[(orientation.ordinal - 1) % 4]
            'A' -> gridPosition = when (orientation) {
                Orientation.NORTH -> gridPosition.copy(y = gridPosition.y + 1)
                Orientation.EAST -> gridPosition.copy(x = gridPosition.x + 1)
                Orientation.SOUTH -> gridPosition.copy(y = gridPosition.y - 1)
                Orientation.WEST -> gridPosition.copy(x = gridPosition.x - 1)
            }
            else -> throw IllegalArgumentException("instructions contain illegal character of '$c'")
        }
    }
}
