object SpiralMatrix {

    fun ofSize(size: Int): Array<IntArray> {
        val output = Array(size) { IntArray(size) { 0 } }
        fill(output, 1, 0 to 0, size)
        return output
    }

    private fun fill(input: Array<IntArray>,
                     startingValue: Int,
                     startingPoint: Pair<Int, Int>,
                     size: Int) {

        var value = startingValue

        for (x in startingPoint.first until startingPoint.first+size) {
            input[startingPoint.second][x] = value
            value++
        }

        if (startingPoint.second + 1 < startingPoint.second + size) {
            for (y in (startingPoint.second + 1) until (startingPoint.second + size)) {
                input[y][startingPoint.first+size-1] = value
                value++
            }

            for (x in (startingPoint.first+size-2) downTo startingPoint.first) {
                input[startingPoint.second + size - 1][x] = value
                value++
            }

            if (size - 2 > 0) {
                for (y in (startingPoint.second + size - 2) downTo (startingPoint.second + 1)) {
                    input[y][startingPoint.first] = value
                    value++
                }

                fill(input, value, (startingPoint.first + 1) to (startingPoint.second +1), size - 2)
            }
        }
    }
}
