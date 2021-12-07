import kotlin.math.abs

object Day07 {

    fun calculateFuelConsumptionAtConstantRate(inputs: List<Int>) =
        calculateFuelConsumption(inputs) { difference: Int -> difference }

    fun calculateFuelConsumptionAtIncrementalRate(inputs: List<Int>) =
        calculateFuelConsumption(inputs) { difference: Int -> (1..difference).sum() }

    private fun calculateFuelConsumption(
        inputs: List<Int>,
        fuelConsumptionFormula: (startPosition: Int) -> Int
    ): Int {
        val max = inputs.maxOrNull()!!
        val min = inputs.minOrNull()!!

        val previousSolutions = mutableMapOf<Int, Int>()

        return (min..max).minOf { position ->
            inputs.map {
                val difference = abs(it - position)
                previousSolutions.getOrPut(difference) { fuelConsumptionFormula(difference) }
            }.reduce { acc, i -> acc + i }
        }
    }
}

fun main() {
    val input = DataParser.parseInts("day07.txt", separator = ",")

    println("Solutions")
    println("Part one: ${Day07.calculateFuelConsumptionAtConstantRate(input)}")
    println("Part two: ${Day07.calculateFuelConsumptionAtIncrementalRate(input)}")
}