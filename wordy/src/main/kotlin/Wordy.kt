import java.lang.UnsupportedOperationException
import kotlin.math.pow

object Wordy {

    fun answer(input: String): Int {
        val normalizedQuestion = input
                .replace("What is ", "")
                .replace("?", "")
                .replace("plus", "+")
                .replace("minus", "-")
                .replace("multiplied by", "*")
                .replace("divided by", "/")
                .replace("raised to the (\\d+)th power".toRegex()) { seq ->
                    "^ ${seq.value.filter { it.isDigit() || it == '-' } }" }
                .split(" ")

        var acc: Int? = null
        var operationType: String? = null
        normalizedQuestion.forEach {
            when {
                (acc == null) -> acc = it.toInt()
                (operationType == null) -> operationType = it
                (operationType != null) -> {
                    acc = when(operationType) {
                        "+" -> acc?.plus(it.toInt())
                        "-" -> acc?.minus(it.toInt())
                        "*" -> acc?.times(it.toInt())
                        "/" -> acc?.div(it.toInt())
                        "^" -> acc?.toDouble()?.pow(it.toInt())?.toInt()
                        else -> throw UnsupportedOperationException("Operation of '$operationType' is not supported!")
                    }
                operationType = null
                }
                else -> throw Exception("Question invalidly formatted")
            }
        }

        if (operationType != null) {
            throw Exception("Question invalidly formatted")
        }

        return acc ?: throw Exception("Question invalidly formatted")
    }
}