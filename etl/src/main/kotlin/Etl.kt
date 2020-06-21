object ETL {
    fun transform(source: Map<Int, Collection<Char>>): Map<Char, Int> {
        return source.flatMap { it.value.map { c -> c.toLowerCase() to it.key } }
                .sortedBy { it.first }
                .toMap()
    }
}
