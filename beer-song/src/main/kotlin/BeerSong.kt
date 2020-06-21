object BeerSong {
    fun verses(startBottles: Int, takeDown: Int): String {
        require(startBottles >= takeDown) {
            "startBottles=$startBottles needs to be more or equal to takeDown=$takeDown !"  }
        require(startBottles in 0..99 && takeDown in 0..99) {
            "startBottles=$startBottles or takeDown=$takeDown not in required range of <0;99> !" }

        return (startBottles downTo takeDown).map {
            when (it) {
                in 3..99 -> "$it bottles of beer on the wall, $it bottles of beer.\nTake one down and pass it around, ${it-1} bottles of beer on the wall.\n"
                2 -> "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
                1 -> "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
                0 -> "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
                else -> IllegalStateException("startBottles/takeDown not in <0;99> range!")
            }
        }.joinToString(separator = "\n")
    }
}
