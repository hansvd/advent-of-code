package aoc2021

object Day20 {
    class Image(private val enhancement:String, private val rows:List<String>) {
        private val w = rows.first().length
        private val h = rows.size

        init {
            require(w == h)
        }
        fun process(version:Int): Image = enlarge(version).convert()
        private fun enlarge(version:Int): Image {
            val borderWidth = 2
            val border = if (enhancement[0] == '.') "." else {
                when {
                    version == 0 -> "."
                    version % 2 == 1 -> enhancement[0].toString()
                    else -> enhancement[511].toString()
                }
            }
            return Image(enhancement,
                (1..borderWidth).map { border.repeat(borderWidth*2+w) }
                        + rows.map { "${border.repeat(borderWidth)}$it${border.repeat(borderWidth)}" }
                        + (1..borderWidth).map { border.repeat(borderWidth*2+w) })
        }

        fun getValue(x:Int,y:Int):Char {
           val bin = String((y-1..y+1).map { r->
                rows[r].substring(x-1,x+2).map {

                    if (it == '.') '0' else '1' } }.flatten().toCharArray())
            return enhancement[Integer.parseInt(bin,2)]
        }
        private fun convert(): Image = Image(enhancement, (1 until h-1).map { y -> (1 until w - 1).map { x->
            getValue(x,y)
        } }.map { String(it.toCharArray())})

        fun count():Int = rows.fold(0) { c, r -> c + r.count { it == '#'} }

        override fun toString(): String = rows.fold("") { s, r -> s + r + '\n'}.toString()
    }
    fun invoke(lines: Sequence<String>, steps:Int = 1): Int =
        (1..steps).fold(parseInput(lines.toList())) { image, i -> image.process(i-1) }.count()


    private fun parseInput(lines: List<String>): Image = Image(lines[0], lines.drop(2))
}