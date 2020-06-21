class ChainNotFoundException(msg: String) : RuntimeException(msg)

data class Domino(val left: Int, val right: Int) {
    fun matchingOrNull(d: Domino): Domino? {
        if (d.right == left) { return this }
        if (d.right == right) { return Domino(right, left) }
        return null
    }

    fun hasSameRightAndLeft(): Boolean = left == right
}

object Dominoes {

    fun formChain(vararg inputDominoes: Domino): List<Domino> = formChain(inputDominoes.toList())

    fun formChain(inputDominoes: List<Domino>): List<Domino> {
        if (inputDominoes.isEmpty()) { return emptyList() }
        if (inputDominoes.size == 1) {
            return if (inputDominoes.first().hasSameRightAndLeft()) inputDominoes else throw ChainNotFoundException("can't be chained")
        }

        val chain = mutableListOf(inputDominoes.first())
        val elementsToUse = inputDominoes.drop(1).toMutableList()
        val usedDominoes = List(inputDominoes.size) { mutableListOf<Domino>() }

        do {
            val currentIndex = chain.size
            val addedToChain = elementsToUse.firstOrNull {
                val maybeMatching = it.matchingOrNull(chain.last())
                val notUsedBefore = !usedDominoes[currentIndex].contains(maybeMatching)
                val matchesFirstIfLast = if (elementsToUse.size == 1) maybeMatching?.right == chain.first().left else true
                (maybeMatching != null) && notUsedBefore && matchesFirstIfLast
            }?.let {
                elementsToUse.remove(it)
                val valid = it.matchingOrNull(chain.last())!!
                usedDominoes[currentIndex].add(valid)
                chain.add(valid)
                true
            } ?: false

            if (!addedToChain) {
                when {
                    chain.size > 1 -> {
                        elementsToUse.add(chain.last())
                        chain.removeAt(chain.size -1)
                    }
                    else -> { throw  ChainNotFoundException("can't be chained") }
                }
            }
        } while (elementsToUse.isNotEmpty())

        return chain
    }
}
