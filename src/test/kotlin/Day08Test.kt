import org.junit.Test
import kotlin.test.assertEquals

class Day08Test {

    private val input = DataParser.parseStrings("day08.txt")

    @Test
    fun `should calculate number of special digits`() {
        assertEquals(26, Day08.calculateNumberOfSpecialDigits(input))
    }

    @Test
    fun `should calculate sum of deciphered output`() {
        assertEquals(61229, Day08.calculateSumOfDecipheredOutput(input))
    }
}