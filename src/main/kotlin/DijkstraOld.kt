// Algoritm based on https://github.com/alexhwoods/alexhwoods.com/tree/master/kotlin-algorithms

fun <T> List<Pair<T, T>>.getUniqueValuesFromPairs(): Set<T> = this
    .map { (a, b) -> listOf(a, b) }
    .flatten()
    .toSet()

fun <T> List<Pair<T, T>>.getUniqueValuesFromPairs(predicate: (T) -> Boolean): Set<T> = this
    .map { (a, b) -> listOf(a, b) }
    .flatten()
    .filter(predicate)
    .toSet()

interface Graph<T>{
    val vertices: Set<T>
    fun edges(t:T):Set<T>?
    fun weights(t:Pair<T,T>): Int
}



fun <T> dijkstra(graph: Graph<T>, start: T): Map<T, T?> {
    val s: MutableSet<T> = mutableSetOf() // a subset of vertices, for which we know the true distance

    /*
     * delta represents the length of the shortest distance paths
     * from start to v, for v in vertices.
     *
     * The values are initialized to infinity, as we'll be getting the key with the min value
     */
    val delta = graph.vertices.associateWith { Int.MAX_VALUE }.toMutableMap()
    delta[start] = 0

    val previous: MutableMap<T, T?> = graph.vertices.associateWith { null }.toMutableMap()

    while (s != graph.vertices) {
        println(s.size)
        // let v be the closest vertex that has not yet been visited
        val v: T = delta
            .filter { !s.contains(it.key) }
            .minByOrNull { it.value }
            ?.key ?: continue

        graph.edges(v)?.minus(s)?.forEach { neighbor ->
            val newPath = delta.getValue(v) + graph.weights(Pair(v, neighbor))

            if (newPath < delta.getValue(neighbor)) {
                delta[neighbor] = newPath
                previous[neighbor] = v
            }
        }

        s.add(v)
    }

    return previous.toMap()
}

fun <T> shortestPath(shortestPathTree: Map<T, T?>, start: T, end: T): List<T> {
    fun pathTo(start: T, end: T): List<T> {
        if (shortestPathTree[end] == null) return listOf(end)
        return listOf(pathTo(start, shortestPathTree[end]!!), listOf(end)).flatten()
    }

    return pathTo(start, end)
}
