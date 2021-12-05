import org.junit.Test
import kotlin.test.assertEquals

class Day05Test {

    private val input = DataParser.parseStrings("day05.txt")

    @Test
    fun `should calculate number of line overlap points without diagonal lines`() {
        assertEquals(5, Day05.calculateNumberOfLineOverlapPoints(input))
    }

    @Test
    fun `should calculate number of line overlap points with diagonal lines`() {
        assertEquals(12, Day05.calculateNumberOfLineOverlapPoints(input, ignoreDiagonalLines = false))
    }
}