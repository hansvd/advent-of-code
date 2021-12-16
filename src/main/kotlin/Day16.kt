fun String.parseBinToLong():Long = this.toLong(2)
fun String.parseBinToInt(startIndex:Int, length:Int):Int =
    this.substring(startIndex,startIndex+length).parseBinToInt()
fun String.parseBinToInt():Int = this.toInt(2)

class Day16 {

    data class State (val versionSum:Int=0, val startIndex:Int=0, val values:List<Long> = listOf())
    companion object {
        fun part1(input:String):Int {
            val bin = hexToBinString(input)
            val day16 =Day16()
            return day16.parsePackets(bin,State()).versionSum
        }
        fun part2(input:String):Long {
            val bin = hexToBinString(input)
            val day16 =Day16()
            return day16.parsePackets(bin,State()).values.firstOrNull() ?: 0
        }
        fun hexToBinString(l:String):String = l.fold("") { r, c ->
            var b =Integer.toBinaryString(c.digitToInt(16))
            while(b.length < 4) b = "0$b"
            r + b
        }
        private fun onlyZerosFrom(s:String, startIndex: Int):Boolean {
            for (i in startIndex until s.length)
                if (s[i]=='1') return false
            return true
        }
    }

    fun parsePackets(s:String, stateIn:State):State {
        var state = stateIn
        do {
            state = parsePacket(s,state)
        } while (state.startIndex < s.length && !onlyZerosFrom(s,state.startIndex))
        return state
    }

    private fun parsePacket(s:String, stateIn:State):State {
        val v = s.parseBinToInt(stateIn.startIndex, 3)
        val state = stateIn.copy(versionSum = stateIn.versionSum+v)
        val id = s.parseBinToInt(state.startIndex+3,3)
        if (id == 4) {
            return parseNumberPacket(s,state)
        } else {

            return if (s[state.startIndex+6] == '0') {
                val l = s.parseBinToInt(state.startIndex+7,15)
                val subString = s.substring(state.startIndex+7+15,state.startIndex+7+15+l)
                val subState = parsePackets(subString,State())
                state.copy(startIndex = state.startIndex+7+15+l, versionSum = state.versionSum + subState.versionSum,values = state.values + listOf(calcValue(id,subState.values)) )
            } else {
                val l = s.parseBinToInt(state.startIndex+7,11)
                var subState = State(startIndex = state.startIndex+7+11)
                for (i in 0 until l) {
                    subState = parsePacket(s, subState)
                }

                state.copy(versionSum=state.versionSum + subState.versionSum,startIndex = subState.startIndex, values = state.values+ listOf(calcValue(id,subState.values)))
            }
        }
    }

    private fun calcValue(id: Int, values: List<Long>): Long {
        if (id == 0) return values.sum()
        if (id == 1) return values.fold(1L) { r,v -> r*v}
        if (id == 2) return values.minOf { it  }
        if (id == 3) return values.maxOf { it }
        if (id == 5) return if (values[0] > values[1]) 1L else 0L
        if (id == 6) return if (values[0] < values[1]) 1L else 0L
        if (id == 7) return if (values[0] == values[1]) 1L else 0L
        require(false)
        return 0
    }

    private fun parseNumberPacket(s:String, state:State):State {
        var i = state.startIndex+6
        var n = ""
        do {
            val stop = s[i] == '0'
            n += s.substring(i+1,i+5)
            i+= 5
        } while(!stop)
        return state.copy(startIndex = i, values = state.values + n.parseBinToLong())
    }



}