import org.junit.Test
import kotlin.test.assertEquals

class Day15Test {

    private val input = DataParser.parseStrings("day15.txt")

    @Test
    fun `should calculate risk level`() {
        assertEquals(40, Day15.calculateRiskLevel(input))
    }

    @Test
    fun `should calculate risk level for five times cave multiplication`() {
        assertEquals(315, Day15.calculateRiskLevel(input, 5))
    }
}