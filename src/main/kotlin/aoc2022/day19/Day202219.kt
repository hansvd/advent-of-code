package aoc2022.day19

import java.util.*

typealias RobotPrices = List<Int>

object Day202219 {

    private const val Ore = 0
    private const val Clay = 1
    private const val Obsidian = 2
    const val Geode = 3
    private const val CurrencyNb = 4
    const val RobotNb = this.CurrencyNb

    private fun nameToInt(name: String): Int = when (name) {
        "ore" -> Ore
        "clay" -> Clay
        "obsidian" -> Obsidian
        "geode" -> Geode
        else -> {
            assert(false);0
        }
    }


    fun part1(lines: Sequence<String>): Int = parseInput(lines).sumOf { it.find() * it.id }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    data class Blueprint(val id: Int, val robotPrices: List<RobotPrices>) {
        val maxCosts = (0 until RobotNb).map { c ->
            robotPrices.maxOf { it[c] }
        }

        private val potentialComparator = compareByDescending<State> { it.potential }
        private val stepsComparator = compareBy<State> { it.stepsLeft }
        private val queue = PriorityQueue(potentialComparator.then(stepsComparator))

        private val seenBefore = mutableMapOf<Int, Int>()

        private fun hasSeenBefore(robots: List<Int>, assets: List<Int>, stepsLeft: Int): Boolean {
            var s = 1
            robots.forEach { s = s * 31 + it }
            assets.forEach { s = s * 31 + it }
            val before = seenBefore[s]
            if (before == null) {
                seenBefore[s] = stepsLeft
                return false
            } else {
                if (before >= stepsLeft) return true
                return false
            }
        }

        fun find(): Int {
            println("Find $id")
            var max = 0
            queue += State(this, 24)
            while (queue.isNotEmpty()) {
                val next = queue.poll()
                when  {
                    next.stepsLeft == 0 -> {
                        //next.print()
                        max = maxOf(max, next.assets[Geode])
                    }
                    next.potential < max -> break
                    else -> {
                        next.nextStates().filter {
                            it.potential > max &&
                                    !hasSeenBefore(it.robots, it.assets, it.stepsLeft)
                        }.forEach { queue.add(it) }
                    }
                }
            }
            return max
        }

    }

    data class State(
        val blueprint: Blueprint,
        val stepsLeft: Int,
        val robots: List<Int> = listOf(1, 0, 0, 0),
        val assets: List<Int> = listOf(0, 0, 0, 0)
    ) {

        val potential = assets[Geode] + (robots[Geode] * stepsLeft) + (stepsLeft * (stepsLeft + 1)) / 2


        fun nextStates(): Sequence<State> {
            //print()
            if (stepsLeft == 0) {
                return sequenceOf()
            }
            val me = this
            return sequence {
                if (canBuyRobot(Geode)) {  // do this if possible
                    yield(nextStep().buyRobot(Geode))
                }
                for (i in RobotNb - 2 downTo 0)
                    if (canBuyRobot(i) && robots[i] < blueprint.maxCosts[i]// &&
                    ) {
                        yield(nextStep().buyRobot(i))
                    }

                yield(me.nextStep())
            }
        }

        override fun toString(): String {
            return "${blueprint.id}: potential=$potential, stepleft=$stepsLeft, robots=$robots, assets=$assets"
        }

        private fun nextStep(): State = copy(
            stepsLeft = stepsLeft - 1,
            assets = assets.mapIndexed { index, a -> a + robots[index] }
        )

        private fun buyRobot(robot: Int): State = copy(
            assets = assets.mapIndexed { index, currency -> currency - blueprint.robotPrices[robot][index] },
            robots = robots.mapIndexed { index, r -> if (index == robot) r + 1 else r }
        )

        private fun canBuyRobot(robot: Int): Boolean =
            assets.mapIndexed { index, a -> a >= blueprint.robotPrices[robot][index] }.all { it }
    }


    fun parseInput(lines: Sequence<String>): Sequence<Blueprint> = lines.map { l ->
        val lineParts = l.split(':')
        val id = lineParts[0].split(' ')[1].toInt()

        val instrParts = lineParts[1].split('.').filter { it.isNotBlank() }
        val instructions = MutableList(CurrencyNb) { MutableList(CurrencyNb) { 0 } }
        instrParts.forEach { i ->
            val costParts = i.split(" robot costs ")
            val robot = nameToInt(costParts[0].trim().substringAfter(' '))
            costParts[1].split(" and ").forEach {
                val s = it.split(' ')
                instructions[robot][nameToInt(s[1])] = s[0].toInt()
            }
        }
        Blueprint(id, instructions)
    }
}