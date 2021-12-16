fun String.parseBinToInt(startIndex:Int, length:Int):Int {
    return Integer.parseInt(this.substring(startIndex,startIndex+length),2)
}
class Day16 {

    companion object {
        fun part1(input:String):Int {
            val bin = hexToBinString(input)
            val day16 =Day16()
            return day16.parsePackets(bin)
        }

        fun hexToBinString(l:String):String = l.fold("") { r, c ->
            var b =Integer.toBinaryString(c.digitToInt(16))
            while(b.length < 4) b = "0$b"
            r + b
        }
    }
    private var versionSum = 0
    fun parsePackets(s:String):Int {
        var i = 0
        do {
            i = parsePacket(s,i)
        } while (i < s.length && !onlyZerosFrom(s,i))
        return versionSum
    }
    private fun onlyZerosFrom(s:String, startIndex: Int):Boolean {
        for (i in startIndex until s.length)
            if (s[i]=='1') return false
        return true
    }
    private fun parsePacket(s:String, startIndex:Int):Int {
        val v = s.parseBinToInt(startIndex, 3)
        versionSum += v
        val id = s.parseBinToInt(startIndex+3,3)
        if (id == 4) {
            return parseNumberPacket(s,startIndex)
        } else {
            return if (s[startIndex+6] == '0') {
                val l = s.parseBinToInt(startIndex+7,15)
                val subString = s.substring(startIndex+7+15,startIndex+7+15+l)
                parsePackets(subString)
                startIndex+7+15+l
            } else {
                val l = s.parseBinToInt(startIndex+7,11)
                var subStart = startIndex+7+11
                for (i in 0 until l) {
                    subStart = parsePacket(s, subStart)
                }
                subStart
            }
        }
    }
    private fun parseNumberPacket(s:String, startIndex:Int):Int {
        var i = startIndex+6
        var n = ""
        do {
            val stop = s[i] == '0'
            n += s.substring(i+1,i+5)
            i+= 5
        } while(!stop)
        return i
    }



}