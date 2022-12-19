package aoc2022.day17

object Day202217 {

    val Rock = 1
    val NewRock = 2
    val Empty = 0

    val shapes = listOf(
        """####""".lines(), """
            .#.
            ###
            .#.""".trimIndent().lines(), """
            ..#
            ..#
            ###""".trimIndent().lines(), """
            #
            #
            #
            #""".trimIndent().lines(), """
            ##
            ##""".trimIndent().lines()
    )

    class Chamber(private val width: Int = 7) {
        private var iShape = -1
        private val content = mutableListOf(MutableList(7) { Rock })
        private var iContentForShape = content.size

        init {
            addContentLines()
        }

        fun print(iEnd:Int = 0) {
            for (i in content.size - 1 downTo iEnd) {
                for (c in 0..6)
                    if (content[i][c] == Rock) print("#") else if (content[i][c] == NewRock) print('@') else print(".")
                println()
            }
            println("----------------------------")

        }

        private fun addContentLines() {

            for (i in iContentForShape until content.size) {
                for (c in 0 until width) {
                    if (content[i][c] == NewRock) content[i][c] = Rock
                }
            }

            repeat(3) {
                content += MutableList(7) { Empty }
            }

            iContentForShape = content.size
        }

        private fun getNextShape() {
            iShape++
            val shape = shapes[iShape % shapes.size].map {
                var s = "..$it"
                while (s.length < 7) s += '.'
                s
            }.reversed()

            shape.forEach {
                content.add(it.map { if (it == '#') NewRock else Empty }.toMutableList())
            }
        }

        private fun canMoveDown(): Boolean {
            for (i in iContentForShape until content.size) {
                for (c in 0 until width) {
                    if (content[i][c] == NewRock) {
                        if (i - 1 < 0) return false
                        if (content[i-1][c] == Rock) return false
                    }
                }
            }
            return true
        }

        private fun moveDown() {

            for (i in iContentForShape until content.size) {
                for (c in 0 until width) {
                    if (content[i][c] == NewRock) {
                        //if (i - 1 < 0) continue
                        content[i-1][c] = NewRock
                        content[i][c] = Empty
                    }
                }
            }

            iContentForShape--
            for (i in content.size -1 downTo iContentForShape) {
                if (content[i].all { it == Empty}) content.removeLast()
                else break
            }
        }

        private fun canMoveLeft():Boolean {
            for (i in iContentForShape until content.size) {
                for (c in 0 until width) {
                    if (content[i][c] == NewRock) {
                        if (c == 0) return false
                        if (content[i][c-1] == Rock) return false
                    }
                }
            }
            return true
        }
        private fun moveLeft() {
            if (!canMoveLeft()) return

            for (i in iContentForShape until content.size) {
                for (c in 1 until width) {
                    if (content[i][c] == NewRock) {
                        content[i][c-1] = NewRock
                        content[i][c] = Empty
                    }
                }
            }
        }

        private fun canMoveRight():Boolean {
            for (i in iContentForShape until content.size) {
                for (c in 0 until  width) {
                    if (content[i][c] == NewRock) {
                        if (c >= width - 1) return false
                        if (content[i][c+1] == Rock) return false
                    }
                }
            }
            return true
        }
        private fun moveRight() {
            if (!canMoveRight()) return

            for (i in iContentForShape until content.size) {
                for (c in width-2 downTo 0) {
                    if (content[i][c] == NewRock) {
                        content[i][c+1] = NewRock
                        content[i][c] = Empty
                    }
                }
            }
        }
        fun run(input: String): Int {
            var iInput = -1
            getNextShape()
            while (true) {
                iInput = (iInput + 1) % input.length
                val instr = input.get(iInput)
                when (instr) {
                    '>' -> moveRight()
                    '<' -> moveLeft()
                }
                if (!canMoveDown()) {
                    if (iShape >= 2021) break
                    addContentLines()
                    getNextShape()
                } else
                    moveDown()

                //print()
            }

            //this.print(content.size - 4)
            return content.size - 1  // bottom
        }


    }

    fun part1(input: String): Int = Chamber().run(input)

    fun part2(lines: Sequence<String>): Int {
        return 0
    }


}