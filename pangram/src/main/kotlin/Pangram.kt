class Pangram {

    companion object {
        fun isPangram(text: String): Boolean {
            for (letter in 'a'..'z') {
                if (!text.contains(letter, ignoreCase = true)) return false
            }
            return true;
        }
    }
}