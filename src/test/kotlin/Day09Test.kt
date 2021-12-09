import org.junit.Test
import kotlin.test.assertEquals

class Day09Test {

    private val input = DataParser.parseStrings("day09.txt")

    @Test
    fun `should calculate sum of risk levels`() {
        assertEquals(15, Day09.calculateSumOfRiskLevels(input))
    }

    @Test
    fun `should calculate sum of basin sizes`() {
        assertEquals(1134, Day09.calculateSumOfBasinSizes(input))
    }
}