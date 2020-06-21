import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

data class ComplexNumber(val real: Double = 0.0, val imag: Double = 0.0) {

    val abs: Double = sqrt(real.pow(2) + imag.pow(2))

    fun conjugate(): ComplexNumber {
        return ComplexNumber(real, imag * -1)
    }

    operator fun plus(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(real + other.real, imag + other.imag)
    }

    operator fun minus(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(real - other.real, imag - other.imag)
    }

    operator fun times(other: ComplexNumber): ComplexNumber {
        return ComplexNumber((real * other.real - imag * other.imag), (imag * other.real + real * other.imag))
    }

    operator fun div(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(((real * other.real + imag * other.imag) / (other.real * other.real + other.imag * other.imag)),
                ((imag * other.real - real * other.imag) / (other.real * other.real + other.imag * other.imag)))
    }
}

fun exponential(cn: ComplexNumber): ComplexNumber {
    return if (cn.real == 0.0)
        ComplexNumber(cos(cn.imag), sin(cn.imag))
    else
        ComplexNumber(Math.E.pow(cn.real)) * exponential(ComplexNumber(imag=cn.imag))
}
