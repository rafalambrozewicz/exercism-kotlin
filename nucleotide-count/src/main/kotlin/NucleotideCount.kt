class Dna(private val nucleotides: String) {

    companion object {
        private val NUCLEOTIDES_SYMBOLS = listOf('A', 'C', 'G', 'T')
    }

    init {
        require(nucleotides.all { NUCLEOTIDES_SYMBOLS.contains(it) }) { "Provided unknown nucleotide symbol" }
    }

    val nucleotideCounts: Map<Char, Int>
        get() {
            val groupedNucleotides = nucleotides.groupBy { it }
            return mapOf(
                    'A' to (groupedNucleotides['A']?.count() ?: 0),
                    'C' to (groupedNucleotides['C']?.count() ?: 0),
                    'G' to (groupedNucleotides['G']?.count() ?: 0),
                    'T' to (groupedNucleotides['T']?.count() ?: 0)
            )
        }
}
