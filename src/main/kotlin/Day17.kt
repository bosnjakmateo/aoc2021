import Day13.Point
import kotlin.math.absoluteValue

object Day17 {

    data class TargetArea(
        val xBorder: IntRange,
        val yBorder: IntRange,
    ) {
        fun hasNotBeenOvershot(currentPosition: Point) =
            currentPosition.x <= xBorder.last && currentPosition.y >= yBorder.first

        fun hasBeenReached(currentPosition: Point) = currentPosition.x in xBorder && currentPosition.y in yBorder
    }

    fun calculateHighestYPosition(inputs: List<Int>) = calculateProbeData(inputs).first

    fun calculateNumberOfHits(inputs: List<Int>) = calculateProbeData(inputs).second

    private fun calculateProbeData(inputs: List<Int>): Pair<Int, Int> {
        val targetArea = parseInput(inputs)
        var highestY = 0
        var numberOfHits = 0

        (0..targetArea.xBorder.last).forEach { x ->
            (targetArea.yBorder.first..targetArea.yBorder.first.absoluteValue).forEach { y ->
                val currentPosition = Point(0, 0)
                val velocity = Point(x, y)
                var currentHighestY = 0

                while (targetArea.hasNotBeenOvershot(currentPosition)) {
                    currentPosition.applyVelocity(velocity)

                    if (currentPosition.y > currentHighestY) {
                        currentHighestY = currentPosition.y
                    }

                    if (targetArea.hasBeenReached(currentPosition)) {
                        numberOfHits++
                        highestY = currentHighestY.takeIf { currentHighestY > highestY } ?: highestY
                        break
                    }

                    velocity.applyDrag()
                }
            }
        }

        return highestY to numberOfHits
    }

    private fun Point.applyVelocity(velocity: Point) {
        x += velocity.x
        y += velocity.y
    }

    private fun Point.applyDrag() {
        if (x < 0) x += 1 else if (x > 0) x -= 1
        y -= 1
    }

    private fun parseInput(input: List<Int>) =
        TargetArea(IntRange(input[0], input[1]), IntRange(input[2], input[3]))
}

fun main() {
    val input = DataParser.parseInts("day17.txt", separator = ",")

    println("Solutions")
    println("Part one: ${Day17.calculateHighestYPosition(input)}")
    println("Part two: ${Day17.calculateNumberOfHits(input)}")
}