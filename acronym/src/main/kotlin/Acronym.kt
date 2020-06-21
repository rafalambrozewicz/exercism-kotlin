class Acronym {
    companion object {
        fun generate(text: String): String {
            return text.replace("[-_]".toRegex(), " ")
                    .replace("\\s+".toRegex(), " ")
                    .split(" ")
                    .fold("", {acronym, word -> acronym + word.get(0).toUpperCase() })
        }
    }
}