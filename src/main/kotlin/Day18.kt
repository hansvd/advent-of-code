object Day18 {
    data class StepResult(val v: Value, val addLeft: Int = 0, val addRight: Int = 0, val done: Boolean = false)

    fun part2(lines:Sequence<String>):Int {
       val values = lines.map { parseInput(it)}.toList()

        return values.fold(0) { max, l ->
            values.fold(max) { max2, l2 ->
                if (l == l2) max2
                else maxOf(SnailFishValue(l, l2).reduce().magnitude(), max2)
            }
        }


    }
    interface Value {
        fun explode(depth: Int): StepResult
        fun split(): StepResult
        fun addFirst(value: Int): StepResult
        fun addLast(value: Int): StepResult
        fun magnitude():Int

        fun explode(): Value = explode(0).v
        fun splitter(): Value = split().v

        fun reduce(): Value {
            var result = this
            do {
                var stepResult = result.explode(0)
                if (stepResult.done) {
                    result = stepResult.v
                    continue
                }

                stepResult = result.split()
                result = stepResult.v

            } while (stepResult.done)
            return result
        }


    }

    data class NumValue(val num: Int) : Value {
        override fun explode(depth: Int): StepResult = StepResult(this)

        override fun split(): StepResult {
            if (num < 10) return StepResult(this)
            return StepResult(SnailFishValue((num / 2).toValue(), ((num + 1) / 2).toValue()), done = true)
        }

        override fun addFirst(value: Int): StepResult = StepResult(NumValue(num + value), done = true)
        override fun addLast(value: Int): StepResult = StepResult(NumValue(num + value), done = true)
        override fun toString(): String = num.toString()
        override fun magnitude(): Int = num
    }

    data class SnailFishValue(val left: Value, val right: Value) : Value {

        override fun explode(depth: Int): StepResult {
            require(depth != 4 || (left is NumValue && right is NumValue))
            if (depth == 4 && (left is NumValue && right is NumValue)) {
                return StepResult(0.toValue(), left.num, right.num, true)
            }
            val leftResult = left.explode(depth + 1)
            var addLeft = leftResult.addLeft
            var addRight = leftResult.addRight

            val rightResult = if (leftResult.done) {
                val r = right.addFirst(addRight)
                addRight = 0
                r
            } else {
                right.explode(depth + 1)
            }

            addLeft += rightResult.addLeft
            addRight += rightResult.addRight
            var newLeft = leftResult.v
            var newRight = rightResult.v
            if (newLeft is NumValue && addLeft > 0 && (!leftResult.done || depth < depth - 1)) {
                newLeft = NumValue(newLeft.num + addLeft)
                addLeft = 0
            }
            if (newRight is NumValue && addRight > 0 && (!rightResult.done || depth < depth - 1)) {
                newRight = NumValue(newRight.num + addRight)
                addRight = 0
            }
            if (rightResult.done && addLeft > 0 && !leftResult.done) {
                val addLeftResult = newLeft.addLast(addLeft)
                if (addLeftResult.done) {
                    newLeft = addLeftResult.v
                    addLeft = 0
                }

            }
            return StepResult(
                SnailFishValue(newLeft, newRight),
                addLeft,
                addRight,
                leftResult.done || rightResult.done
            )

        }

        override fun split(): StepResult {
            val leftResult = left.split()
            if (leftResult.done) {
                return StepResult(SnailFishValue(leftResult.v, right), done = true)
            }
            val rightResult = right.split()
            return StepResult(SnailFishValue(left, rightResult.v), done = rightResult.done)
        }

        override fun addFirst(value: Int): StepResult {
            val leftResult = left.addFirst(value)
            if (leftResult.done)
                return StepResult(SnailFishValue(leftResult.v, right), done = true)

            val rightResult = right.addFirst(value)
            return StepResult(SnailFishValue(left, rightResult.v), done = rightResult.done)
        }

        override fun addLast(value: Int): StepResult {
            val result = right.addLast(value)
            return StepResult(SnailFishValue(left, result.v), done = result.done)

        }

        override fun toString(): String = "[$left,$right]"
        override fun magnitude(): Int = left.magnitude()*3 + right.magnitude()*2
    }

    fun Int.toValue() = NumValue(this)

    fun parseInputLines(input: Sequence<String>): Value =
        input.map { parseInput(it) }.reduce { r, l -> SnailFishValue(r, l).reduce() }

    fun parseInput(line: String): Value {
        require(line.isNotBlank() && line.first() == '[' && line.last() == ']')

        val separatorIndex = pairSeparatorIndex(line)
        val left = parseValue(line.substring(1, separatorIndex))
        val right = parseValue(line.substring(separatorIndex + 1, line.length - 1))
        return SnailFishValue(left, right)
    }

    private fun pairSeparatorIndex(line: String): Int {
        var numPairs = 0
        for (i in line.indices) {
            if (line[i] == '[') numPairs++
            if (line[i] == ']') numPairs--
            if (numPairs <= 1 && line[i] == ',') return i
        }
        return line.length
    }

    fun parseValue(line: String): Value {
        var i = 0; while (i < line.length && line[i].isDigit()) i++
        if (i > 0) return NumValue(Integer.parseInt(line.substring(0, i)))
        if (line[0].isDigit()) return NumValue(line[0].code - '0'.code)
        return parseInput(line)
    }
}