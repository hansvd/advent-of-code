

object Day20 {
    class Image(val enhancement:String, val rows:List<String>) {
        val w = rows.first().length
        val h = rows.size

        init {
            require(w == h)
        }
        fun process(turn:Int):Image {
            //println("========")
            //println(this)
            val e = enlarge(turn)
            //println("-------")
            //println(e)
            val r = e.convert()
//            println("-------")
//            println(r)
            return r
        }
        fun enlarge(turn:Int):Image {
            val extra = 5
            val border = if (turn == 0) "." else enhancement[0].toString()
            return Image(enhancement,
                (1..extra).map { border.repeat(extra*2+w) }
                        + rows.map { "${border.repeat(extra)}$it${border.repeat(extra)}" }
                        + (1..extra).map { border.repeat(extra*2+w) })
        }

        fun getValue(x:Int,y:Int):Char {

           val bin = String((y-1..y+1).map { r->
                rows[r].substring(x-1,x+2).map {

                    if (it == '.') '0' else '1' } }.flatten().toCharArray())
            val dec = Integer.parseInt(bin,2)
            return enhancement[dec]
        }
        fun convert():Image {
            val newRows = (1 until h-1).map { y -> (1 until w - 1).map { x->
                getValue(x,y)
            } }.map { String(it.toCharArray())}
            return Image(enhancement,newRows)
        }

        fun count():Int = rows.fold(0) { c, r -> c + r.count { it == '#'} }

        override fun toString(): String {
            return rows.fold("") { s, r -> s + r + '\n'}.toString()
        }
    }
    fun part1(lines: Sequence<String>, steps:Int = 1): Int {
        var image = parseInput(lines.toList())
//        println("===")
//        println(image)


        println(image.count())
        for (i in 1..steps) {

            image = image.process(i-1)
            println(image.count())
        }

        println("===")
        println(image)
        return image.count()
    }

    fun part2(lines: Sequence<String>): Int {
        return 0
    }

    fun parseInput(lines: List<String>):Image {
        return Image(lines[0], lines.drop(2))
    }
}