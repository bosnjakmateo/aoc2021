import org.junit.Test
import kotlin.test.assertEquals

class Day17Test {

    private val input = DataParser.parseInts("day17.txt", separator = ",")

    @Test
    fun `should calculate highest y position`() {
        assertEquals(45, Day17.calculateHighestYPosition(input))
    }

    @Test
    fun `should calculate number of hits`() {
        assertEquals(112, Day17.calculateNumberOfHits(input))
    }
}