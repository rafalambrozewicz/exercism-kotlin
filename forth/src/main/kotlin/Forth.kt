class Forth {

    private val stack = mutableListOf<Int>()
    private val userDefinedWords = mutableMapOf<String,String>()

    fun evaluate(vararg line: String): List<Int> {
        for (l in line) {
            if (l.isUserDefinedWord()) {
                val wordToDefinition = l
                        .replace(": ", "")
                        .replace(" ;", "")
                        .split(" ")
                        .let {
                            val word = it.first()
                            if (word.toIntOrNull() != null) { throw Exception("illegal operation") }
                            var definitionText = it.drop(1).joinToString(" ")
                            userDefinedWords.forEach { (word, definition) ->
                                definitionText = definitionText.replace(word, definition) }

                            word.toLowerCase() to definitionText.toLowerCase()
                        }

                userDefinedWords[wordToDefinition.first] = wordToDefinition.second
            } else {
                var normalizedLine = l.toLowerCase()
                userDefinedWords.forEach { (word, definition) ->
                    normalizedLine = normalizedLine.replace(word, definition) }
                normalizedLine.split(" ").forEach { arg ->
                    when {
                        arg.toIntOrNull() != null -> stack.add(arg.toInt())
                        arg == "+" -> executeOperation { a1, a2 -> a1 + a2}
                        arg == "-" -> executeOperation { a1, a2 -> a1 - a2}
                        arg == "*" -> executeOperation { a1, a2 -> a1 * a2}
                        arg == "/" -> executeOperation { a1, a2 -> a1 / a2}
                        arg.toLowerCase() == "dup" -> manipulateStackTip { stack.add(stack.last()) }
                        arg.toLowerCase() == "drop" -> manipulateStackTip { stack.removeAt(stack.size-1) }
                        arg.toLowerCase() == "swap" -> manipulateStackLastTwo {
                            val preLastElement = stack[stack.size-2]
                            val lastElement = stack[stack.size-1]
                            stack[stack.size-2] = lastElement
                            stack[stack.size-1] = preLastElement
                        }
                        arg.toLowerCase() == "over" -> manipulateStackLastTwo{ stack.add(stack[stack.size-2]) }
                        else -> throw Exception("undefined operation")
                    }
                }
            }
        }

        return stack
    }

    private fun String.isUserDefinedWord(): Boolean = this.startsWith(":") && this.endsWith(";")

    private fun executeOperation(o: (preLastElement:Int, lastElement: Int) -> Int) {
        if (stack.size == 0) { throw Exception("empty stack") }
        if (stack.size == 1) { throw Exception("only one value on the stack") }

        val lastElement = stack[stack.size-1]
        stack.removeAt(stack.size-1)
        val preLastElement = stack[stack.size-1]
        stack.removeAt(stack.size-1)

        try {
            stack.add(o.invoke(preLastElement, lastElement))
        } catch (e: ArithmeticException) {
            if (lastElement == 0) {
                throw ArithmeticException("divide by zero")
            } else {
                throw e
            }
        }
    }

    private fun manipulateStackTip(o: () -> Unit) {
        if (stack.size == 0) { throw Exception("empty stack") }
        o.invoke()
    }

    private fun manipulateStackLastTwo(o: () -> Unit) {
        if (stack.size == 0) { throw Exception("empty stack") }
        if (stack.size == 1) { throw Exception("only one value on the stack") }
        o.invoke()
    }
}
