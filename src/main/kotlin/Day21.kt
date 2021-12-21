

object Day21 {
    class Player(val id:Int, var pos:Int, var score:Int=0) {
        fun play(dice:Int) {
            pos += dice
            pos = (pos - 1) % 10 + 1
            score += pos
        }
    }
    class Dice {
        var value = 0
        var turn = 0
        fun next():Int {
            turn += 1
            value+=1
            if (value > 100) value = 1
            return value
        }
    }
    fun part1(players:List<Player>): Int {

        return play(players)

    }

    private fun play(players: List<Player>):Int {
        var dice = Dice()
        do {
            for (p in players) {
                p.play(dice.next() + dice.next() + dice.next())

                println("${p.id}: ${p.pos} ${p.score}")
                if (p.score >= 1000) {
                    val p2 = players.first { it.score < 1000 }
                    return p2.score * dice.turn
                }
            }
        } while (true)
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }
}