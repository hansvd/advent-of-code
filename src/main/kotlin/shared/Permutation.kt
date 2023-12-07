package shared

fun <T> permutations(possibleValues: List<T>, expectedSize:Int): List<List<T>> {
    if (expectedSize == 1) return possibleValues.map { listOf(it) }
    val result:MutableList<List<T>> = mutableListOf()
    (0 until expectedSize).forEach {index ->
        possibleValues.forEach {  p ->
            permutations(possibleValues, expectedSize - 1).forEach { result.add(listOf(p) + it) }
        }
    }
    return result
}

fun <T> combinations(possibleValues: List<T>, expectedSize: Int): List<List<T>> {
    if (expectedSize == 0) return listOf(emptyList())
    if (possibleValues.isEmpty()) return emptyList()

    val result = mutableListOf<List<T>>()
    val first = possibleValues.first()
    val rest = possibleValues.drop(1)

    for (i in 0..expectedSize) {
        combinations(rest, expectedSize - i).forEach { combo ->
            result.add(List(i) { first } + combo)
        }
    }

    return result
}