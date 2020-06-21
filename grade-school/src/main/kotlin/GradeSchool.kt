import java.util.*

class School {

    private val gradeToStudents = sortedMapOf<Int, SortedSet<String>>()

    fun add(student: String, grade: Int) {
        val students = gradeToStudents.getOrElse(grade) { sortedSetOf() }
        gradeToStudents[grade] = (students + student).toSortedSet()
    }

    fun grade(grade: Int): List<String> = gradeToStudents.getOrElse(grade) { sortedSetOf() }.toList()

    fun roster(): List<String> = gradeToStudents.values.flatten()
}