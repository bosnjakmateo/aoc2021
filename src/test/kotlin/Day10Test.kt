import org.junit.Test
import kotlin.test.assertEquals

class Day10Test {

    private val input = DataParser.parseStrings("day10.txt")

    @Test
    fun `should calculate syntax error score`() {
        assertEquals(26397, Day10.calculateSyntaxErrorScore(input))
    }

    @Test
    fun `should calculate middle score of completed lines`() {
        assertEquals(288957, Day10.calculateMiddleScoreOfCompletedLines(input))
    }
}