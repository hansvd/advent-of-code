

object Day18 {
    interface Value
    data class NumValue(val num:Int):Value
    data class SnailFishValue(val left:Value, val right:Value):Value
    fun Int.toValue() = NumValue(this)
    fun part1(lines: Sequence<String>): Int {
        return 0
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    fun parseInput(line:String):SnailFishValue {
        require(line.isNotBlank() && line.first() == '[' && line.last() == ']')

        val separatorIndex = pairSeparatorIndex(line)
        val left = parseValue(line.substring(1,separatorIndex))
        val right = parseValue(line.substring(separatorIndex+1,line.length-1))
        return SnailFishValue(left,right)
    }
    fun pairSeparatorIndex(line:String):Int {
        var numPairs = 0
        for(i in line.indices) {
            if (line[i] == '[') numPairs++
            if (line[i] == ']') numPairs--
            if (numPairs <= 1 && line[i] == ',') return i
        }
        return line.length
    }
    fun parseValue(line:String):Value {
        if (line[0].isDigit()) return NumValue(line[0].code - '0'.code)
        return parseInput(line)
    }
}