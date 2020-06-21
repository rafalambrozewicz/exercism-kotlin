
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.IsoFields
import java.time.temporal.Temporal
import java.time.temporal.TemporalUnit


class Gigasecond(date: LocalDateTime) {

    constructor(date: LocalDate) : this(date.atStartOfDay())

    companion object {
        const val GIGASECOND_IN_SECONDS = 1000000000L
    }

    val date: LocalDateTime

    init {
        this.date = date.plusSeconds(GIGASECOND_IN_SECONDS)
    }
}