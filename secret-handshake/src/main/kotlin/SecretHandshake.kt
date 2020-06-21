class HandshakeCalculator() {

    companion object {
        fun calculateHandshake(n: Int): List<Signal> {
            val handShakes = mutableListOf<Signal>()
            Signal.values().forEachIndexed {index, signal -> if(n.byteAt(index) == 1) handShakes.add(signal) }
            if (n.byteAt(4) == 1) handShakes.reverse()
            return handShakes
        }

        private fun Int.byteAt(index: Int): Int {
            return if ((this shr index) % 2 == 0) 0 else 1
        }
    }
}