package aoc2022.day17

object Day202217 {

    const val Rock = 1
    const val NewRock = 2
    const val Empty = 0

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

    data class SnapShot(val bucket: List<List<Int>>, val iShape: Int, val iInput: Int)

    class Chamber(private val width: Int = 7) {
        private var iShape = -1
        private var shapeNb = 0L
        private val bucket = mutableListOf<MutableList<Int>>()
        private var iBucketForShape = bucket.size

        private var removedLines = 0L
        private val snapshots =
            mutableMapOf<SnapShot, Pair<Long, Long>>()    // snapshot to  steps done, height of bucket

        init {
            addBucketLines()
        }

//        fun print(iEnd: Int = 0) {
//            for (i in bucket.size - 1 downTo iEnd) {
//                for (c in 0..6)
//                    if (bucket[i][c] == Rock) print("#") else if (bucket[i][c] == NewRock) print('@') else print(".")
//                println()
//            }
//            println("----------------------------")
//
//        }

        private fun cleanUp() {

            for (i in iBucketForShape until bucket.size) {
                for (c in 0 until width) {
                    if (bucket[i][c] == NewRock) bucket[i][c] = Rock
                }
            }
            for (i in iBucketForShape - 1 downTo 0) {
                if (bucket[i].all { it == Rock }) {
                    removedLines += i
                    for (j in 0 until i) bucket.removeFirst()
                    break
                }
            }
        }

        private fun addBucketLines() {


            repeat(3) {
                bucket += MutableList(7) { Empty }
            }

            iBucketForShape = bucket.size
        }

        private fun getNextShape() {
            iShape = (iShape + 1) % shapes.size
            shapeNb++
            val shape = shapes[iShape].map {
                var s = "..$it"
                while (s.length < 7) s += '.'
                s
            }.reversed()

            shape.forEach {
                bucket.add(it.map { c -> if (c == '#') NewRock else Empty }.toMutableList())
            }
        }

        private fun canMoveDown(): Boolean {
            for (i in iBucketForShape until bucket.size) {
                for (c in 0 until width) {
                    if (bucket[i][c] == NewRock) {
                        if (i - 1 < 0) return false
                        if (bucket[i - 1][c] == Rock) return false
                    }
                }
            }
            return true
        }

        private fun moveDown(): Boolean {

            if (!canMoveDown()) return false
            for (i in iBucketForShape until bucket.size) {
                for (c in 0 until width) {
                    if (bucket[i][c] == NewRock) {
                        //if (i - 1 < 0) continue
                        bucket[i - 1][c] = NewRock
                        bucket[i][c] = Empty
                    }
                }
            }

            iBucketForShape--
            for (i in bucket.size - 1 downTo iBucketForShape) {
                if (bucket[i].all { it == Empty }) bucket.removeLast()
                else break
            }
            return true
        }

        private fun canMoveLeft(): Boolean {
            for (i in iBucketForShape until bucket.size) {
                for (c in 0 until width) {
                    if (bucket[i][c] == NewRock) {
                        if (c == 0) return false
                        if (bucket[i][c - 1] == Rock) return false
                    }
                }
            }
            return true
        }

        private fun moveLeft() {
            if (!canMoveLeft()) return

            for (i in iBucketForShape until bucket.size) {
                for (c in 1 until width) {
                    if (bucket[i][c] == NewRock) {
                        bucket[i][c - 1] = NewRock
                        bucket[i][c] = Empty
                    }
                }
            }
        }

        private fun canMoveRight(): Boolean {
            for (i in iBucketForShape until bucket.size) {
                for (c in 0 until width) {
                    if (bucket[i][c] == NewRock) {
                        if (c >= width - 1) return false
                        if (bucket[i][c + 1] == Rock) return false
                    }
                }
            }
            return true
        }

        private fun moveRight() {
            if (!canMoveRight()) return

            for (i in iBucketForShape until bucket.size) {
                for (c in width - 2 downTo 0) {
                    if (bucket[i][c] == NewRock) {
                        bucket[i][c + 1] = NewRock
                        bucket[i][c] = Empty
                    }
                }
            }
        }

        fun run(input: String, rocks: Long = 2022): Long {
            var iInput = -1
            getNextShape()
            var testLoop = true
            while (true) {
                iInput = (iInput + 1) % input.length
                when (input[iInput]) {
                    '>' -> moveRight()
                    '<' -> moveLeft()
                }
                if (moveDown()) continue

                if (shapeNb == rocks) break
                cleanUp()
                if (testLoop) {
                    // check if we skip a lot of shapes because of a looping pattern
                    testLoop = !testSnapshot(iInput, rocks)
                }
                addBucketLines()
                getNextShape()
                //print()
            }

            //this.print(bucket.size - 4)
            return removedLines + bucket.size
        }

        private fun testSnapshot(iInput: Int, rocks: Long): Boolean {

            // guessing 10 lines is max of lines the rocks will fall into the bucket
            // (better is calculate this, but that takes even more lines of code)
            val snapShot = SnapShot(bucket.take(10), iShape, iInput)
            snapshots[snapShot]?.let { (loopStartShapeNb, loopStartBucketHeight) ->
                val loopSize = shapeNb - loopStartShapeNb
                if (loopSize > 0) {
                    val loopCount = ((rocks - shapeNb) / loopSize) - 1
                    val bucketHeight = removedLines + bucket.size
                    removedLines += (bucketHeight - loopStartBucketHeight) * loopCount
                    shapeNb += loopCount * loopSize
                    return true // almost done
                }
            }
            snapshots[snapShot] = Pair(shapeNb, removedLines + bucket.size)
            return false
        }


    }

    fun part1(input: String) = Chamber().run(input)

    fun part2(input: String) = Chamber().run(input, 1000_000_000_000L)


}