fun String.parseBinToLong(): Long = this.toLong(2)
fun String.parseBinToInt(startIndex: Int, length: Int): Int =
    this.substring(startIndex, startIndex + length).parseBinToInt()

fun String.parseBinToInt(): Int = this.toInt(2)

object Day16 {


    fun part1(input: String): Int = State(s = hexToBinString(input)).parsePackets().versionSum

    fun part2(input: String): Long = State(s = hexToBinString(input)).parsePackets().values.firstOrNull() ?: 0

    fun hexToBinString(l: String): String = l.fold("") { r, c ->
        var b = Integer.toBinaryString(c.digitToInt(16))
        while (b.length < 4) b = "0$b"
        r + b
    }

    private fun onlyZerosFrom(s: String, startIndex: Int): Boolean {
        for (i in startIndex until s.length)
            if (s[i] == '1') return false
        return true
    }

    data class State(
        val s: String,
        val versionSum: Int = 0,
        val startIndex: Int = 0,
        val values: List<Long> = listOf()
    ) {
        fun parsePackets(): State {
            var state = this
            do {
                state = state.parsePacket()
            } while (state.startIndex < state.s.length && !onlyZerosFrom(state.s, state.startIndex))
            return state
        }

        private fun parsePacket(): State {
            val v = s.parseBinToInt(startIndex, 3)
            val id = s.parseBinToInt(startIndex + 3, 3)
            return if (id == 4) {
                parseNumberPacket()
            } else {
                parseOperator(id)
            }.let { it.copy(versionSum = it.versionSum + v) }
        }


        private fun parseOperator(id: Int): State {
            return if (s[startIndex + 6] == '0') {
                val l = s.parseBinToInt(startIndex + 7, 15)
                val subString = s.substring(startIndex + 7 + 15, startIndex + 7 + 15 + l)
                val subState = State(s = subString).parsePackets()
                copy(
                    startIndex = startIndex + 7 + 15 + l,
                    versionSum = versionSum + subState.versionSum,
                    values = values + listOf(calcValue(id, subState.values))
                )
            } else {
                val l = s.parseBinToInt(startIndex + 7, 11)
                var subState = State(s, startIndex = startIndex + 7 + 11)
                for (i in 0 until l) {
                    subState = subState.parsePacket()
                }

                copy(
                    versionSum = versionSum + subState.versionSum,
                    startIndex = subState.startIndex,
                    values = values + listOf(calcValue(id, subState.values))
                )
            }
        }

        private fun parseNumberPacket(): State {
            var i = startIndex + 6
            var n = ""
            do {
                val stop = s[i] == '0'
                n += s.substring(i + 1, i + 5)
                i += 5
            } while (!stop)
            return copy(startIndex = i, values = values + n.parseBinToLong())
        }

        private fun calcValue(id: Int, values: List<Long>): Long {
            if (id == 0) return values.sum()
            if (id == 1) return values.fold(1L) { r, v -> r * v }
            if (id == 2) return values.minOf { it }
            if (id == 3) return values.maxOf { it }
            if (id == 5) return if (values[0] > values[1]) 1L else 0L
            if (id == 6) return if (values[0] < values[1]) 1L else 0L
            if (id == 7) return if (values[0] == values[1]) 1L else 0L
            require(false)
            return 0
        }

    }


}