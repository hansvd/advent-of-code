package shared

fun IntRange.intersect(other: IntRange): IntRange =
    IntRange(maxOf(this.first, other.first), minOf(this.last, other.last))

val IntRange.width: Int get() = this.last - this.first + 1
fun IntRange.isOverlap(other: IntRange) = !intersect(other).isEmpty()
fun IntRange.contains(other: IntRange) = first <= other.first && last >= other.last

fun IntRange.combine(other:IntRange) = minOf(first, other.first) .. maxOf(last,other.last)
