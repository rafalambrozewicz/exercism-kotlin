import kotlin.math.pow
import kotlin.math.sqrt

object Darts {

    fun score(x: Number, y: Number): Int {
        val distanceFromTheCenter = sqrt( x.toDouble().pow(2) + y.toDouble().pow(2) )
        return when {
            (distanceFromTheCenter <= 1) -> 10
            (distanceFromTheCenter <= 5) -> 5
            (distanceFromTheCenter <= 10) -> 1
            else -> 0
        }
    }
}
