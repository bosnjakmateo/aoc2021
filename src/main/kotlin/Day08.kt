import Day08.Match.*
import Utils.one

object Day08 {

    data class Display(
        val signals: List<List<String>>,
        val outputs: List<List<String>>,
    )

    enum class Match { ALL, ONE, NONE; }

    fun calculateNumberOfSpecialDigits(inputs: List<String>): Int {
        return inputs.sumOf { input ->
            input
                .split("|")
                .last()
                .split(" ")
                .count { listOf(2, 3, 4, 7).contains(it.length) }
        }
    }

    fun calculateSumOfDecipheredOutput(inputs: List<String>): Int {
        val displays = parseInputs(inputs)

        return displays.sumOf { display ->
            val uniqueDigitSegments = buildUniqueDigitSegments(display)

            display.outputs.joinToString("") { output ->
                when {
                    output.match(uniqueDigitSegments, one = ALL, four = ONE, seven = ALL, eight = ALL) -> "0"
                    output.match(uniqueDigitSegments, one = ALL, four = NONE, seven = NONE, eight = NONE) -> "1"
                    output.match(uniqueDigitSegments, one = ONE, four = ONE, seven = ALL, eight = ALL) -> "2"
                    output.match(uniqueDigitSegments, one = ALL, four = ONE, seven = ALL, eight = ONE) -> "3"
                    output.match(uniqueDigitSegments, one = ALL, four = ALL, seven = NONE, eight = NONE) -> "4"
                    output.match(uniqueDigitSegments, one = ONE, four = ALL, seven = ALL, eight = ONE) -> "5"
                    output.match(uniqueDigitSegments, one = ONE, four = ALL, seven = ALL, eight = ALL) -> "6"
                    output.match(uniqueDigitSegments, one = ALL, four = NONE, seven = ALL, eight = NONE) -> "7"
                    output.match(uniqueDigitSegments, one = ALL, four = ALL, seven = ALL, eight = ALL) -> "8"
                    output.match(uniqueDigitSegments, one = ALL, four = ALL, seven = ALL, eight = ONE) -> "9"
                    else -> throw IllegalArgumentException()
                }
            }.toInt()
        }
    }

    private fun buildUniqueDigitSegments(display: Display): MutableMap<Int, Set<String>> {
        val uniqueOneSegment = display.signals.find { it.size == 2 }!!.toSet()
        val uniqueSevenSegment = display.signals.find { it.size == 3 }!!.toSet()
        val uniqueFourSegment = display.signals.find { it.size == 4 }!!.toSet()
        val uniqueEightSegment = display.signals.find { it.size == 7 }!!.toSet()

        return mutableMapOf(
            1 to uniqueOneSegment,
            7 to uniqueSevenSegment.minus(uniqueOneSegment),
            4 to uniqueFourSegment.minus(uniqueOneSegment).minus(uniqueSevenSegment),
            8 to uniqueEightSegment.minus(uniqueOneSegment).minus(uniqueSevenSegment).minus(uniqueFourSegment),
        )
    }

    private fun List<String>.match(
        digitDisplays: MutableMap<Int, Set<String>>,
        one: Match,
        four: Match,
        seven: Match,
        eight: Match
    ): Boolean {
        return this.match(one, digitDisplays[1]!!)
                && this.match(four, digitDisplays[4]!!)
                && this.match(seven, digitDisplays[7]!!)
                && this.match(eight, digitDisplays[8]!!)
    }

    private fun List<String>.match(match: Match, digitDisplay: Set<String>): Boolean {
        return when (match) {
            ALL -> digitDisplay.all { this.contains(it) }
            ONE -> digitDisplay.one { this.contains(it) }
            NONE -> digitDisplay.none { this.contains(it) }
        }
    }

    private fun parseInputs(inputs: List<String>): List<Display> {
        return inputs.map { input ->
            val rawDisplay = input.split("|")
            Display(
                rawDisplay[0].trim().split(" ").map { it.chunked(1) },
                rawDisplay[1].trim().split(" ").map { it.chunked(1) }
            )
        }
    }
}

fun main() {
    val input = DataParser.parseStrings("day08.txt")

    println("Solutions")
    println("Part one: ${Day08.calculateNumberOfSpecialDigits(input)}")
    println("Part one: ${Day08.calculateSumOfDecipheredOutput(input)}")
}