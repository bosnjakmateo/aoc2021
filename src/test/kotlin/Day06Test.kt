import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class Day06Test {

    private val input = DataParser.parseInts("day06.txt", separator = ",")

    @Test
    fun `should calculate number of lanternfish after 80 days`() {
        assertEquals(BigInteger.valueOf(5934), Day06.calculateNumberOfLanternfish(input, 80))
    }

    @Test
    fun `should calculate number of lanternfish after 256 days`() {
        assertEquals(BigInteger.valueOf(26984457539), Day06.calculateNumberOfLanternfish(input, 256))
    }
}