import java.util.*

object Day10 {

    private val chunkPairs = mapOf(
        ")" to "(", "]" to "[", "}" to "{", ">" to "<",
        "(" to ")", "[" to "]", "{" to "}", "<" to ">"
    )
    private val openingChunkChar = setOf("(", "[", "{", "<")
    private val closingChunkChar = setOf(")", "]", "}", ">")

    sealed class Line
    class SyntaxError(val corruptedChar: String) : Line()
    class Incomplete(val uncompletedChunks: Stack<String>) : Line()

    fun calculateSyntaxErrorScore(inputs: List<String>): Int {
        val closingChunkValue = mapOf(")" to 3, "]" to 57, "}" to 1197, ">" to 25137)

        return parseLines<SyntaxError>(inputs).sumOf { closingChunkValue[it.corruptedChar]!! }
    }

    fun calculateMiddleScoreOfCompletedLines(inputs: List<String>): Long {
        val closingChunkValue = mapOf(")" to 1, "]" to 2, "}" to 3, ">" to 4)

        val closingChunkScores = parseLines<Incomplete>(inputs).map {
            val stack = it.uncompletedChunks
            var chunkValue = 0L

            while (stack.isNotEmpty()) {
                val closingChunk = chunkPairs[stack.pop()]
                chunkValue = chunkValue * 5 + closingChunkValue[closingChunk]!!
            }
            chunkValue
        }

        return closingChunkScores.sorted()[closingChunkScores.size / 2]
    }

    private inline fun <reified T> parseLines(inputs: List<String>): List<T> {
        return inputs.map { input ->
            val stack = Stack<String>()

            for (chunkChar in input.chunked(1)) {
                if (openingChunkChar.contains(chunkChar)) {
                    stack.push(chunkChar)
                    continue
                }
                if (closingChunkChar.contains(chunkChar) && stack.pop() != chunkPairs[chunkChar]) {
                    return@map SyntaxError(chunkChar)
                }
            }
            return@map Incomplete(stack)
        }.filterIsInstance<T>()
    }
}

fun main() {
    val input = DataParser.parseStrings("day10.txt")

    println("Solutions")
    println("Part one: ${Day10.calculateSyntaxErrorScore(input)}")
    println("Part two: ${Day10.calculateMiddleScoreOfCompletedLines(input)}")
}