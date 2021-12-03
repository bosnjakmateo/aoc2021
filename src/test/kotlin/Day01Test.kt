import org.junit.Test
import kotlin.test.assertEquals

class Day01Test {

    @Test
    fun `should calculate correct increase for window size 1`() {
        val measurements = DataParser.parseInts("day01.txt")

        assertEquals(7, Day01.calculateIncrease(measurements, 1))
    }

    @Test
    fun `should calculate correct increase for window size 3`() {
        val measurements = DataParser.parseInts("day01.txt")

        assertEquals(5, Day01.calculateIncrease(measurements, 3))
    }
}