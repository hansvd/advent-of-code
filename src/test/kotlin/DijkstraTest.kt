import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DijkstraTest {

    class MyGraph<T>( override val vertices: Set<T>,
                      val edges: Map<T, Set<T>>,
                      val weights: Map<Pair<T, T>, Int>): Graph<T> {

        override fun edges(t:T):Set<T>? {
            return edges[t]
        }
        override fun weights(t:Pair<T,T>): Int {
            return weights[t] ?: Int.MAX_VALUE
        }




    constructor(weights: Map<Pair<T, T>, Int>): this(
        vertices = weights.keys.toList().getUniqueValuesFromPairs(),
        edges = weights.keys
            .groupBy { it.first }
            .mapValues { it.value.getUniqueValuesFromPairs { x -> x !== it.key } }
            .withDefault { emptySet() },
        weights = weights
    )
    }


    @Test
    fun shouldProperlyInitializeGraph() {
        val weights = mapOf(
            Pair("A", "B") to 2,
            Pair("A", "C") to 8,
            Pair("A", "D") to 5,
            Pair("B", "C") to 1,
            Pair("C", "E") to 3,
            Pair("D", "E") to 2
        )

        val g = MyGraph(weights)

        assertEquals(setOf("A", "B", "C", "D", "E"), g.vertices)

        assertEquals(setOf("B", "C", "D"), g.edges.getValue("A"))
        assertEquals(setOf("C"), g.edges.getValue("B"))
        assertEquals(setOf("E"), g.edges.getValue("C"))
        assertEquals(setOf("E"), g.edges.getValue("D"))
        assertEquals(emptySet<String>(), g.edges.getValue("E"))

        assertEquals(weights, g.weights)
    }

    @Test
    fun shouldCalculateCorrectShortestPaths() {
        val weights = mapOf(
            Pair("A", "B") to 2,
            Pair("A", "C") to 8,
            Pair("A", "D") to 5,
            Pair("B", "C") to 1,
            Pair("C", "E") to 3,
            Pair("D", "E") to 2
        )

        val start = "A"
        val shortestPathTree = dijkstra(MyGraph(weights), start)

        assertEquals(listOf(start, "B", "C"), shortestPath(shortestPathTree, start, "C"))
        assertEquals(listOf(start, "B", "C", "E"), shortestPath(shortestPathTree, start, "E"))
        assertEquals(listOf(start, "D"), shortestPath(shortestPathTree, start, "D"))
    }
}