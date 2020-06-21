class SpaceAge(private val seconds: Long) {

    companion object {
        const val EARTH_ORBITAL_PERIOD_SECONDS = 31557600.0
        const val MERCURY_ORBITAL_PERIOD_SECONDS = EARTH_ORBITAL_PERIOD_SECONDS * 0.2408467
        const val VENUS_ORBITAL_PERIOD_SECONDS = EARTH_ORBITAL_PERIOD_SECONDS * 0.61519726
        const val MARS_ORBITAL_PERIOD_SECONDS = EARTH_ORBITAL_PERIOD_SECONDS * 1.8808158
        const val JUPITER_ORBITAL_PERIOD_SECONDS = EARTH_ORBITAL_PERIOD_SECONDS * 11.862615
        const val SATURN_ORBITAL_PERIOD_SECONDS = EARTH_ORBITAL_PERIOD_SECONDS * 29.447498
        const val URANUS_ORBITAL_PERIOD_SECONDS = EARTH_ORBITAL_PERIOD_SECONDS * 84.016846
        const val NEPTUNE_ORBITAL_PERIOD_SECONDS = EARTH_ORBITAL_PERIOD_SECONDS * 164.79132
    }

    fun onEarth() = (seconds / EARTH_ORBITAL_PERIOD_SECONDS).round(2)

    fun onMercury() = (seconds / MERCURY_ORBITAL_PERIOD_SECONDS).round(2)

    fun onVenus() = (seconds / VENUS_ORBITAL_PERIOD_SECONDS).round(2)

    fun onMars() = (seconds / MARS_ORBITAL_PERIOD_SECONDS).round(2)

    fun onJupiter() = (seconds / JUPITER_ORBITAL_PERIOD_SECONDS).round(2)

    fun onSaturn() = (seconds / SATURN_ORBITAL_PERIOD_SECONDS).round(2)

    fun onUranus() = (seconds / URANUS_ORBITAL_PERIOD_SECONDS).round(2)

    fun onNeptune() = (seconds / NEPTUNE_ORBITAL_PERIOD_SECONDS).round(2)

    private fun Double.round(numDecimalPlaces: Int): Double {
        val factor = Math.pow(10.0, numDecimalPlaces.toDouble())
        return Math.round(this * factor) / factor
    }
}