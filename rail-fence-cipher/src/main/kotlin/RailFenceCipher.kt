class RailFenceCipher(private val fenceHeight: Int) {

    private val offsets: List<Pair<Int,Int>> = (0 until fenceHeight).map { indexNo ->
        val maxOffset = (2 * fenceHeight)-2
        when {
            (indexNo == 0) ->
                Pair(maxOffset, maxOffset)
            (indexNo == fenceHeight -1) ->
                Pair(maxOffset, maxOffset)
            else ->
                Pair(maxOffset - 2 * indexNo, 2 * indexNo)
        }
    }

    init {
        require(fenceHeight>=2) { "Invalid fenceHeight of $fenceHeight (not >= 2)" }
    }

    fun getEncryptedData(input: String): String {
        return generateIndexes(input.length)
                .flatten()
                .map { input[it] }
                .joinToString(separator = "")
    }

    private fun generateIndexes(wordLength: Int): List<List<Int>> {
        return offsets.mapIndexed {initialOffset, offsets ->
            val indexesRow = mutableListOf<Int>()
            indexesRow.add(initialOffset)

            var i = 0
            do {
                val currentOffset = if (i % 2 == 0) offsets.first else offsets.second
                indexesRow.add(indexesRow.last() + currentOffset)
                i++
            } while (indexesRow.last() <= wordLength - 1)

            indexesRow.remove(indexesRow.last())
            indexesRow
        }
    }

    fun getDecryptedData(input: String): String {
        val indexes = generateIndexes(input.length).flatten()

        val output = MutableList(input.length) { ' ' }
        input.forEachIndexed { index, c -> output[indexes[index]] = c  }

        return output.joinToString(separator = "")
    }
}
