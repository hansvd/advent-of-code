package shared

data class HLine(val x:IntRange, val y:Int) {
    val right get() = x.last
    val left get() = x.first
}