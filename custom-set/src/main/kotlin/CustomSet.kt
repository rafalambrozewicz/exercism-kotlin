class CustomSet(vararg elements: Int) {

    private val elements = elements.toMutableSet()

    fun isEmpty(): Boolean = elements.isEmpty()

    fun isSubset(other: CustomSet): Boolean = this.elements.all { other.elements.contains(it) }

    fun isDisjoint(other: CustomSet): Boolean = this.elements.all { !other.elements.contains(it) }

    fun contains(other: Int): Boolean = elements.contains(other)

    fun intersection(other: CustomSet): CustomSet {
        val newElements = elements.filter { other.contains(it) }
        return CustomSet(*newElements.toIntArray())
    }

    fun add(other: Int) {
        elements.add(other)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is CustomSet) { return false }

        return elements == other.elements
    }

    operator fun plus(other: CustomSet): CustomSet {
        elements.addAll(other.elements)
       return this
    }

    operator fun minus(other: CustomSet): CustomSet {
        elements.removeAll(other.elements)
        return this
    }
}
