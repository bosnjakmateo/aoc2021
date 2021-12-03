import org.junit.Test
import kotlin.test.assertEquals

class Day03Test {

    private val input = DataParser.parseStrings("day03.txt")

    @Test
    fun `should calculate correct increase for window size 1`() {
        assertEquals(198, Day03.calculatePowerConsumption(input))
    }

    @Test
    fun `should calculate correct increase for window size 3`() {
        assertEquals(230, Day03.calculateLifeSupportRating(input))
    }
}