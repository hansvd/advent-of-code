package shared

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PointTest {

    @Test
    fun testManhattanDistance() {
        assertEquals(0, Point(1, 1).manhattanDistance(Point(1, 1)))
        assertEquals(1, Point(1, 1).manhattanDistance(Point(2, 1)))
        assertEquals(1, Point(1, 1).manhattanDistance(Point(0, 1)))
        assertEquals(2, Point(1, 1).manhattanDistance(Point(0, 0)))
    }

    @Test
    fun testAdjacentWithManhattanDistance() {
        assertEquals(
            setOf(
                Point(x = -1, y = 1),
                Point(x = 0, y = 0),
                Point(x = 0, y = 1),
                Point(x = 0, y = 2),
                Point(x = 1, y = -1),
                Point(x = 1, y = 0),
                Point(x = 1, y = 1),
                Point(x = 1, y = 2),
                Point(x = 1, y = 3),
                Point(x = 2, y = 0),
                Point(x = 2, y = 1),
                Point(x = 2, y = 2),
                Point(x = 3, y = 1)
            ), Point(1, 1).adjacentWithManhattanDistance(2).toSet()
        )

    }
}