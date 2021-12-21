import org.junit.Test
import kotlin.test.assertEquals

class Day18Test {

    private val input = DataParser.parseStrings("day18.txt")

    @Test
    fun `should calculate magnitude of final sum`() {
        assertEquals(4140, Day18.calculateMagnitudeOfFinalSum(input))
    }

    @Test
    fun `should calculate highest magnitude of final sum using two numbers`() {
        assertEquals(3993, Day18.calculateHighestMagnitudeOfFinalSumUsingTwoNumbers(input))
    }
}