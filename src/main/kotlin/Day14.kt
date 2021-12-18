import Extensions.plus

object Day14 {

    fun calculatePolymerGrowth(inputs: List<String>, steps: Int): Long {
        val lastPolymer = inputs.first().last()
        val (polymerPairs, pairInsertions) = parseInputs(inputs)

        return (0 until steps)
            .fold(polymerPairs) { polymer, _ -> polymer.react(pairInsertions) }
            .byPolymerFrequency(lastPolymer)
            .values
            .sorted()
            .let { it.last() - it.first() }
    }

    private fun Map<String, Long>.react(pairInsertions: Map<String, String>): Map<String, Long> =
        buildMap {
            this@react.forEach { (pair, count) ->
                val inserted = pairInsertions.getValue(pair)
                plus("${pair.first()}$inserted", count)
                plus("$inserted${pair.last()}", count)
            }
        }

    private fun Map<String, Long>.byPolymerFrequency(lastPolymer: Char): Map<Char, Long> =
        this
            .map { it.key.first() to it.value }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.sum() + if (it.key == lastPolymer) 1 else 0 }

    private fun parseInputs(inputs: List<String>): Pair<Map<String, Long>, Map<String, String>> {
        val polymerTemplateAsPairs = inputs.first().windowed(2)

        return Pair(
            polymerTemplateAsPairs.groupingBy { it }.eachCount().mapValues { it.value.toLong() },
            inputs.drop(2).associate {
                it.take(2) to it.last().toString()
            }
        )
    }
}

fun main() {
    val input = DataParser.parseStrings("day14.txt")

    println("Solutions")
    println("Part one: ${Day14.calculatePolymerGrowth(input, 10)}")
    println("Part two: ${Day14.calculatePolymerGrowth(input, 40)}")
}