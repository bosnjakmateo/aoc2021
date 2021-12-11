import Utils.permutations

object Day11 {

    data class Octopus(
        val x: Int,
        val y: Int,
        var timer: Int,
        var flashed: Boolean = false,
    )

    private val neighboursLocations = permutations(1, -1, 0).minus(Pair(0, 0))

    fun calculateNumberOfFlashesAtDayHundred(inputs: List<String>): Int {
        val octopuses = parseInputs(inputs)

        return (1..100).sumOf {
            octopuses.forEach { octopuses.increaseTimer(it) }
            octopuses.resetAndReturnFlashed()
        }
    }

    fun calculateFirstDayOfSynchronizedFlashes(inputs: List<String>): Int {
        val octopuses = parseInputs(inputs)

        return generateSequence(1, Int::inc).first {
            octopuses.forEach { octopuses.increaseTimer(it) }
            octopuses.resetAndReturnFlashed() == octopuses.size
        }
    }

    private fun List<Octopus>.increaseTimer(octopus: Octopus) {
        if (octopus.flashed) return

        octopus.timer++

        if (octopus.timer <= 9) return

        octopus.flashed = true

        for (neighbour in this.getNeighbours(octopus)) {
            increaseTimer(neighbour)
        }
    }

    private fun List<Octopus>.getNeighbours(octopus: Octopus): List<Octopus> {
        return neighboursLocations.mapNotNull {
            val x = octopus.x + it.first
            val y = octopus.y + it.second

            if (x < 0 || y < 0 || x >= 10) return@mapNotNull null

            this.getOrNull(10 * y + x)
        }
    }

    private fun List<Octopus>.resetAndReturnFlashed(): Int {
        var flashes = 0
        this.filter { it.flashed }.forEach {
            it.timer = 0
            it.flashed = false
            flashes++
        }
        return flashes
    }

    private fun parseInputs(inputs: List<String>): List<Octopus> {
        return inputs.flatMapIndexed { y, row ->
            row.chunked(1).mapIndexed { x, timer -> Octopus(x, y, timer.toInt()) }
        }
    }
}

fun main() {
    val input = DataParser.parseStrings("day11.txt")

    println("Solutions")
    println("Part one: ${Day11.calculateNumberOfFlashesAtDayHundred(input)}")
    println("Part two: ${Day11.calculateFirstDayOfSynchronizedFlashes(input)}")
}