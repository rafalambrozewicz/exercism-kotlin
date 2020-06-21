class KindergartenGarden(private val diagram: String) {

    private val studentNameToRowNumber = mapOf(
            "Alice" to 0,
            "Bob" to 1,
            "Charlie" to 2,
            "David" to 3,
            "Eve" to 4,
            "Fred" to 5,
            "Ginny" to 6,
            "Harriet" to 7,
            "Ileana" to 8,
            "Joseph" to 9,
            "Kincaid" to 10,
            "Larry" to 11)

    private val letterToPlantName = mapOf(
            'G' to "grass",
            'C' to "clover",
            'R' to "radishes",
            'V' to "violets")

    fun getPlantsOfStudent(student: String): List<String> {
        val plantsIndexes = listOf(
                (studentNameToRowNumber[student]!! * 2),
                ((studentNameToRowNumber[student]!! * 2) + 1))

        return diagram.lines()
                .map { listOf(it[plantsIndexes[0]], it[plantsIndexes[1]]) }
                .flatten()
                .map { letterToPlantName[it].toString() }
    }
}
