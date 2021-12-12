import org.junit.Test
import kotlin.test.assertEquals

class Day12Test {

    private val input = DataParser.parseStrings("day12.txt")

    @Test
    fun `should calculate number of paths visiting small caves once`() {
        assertEquals(10, Day12.calculateNumberOfPaths(input))
    }

    @Test
    fun `should calculate number of paths visiting small caves twice and remaining small caves once`() {
        assertEquals(36, Day12.calculateNumberOfPathsWithTimeWasting(input))
    }
}