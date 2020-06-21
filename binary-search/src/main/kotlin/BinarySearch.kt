import java.lang.Integer.max
import java.lang.Integer.min

object BinarySearch {
    fun search(list: List<Int>, item: Int): Int {
        if (list.isEmpty()) { throw NoSuchElementException("$item not found!") }
        return binarySearch(list, item, 0, list.size -1)
    }

    private fun binarySearch(list: List<Int>, item: Int, startIndex: Int, endIndex: Int): Int {
        val index = (startIndex + endIndex) / 2
        val foundItem = list[index]

        if (startIndex == endIndex && foundItem != item) { throw NoSuchElementException("$item not found!") }

        return if (foundItem == item) {
            index
        } else {
            if (foundItem > item) {
                binarySearch(list, item, startIndex, max(index -1, 0) )
            } else {
                binarySearch(list, item, min(index + 1, list.size - 1), endIndex)
            }
        }
    }
}
