enum class Relationship {
    EQUAL,
    SUBLIST,
    SUPERLIST,
    UNEQUAL
}

fun <E> List<E>.relationshipTo(list: List<E>): Relationship {
    return when {
        this.isEmpty() && list.isNotEmpty() -> Relationship.SUBLIST
        this.isNotEmpty() && list.isEmpty() -> Relationship.SUPERLIST
        this.size < list.size -> if (list.windowed(this.size).any { it == this }) Relationship.SUBLIST else Relationship.UNEQUAL
        this.size == list.size -> if (this == list) Relationship.EQUAL else Relationship.UNEQUAL
        this.size > list.size -> if (this.windowed(list.size).any { it == list }) Relationship.SUPERLIST else Relationship.UNEQUAL
        else -> throw IllegalStateException()
    }
}
