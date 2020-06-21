import kotlin.random.Random

object RobotName {
    private val usedNames = mutableSetOf<String>()

    fun nextName(): String {
        var name = generateName()
        while (usedNames.contains(name)) {
            name = generateName()
        }
        usedNames.add(name)
        return name
    }

    private fun generateName(): String {
        return  "${'A' + Random.nextInt(26)}" +
                "${'A' + Random.nextInt(26)}" +
                "${Random.nextInt(10)}" +
                "${Random.nextInt(10)}" +
                "${Random.nextInt(10)}"
    }
}

class Robot {
    var name: String = RobotName.nextName()
        private set

    fun reset() {
        name = RobotName.nextName()
    }
}
