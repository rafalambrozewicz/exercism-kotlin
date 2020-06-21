class Clock(hours: Int, minutes: Int) {

    private var allMinutes: Int =  makePositive(hours * 60 + minutes)

    var hours: Int = (allMinutes / 60) % 24
        private set
        get() = (allMinutes / 60) % 24

    var minutes: Int = allMinutes % 60
        private set
        get() = allMinutes % 60

    private fun makePositive(minutes: Int): Int {
        var m = minutes
        while (m < 0) { m += 24 * 60 }

        return m
    }

    fun add(minutes: Int) {
        allMinutes = makePositive(allMinutes + minutes)
    }

    fun subtract(minutes: Int) {
        allMinutes = makePositive(allMinutes - minutes)
    }
    
    override fun toString(): String {
        return "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Clock

        if (hours != other.hours || minutes != other.minutes) return false

        return true
    }

    override fun hashCode(): Int {
        return hours * 60 + minutes
    }
}
