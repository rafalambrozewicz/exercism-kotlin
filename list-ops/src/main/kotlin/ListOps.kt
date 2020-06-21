fun <T> List<T>.customAppend(list: List<T>): List<T> {
    val result = mutableListOf<T>()

    for (e in this) {
        result.add(e)
    }

    for (e in list) {
        result.add(e)
    }

    return result
}

fun List<Any>.customConcat(): List<Any> {
    val result = mutableListOf<Any>()
    for (e in this) {
        if (e is List<*>) {
            result.addAll(e.filterNotNull().customConcat())
        } else {
            result.add(e)
        }
    }
    return result
}

fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    for (e in this) {
        if (predicate.invoke(e)) {
            result.add(e)
        }
    }
    return result
}

val List<Any>.customSize: Int get() = this.size

fun <T, U> List<T>.customMap(transform: (T) -> U): List<U> {
    val result = mutableListOf<U>()
    for (e in this) {
        result.add(transform.invoke(e))
    }
    return result
}

fun <T, U> List<T>.customFoldLeft(initial: U, f: (U, T) -> U): U {
    var current = initial
    this.forEach { current = f.invoke(current, it) }
    return current
}

fun <T, U> List<T>.customFoldRight(initial: U, f: (T, U) -> U): U {
    var current = initial
    this.customReverse().forEach { current = f.invoke(it, current) }
    return current
}

fun <T> List<T>.customReverse(): List<T> {
    if (this.isEmpty()) { return emptyList() }
    val result = mutableListOf<T>()
    for (i in (this.size - 1)  downTo 0) {
        result.add(this[i])
    }
    return result
}
