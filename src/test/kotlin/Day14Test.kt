import org.junit.Test
import kotlin.test.assertEquals

class Day14Test {

    private val input = DataParser.parseStrings("day14.txt")

    @Test
    fun `should calculate polymer growth after 10 steps`() {
        assertEquals(1588, Day14.calculatePolymerGrowth(input, 10))
    }

    @Test
    fun `should calculate polymer growth after 40 steps`() {
        assertEquals(2188189693529L, Day14.calculatePolymerGrowth(input, 40))
    }
}