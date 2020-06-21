object ResistorColorDuo {

    fun value(vararg colors: Color): Int = colors.take(2).let { it[0].ordinal * 10 + it[1].ordinal }
}
