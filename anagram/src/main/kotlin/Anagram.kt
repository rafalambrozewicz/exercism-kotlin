class Anagram(private val source: String) {

    private val sourceSortedLettersMap = source.toLowerCase().groupBy { it }.toSortedMap()

    fun match(anagrams: Collection<String>): Set<String> {
        return anagrams.filter {
            (it.toLowerCase().groupBy { it }.toSortedMap() == sourceSortedLettersMap) &&
                    (it.toLowerCase() != source.toLowerCase())
        }.toSet()
    }
}
