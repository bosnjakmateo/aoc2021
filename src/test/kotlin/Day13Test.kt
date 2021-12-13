import org.junit.Test
import kotlin.test.assertEquals

class Day13Test {

    private val input = DataParser.parseStrings("day13.txt")

    @Test
    fun `should calculate visible dots after one fold`() {
        assertEquals(17, Day13.calculateVisibleDotsAfterOneFold(input))
    }

    @Test
    fun `should print out code after all folding is completed`() {
        Day13.printCode(input)
    }
}