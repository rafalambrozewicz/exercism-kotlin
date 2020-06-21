import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class Meetup(private val month: Int, val year: Int ) {

    fun day(dayOfWeek: DayOfWeek, schedule: MeetupSchedule): LocalDate{
        var startDate = LocalDate.of(year, month, 1)
        val endDateExclusive = startDate.plusMonths(1)

        val days = mutableListOf<LocalDate>().apply {
            while (startDate != endDateExclusive) {
                this.add(startDate)
                startDate = startDate.plusDays(1)
            }
        }.filter { it.dayOfWeek == dayOfWeek }

        return when (schedule) {
            MeetupSchedule.FIRST -> days.first()
            MeetupSchedule.SECOND -> days[1]
            MeetupSchedule.THIRD -> days[2]
            MeetupSchedule.FOURTH -> days[3]
            MeetupSchedule.LAST -> days.last()
            MeetupSchedule.TEENTH -> days.last { it.dayOfMonth in 10..19  }
        }
    }
}
