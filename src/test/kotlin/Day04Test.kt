import org.junit.Test
import kotlin.test.assertEquals

class Day04Test {

    private val input = DataParser.parseStrings("day04.txt", filterOutNewLines = true)

    @Test
    fun `should calculate bingo score for first winner`() {
        assertEquals(4512, Day04.calculateBingoScoreOfFirstWinner(input))
    }

    @Test
    fun `should calculate bingo score for last winner`() {
        assertEquals(1924, Day04.calculateBingoScoreOfLastWinner(input))
    }
}