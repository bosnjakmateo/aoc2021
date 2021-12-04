import org.junit.Test
import kotlin.test.assertEquals

class Day02Test {

    private val input = DataParser.parseStrings("day02.txt")

    @Test
    fun `should calculate submarine position`() {
        assertEquals(150, Day02.calculateSubmarinePosition(input))
    }

    @Test
    fun `should calculate submarine position with angle`() {
        assertEquals(900, Day02.calculateSubmarinePositionWithAngle(input))
    }
}