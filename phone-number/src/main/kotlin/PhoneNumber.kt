class PhoneNumber(aNumber: String) {

    private val normalizedNumber: String

    init {
        require(aNumber.all { "$it".matches("([0-9|\\.|\\-|\\+|\\s|\\(|\\)])".toRegex()) })
        val normalizedNumber = aNumber.filter { it.isDigit() }.let {
            if (it.length == 10) "1$it" else it
        }
        require(normalizedNumber.length == 11)
        require(normalizedNumber.matches("1[2-9]{1}[0-9]{2}[2-9]{1}[0-9]{6}".toRegex()))

        this.normalizedNumber =  normalizedNumber.removePrefix("1")
    }

    val number: String? = normalizedNumber
}
