package shared

data class CharRectangle(val rows: List<List<Char>>) {

    private val width = rows[0].size
    private val height = rows.size

    fun get(p: Point) = rows[p.y][p.x]

    fun rotate90() = CharRectangle((0 until width).map { w ->
        (height - 1 downTo 0).map { h -> rows[h][w] }
    })

    //fun rotate180() = rotate90().rotate90()
    //fun rotate270() = rotate90().rotate180()

}
