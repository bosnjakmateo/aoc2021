import Day02.calculateSubmarinePosition
import Day02.calculateSubmarinePositionWithAngle

object Day02 {

    fun calculateSubmarinePosition(rawCommands: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0

        rawCommands.forEach {
            val (command, value) = parseCommand(it)

            when (command) {
                "forward" -> horizontalPosition += value
                "down" -> depth += value
                "up" -> depth -= value
            }
        }

        return horizontalPosition * depth
    }


    fun calculateSubmarinePositionWithAngle(rawCommands: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0

        rawCommands.forEach {
            val (command, value) = parseCommand(it)

            when (command) {
                "forward" -> {
                    horizontalPosition += value
                    depth += aim * value
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }

        return horizontalPosition * depth
    }

    private fun parseCommand(rawCommand: String): Pair<String, Int> {
        val separatedCommands = rawCommand.split(" ")

        return Pair(separatedCommands[0], separatedCommands[1].toInt())
    }
}

fun main() {
    val rawCommands = DataParser.parseStrings("day02.txt")

    println("Day two solutions")
    println("Part one: ${calculateSubmarinePosition(rawCommands)}")
    println("Part two: ${calculateSubmarinePositionWithAngle(rawCommands)}")
}
