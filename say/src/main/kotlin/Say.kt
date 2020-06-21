import java.lang.IllegalStateException

class NumberSpeller {

    fun say(input: Long): String {
        require(input in 0..999_999_999_999) { "Input ($input) not in required rage (<0;999 999 999 999>)" }
        return input.sayNumber()
    }

    private fun Long.sayNumber(): String {
        return when(this) {
            0L -> "zero"
            1L -> "one"
            2L -> "two"
            3L -> "three"
            4L -> "four"
            5L -> "five"
            6L -> "six"
            7L -> "seven"
            8L -> "eight"
            9L -> "nine"
            10L -> "ten"
            11L -> "eleven"
            12L -> "twelve"
            13L -> "thirteen"
            14L -> "fourteen"
            15L -> "fifteen"
            16L -> "sixteen"
            17L -> "seventeen"
            18L -> "eighteen"
            19L -> "nineteen"
            20L -> "twenty"
            in 21L .. 29L -> "twenty-${(this-20).sayNumber()}"
            30L -> "thirty"
            in 31L .. 39L -> "thirty-${(this-30).sayNumber()}"
            40L -> "forty"
            in 41L .. 49L -> "forty-${(this-40).sayNumber()}"
            50L -> "fifty"
            in 51L .. 59L -> "fifty-${(this-50).sayNumber()}"
            60L -> "sixty"
            in 61L .. 69L -> "sixty-${(this-60).sayNumber()}"
            70L -> "seventy"
            in 71L .. 79L -> "seventy-${(this-70).sayNumber()}"
            80L -> "eighty"
            in 81L .. 89L -> "eighty-${(this-80).sayNumber()}"
            90L -> "ninety"
            in 91L .. 99L -> "ninety-${(this-90).sayNumber()}"
            in 100L .. 999L -> {
                "${(this / 100).sayNumber()} hundred${if (this % 100 == 0L) "" else " " + (this % 100).sayNumber()}"
            }
            in 1_000L .. 999_999L -> {
                "${(this / 1_000).sayNumber()} thousand${if (this % 1_000 == 0L) "" else " " + (this % 1_000).sayNumber()}"
            }
            in 1_000_000L .. 999_999_999L -> {
                "${(this / 1_000_000).sayNumber()} million${if (this % 1_000_000 == 0L) "" else " " + (this % 1_000_000).sayNumber()}"
            }
            in 1_000_000_000L .. 999_999_999_999L -> {
                "${(this / 1_000_000_000).sayNumber()} billion${if (this % 1_000_000_000 == 0L) "" else " " + (this % 1_000_000_000).sayNumber()}"
            }
            else -> throw IllegalStateException("Number ($this) not in required rage (<0;999 999 999 999>)")
        }
    }
}
