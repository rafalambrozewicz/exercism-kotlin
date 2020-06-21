import kotlin.math.pow

object ResistorColorTrio {

    fun text(vararg input: Color): String {
        val rawValue = (input[0].ordinal * 10 + input[1].ordinal) * 10.0.pow(input[2].ordinal).toInt()
        val unitIndex = rawValue.toString().takeLastWhile { it == '0' }.length / 3
        val value = (rawValue / 10.0.pow(unitIndex * 3)).toInt()

        return "$value ${Unit.values()[unitIndex].name.toLowerCase()}"
    }
}
