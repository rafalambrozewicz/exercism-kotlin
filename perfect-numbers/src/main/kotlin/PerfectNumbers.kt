import java.lang.RuntimeException

enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

fun classify(naturalNumber: Int): Classification {
    if (naturalNumber < 1) { throw RuntimeException("Input not valid!")}

    val factorsSum = (1..(naturalNumber/2))
            .filter { naturalNumber % it == 0 }
            .sum()

    return when {
        factorsSum == naturalNumber -> Classification.PERFECT
        factorsSum > naturalNumber -> Classification.ABUNDANT
        factorsSum < naturalNumber -> Classification.DEFICIENT
        else -> throw RuntimeException("Impossible state!")
    }
}
