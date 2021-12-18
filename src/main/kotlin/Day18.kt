

object Day18 {
    data class ReduceResult(val v:Value, val addLeft:Int=0, val addRight:Int=0, val done:Boolean=false)

    interface Value {
        //val depth:In
        fun explode(depth:Int):ReduceResult
        fun split():ReduceResult
        fun explode():Value {
            return explode(0).v
        }
        fun splitter():Value {
            return split().v
        }
        fun reduce():Value {
            var result = this
            do {
                val explodeResult = result.explode(0)
                val splitResult = explodeResult.v.split()
                result = splitResult.v
            } while(explodeResult.done || splitResult.done)
            return result
        }
        fun addFirst(value:Int):ReduceResult
    }
    data class NumValue(val num:Int):Value {
        override fun explode(depth:Int):ReduceResult {
            //if (num >= 10) return this
            return ReduceResult(this)
        }

        override fun split(): ReduceResult {
            if (num < 10) return ReduceResult(this)
            return ReduceResult(SnailFishValue((num / 2).toValue(),((num + 1) / 2).toValue()),done=true)
        }

        override fun addFirst(value: Int): ReduceResult {
            return ReduceResult(NumValue(num + value),done=true)
        }

        override fun toString(): String = num.toString()
    }
    data class SnailFishValue(val left:Value, val right:Value):Value {
        //override val depth = 1 + maxOf(left.depth,right.depth)

        override fun explode(depth:Int):ReduceResult {
            if (depth == 4 && (left is NumValue && right is NumValue)) {
                return ReduceResult(0.toValue(),left.num,right.num,true)
            }
            else {
                val leftResult = left.explode(depth+1)
                var addLeft = leftResult.addLeft
                var addRight = leftResult.addRight

                val rightResult = if (leftResult.done) {
                    val r = right.addFirst(addRight)
                    addRight = 0
                    r
                } else right.explode(depth+1)



                addLeft = addLeft + rightResult.addLeft
                addRight = addRight + rightResult.addRight
                var newLeft = leftResult.v
                var newRight = rightResult.v
                if (newLeft is NumValue && addLeft > 0 && (!leftResult.done || depth < 3)) {
                    newLeft = NumValue(newLeft.num + addLeft)
                    addLeft = 0
                }
                if (newRight is NumValue && addRight > 0 && (!rightResult.done || depth < 3)) {
                    newRight = NumValue(newRight.num + addRight)
                    addRight = 0
                }
                return ReduceResult(SnailFishValue(newLeft,newRight),addLeft,addRight,leftResult.done || rightResult.done)
            }

        }

        override fun split(): ReduceResult {
            val leftResult = left.split()
            if (leftResult.done) {
                return ReduceResult(SnailFishValue(leftResult.v,right), done = true)
            }
            val rightResult = right.split()
            return ReduceResult(SnailFishValue(left,rightResult.v),done=rightResult.done)
        }

        override fun addFirst(value: Int): ReduceResult {
             val leftResult = left.addFirst(value)
            if (leftResult.done)
                return ReduceResult(SnailFishValue(leftResult.v,right),done=true)

            val rightResult = right.addFirst(value)
            return ReduceResult(SnailFishValue(left,rightResult.v),done = rightResult.done)

        }

        override fun toString(): String = "[$left,$right]"
    }
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
        var i = 0; while(i < line.length && line[i].isDigit()) i++
        if (i>0) return NumValue(Integer.parseInt(line.substring(0,i)))
        if (line[0].isDigit()) return NumValue(line[0].code - '0'.code)
        return parseInput(line)
    }
}