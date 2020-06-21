import java.util.*

class BinarySearchTree<T : Comparable<T>> {

    data class Node<T>(val data: T,
                       var left: Node<T>? = null,
                       var right: Node<T>? = null) {

        fun insert(dataToInsert: T) {
            when  {
                (dataToInsert as Comparable<T>) <= (data) ->
                    if (left == null) left = Node(dataToInsert)
                    else left?.insert(dataToInsert)
                (dataToInsert as Comparable<T>) > (data) ->
                    if (right == null) right = Node(dataToInsert)
                    else right?.insert(dataToInsert)
            }
        }

        fun unfold(): List<T> {
            return (this.left?.unfold() ?: emptyList()) + listOf(this.data) + (this.right?.unfold() ?: emptyList())
        }
    }

    var root: Node<T>? = null

    fun insert(value: T) {
        if (root == null) {
            root = Node(value)
        } else {
            root?.insert(value)
        }
    }

    fun asSortedList(): List<T> {
        return root?.unfold() ?: emptyList()
    }

    fun asLevelOrderList(): List<T> {
        val results = sortedMapOf<Int, List<T>>()
        level(results, root, 0)
        return results.values.flatten()
    }

    private fun level(results: SortedMap<Int, List<T>>, node: Node<T>?, level: Int) {
        if (node == null) { return }
        results[level] = results.getOrDefault(level, emptyList()) + listOf(node.data)
        node.left?.run { level(results,this, level + 1)  }
        node.right?.run { level(results,this, level + 1)  }
    }
}