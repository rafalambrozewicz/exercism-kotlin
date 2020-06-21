class Raindrops {
    companion object {
        fun convert(number: Int): String {
            var result = ""
            if (number % 3 == 0) result = result + "Pling"
            if (number % 5 == 0) result = result + "Plang"
            if (number % 7 == 0) result = result + "Plong"
            if (result.isEmpty()) result = number.toString()
            return result
        }
    }
}