class Scale(private val tonic: String) {

    companion object {
        val CHROMATIC_SCALE_SHARPS = listOf("A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#")
        val CHROMATIC_SCALE_FLATS = listOf("A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab")

        val USE_NO_SHARPS_NOR_FLATS = listOf("C", "a")
        val USE_SHARPS = listOf("G", "D", "A", "E", "B", "F#", "e", "b", "f#", "c#", "g#", "d#")
        val USE_FLATS = listOf("F", "Bb", "Eb", "Ab", "Db", "Gb", "d", "g", "c", "f", "bb", "eb")

        val INTERVAL_LETTER_TO_VALUE = mapOf(
                'm' to 1,
                'M' to 2,
                'A' to 3)
    }

    fun chromatic(): List<String> {
        val scale = when {
            USE_NO_SHARPS_NOR_FLATS.contains(tonic) -> CHROMATIC_SCALE_SHARPS
            USE_SHARPS.contains(tonic) -> CHROMATIC_SCALE_SHARPS
            USE_FLATS.contains(tonic) -> CHROMATIC_SCALE_FLATS
            else -> throw Exception("Unsupported tonic of '$tonic'")
        }
        val indexOfTonic = scale.indexOf(tonic.capitalize())

        return scale.drop(indexOfTonic) + scale.take(indexOfTonic)
    }

    fun interval(intervals: String): List<String> {
        val intervalValuesIndexes = intervals
                .dropLast(1)
                .fold(listOf(0), { acc, v -> acc + listOf(acc.last() + (INTERVAL_LETTER_TO_VALUE[v] ?: 0)) })

        return chromatic().filterIndexed { index, _ -> intervalValuesIndexes.contains(index) }
    }
}
