import java.lang.IllegalArgumentException

class Triangle(private val fL: Double,
               private val sL: Double,
               private val tL: Double) {

    constructor( f: Int, s: Int, t:Int) : this(f.toDouble(), s.toDouble(), t.toDouble())

    init {
        if (fL==0.0 || sL==0.0 || tL==0.0) {
            throw IllegalArgumentException("Length needs to be more that 0")
        }

        if ((fL + sL < tL) || (sL + tL < fL) || (tL + fL < sL)) {
            throw IllegalArgumentException("Illegal lengths for triangle")
        }
    }

    val isScalene: Boolean
        get() = (fL != sL) && (fL != tL) && (sL != tL)

    val isIsosceles: Boolean
        get() = (fL == sL) || (fL == tL) || (sL == tL)

    val isEquilateral: Boolean
        get() = (fL == sL) && (sL == tL)

}