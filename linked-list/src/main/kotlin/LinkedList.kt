class Deque<T> {

    private class Node<T>(val value: T,
                          var prevNode: Node<T>? = null,
                          var nextNode: Node<T>? = null)

    private var head: Node<T>? = null

    private fun findLatestNode(): Node<T>? {
        var latestNode = head
        while (latestNode?.nextNode != null) { latestNode = latestNode.nextNode }
        return latestNode
    }

    fun push(value: T) {
        val latestNode = findLatestNode()
        if (latestNode == null) {
            head = Node(value)
        } else {
            val nodeToAdd = Node(value, prevNode = latestNode)
            latestNode?.nextNode = nodeToAdd
        }
    }

    fun pop(): T? {
        val latestNode = findLatestNode()
        val preLatestNode = latestNode?.prevNode

        preLatestNode?.nextNode = null
        latestNode?.prevNode = null
        latestNode?.nextNode = null

        return latestNode?.value
    }

    fun unshift(value: T) {
        val nodeToAdd = Node(value, nextNode = head)
        head?.prevNode = nodeToAdd
        head = nodeToAdd
    }

    fun shift(): T? {
        val oldHead = head
        val newHead = head?.nextNode

        head = newHead
        newHead?.prevNode = null
        oldHead?.prevNode = null
        oldHead?.nextNode = null

        return oldHead?.value
    }
}
