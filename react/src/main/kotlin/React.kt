import kotlin.properties.Delegates

class Reactor<T> {

    interface Subscription {
        fun cancel()
    }

    abstract inner class Cell<T>(initialValue: T) {
        var value: T by Delegates.observable(initialValue) { _, oldValue, newValue ->
            if (newValue != oldValue) { callbacks.forEach { it.invoke(newValue) } }
        }
        internal var callbacks: List< (T) -> Unit > = emptyList()

        fun addCallback(callback: (T) -> Unit): Subscription {
            callbacks = callbacks + callback
            return object : Subscription {
                override fun cancel() {
                    callbacks = callbacks - callback
                }
            }
        }
    }

    inner class InputCell<T>(initialValue: T) : Cell<T>(initialValue)

    inner class ComputeCell<T>(private vararg val inputCells: Cell<T>,
                               private val computeExpr: (List<T>) -> T) : Cell<T>(computeExpr(inputCells.map { it.value })) {
        init { populateCallbacks(inputCells.toList()) }

        private fun populateCallbacks(cells: List<Cell<T>>) {
            cells.forEach { c ->
                when (c) {
                    is Reactor<*>.ComputeCell<*> -> { populateCallbacks(c.inputCells.toList() as List<Cell<T>>) }
                    else -> c.addCallback { value = computeExpr(inputCells.map { it.value }) }
                }
            }
        }
    }
}