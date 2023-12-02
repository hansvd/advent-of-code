package aoc2023.day02
typealias SamleSet =List<Day.Sample>
object Day {

    data class Sample(val number:Int, val color:String)

    data class Game(val id:Int, val samples:List<SamleSet>)
    //only 12 red cubes, 13 green cubes, and 14 blue cubes
    fun part1(lines: List<String>):Int {
        val games = parseInput(lines)
        return games.mapNotNull { game ->
            if (game.samples.all { set ->
                    set.all {
                        when (it.color) {
                            "red" -> it.number <= 12
                            "green" -> it.number <= 13
                            "blue" -> it.number <= 14
                            else -> false
                        }
                    }
                }
            ) game.id else null
        }.sum()
    }


    fun part2(lines: List<String>): Int = 0

    private fun parseInput(lines: List<String>):List<Game> = lines.map { line ->
        val game = line.split(":")
        Game(game[0].split(' ')[1].toInt(),
            game[1].split(';').map { set ->
            set.split(',').map { sample ->
                val s = sample.trim().split(' ')
                Sample(s[0].toInt(), s[1])
            }
        })
    }

}