import org.junit.Test
import kotlin.test.assertEquals

class Day16Test {

    private val inputPartOne = DataParser.parseString("day16_1.txt")
    private val inputPartTwo = DataParser.parseString("day16_2.txt")

    @Test
    fun `should calculate sum of version numbers`() {
        assertEquals(31, Day16.calculateSumOfVersionNumbers(inputPartOne))
    }

    @Test
    fun `should calculate bits transmission`() {
        assertEquals(1, Day16.calculateBitsTransmission(inputPartTwo))
    }
}