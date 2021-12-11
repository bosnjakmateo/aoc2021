import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {

    private val input = DataParser.parseStrings("day11.txt")

    @Test
    fun `should calculate number of flashes at day 100`() {
        assertEquals(1656, Day11.calculateNumberOfFlashesAtDayHundred(input))
    }

    @Test
    fun `should calculate first day of synchronized flashes`() {
        assertEquals(195, Day11.calculateFirstDayOfSynchronizedFlashes(input))
    }
}