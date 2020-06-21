object Flattener {
    fun flatten(source: Collection<Any?>): List<Any> {
        val output = mutableListOf<Int>()
        flatten(output, source)

        return output
    }

    fun flatten(output: MutableCollection<Int>, input: Collection<Any?>) {
        input.forEach {
            if (it is Int) { output.add(it) }
            else if (it is Collection<Any?>) { flatten(output, it) }
        }
    }
}
