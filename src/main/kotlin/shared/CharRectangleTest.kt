package shared

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CharRectangleTest {
    @Test
    fun rectangleRotateTest() {
        val r = CharRectangle(
            listOf(
                listOf('A', 'B', 'C'),
                listOf('D', 'E', 'F'),
                listOf('G', 'H', 'I')
            )
        )
        val r2 = CharRectangle(
            listOf(
                listOf('G', 'D', 'A'),
                listOf('H', 'E', 'B'),
                listOf('I', 'F', 'C')
            )
        )
        assertEquals(r2, r.rotate90())
    }
}