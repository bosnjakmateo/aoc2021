import Utils.incrementOrSetToOne
import kotlin.math.abs
import kotlin.math.max

data class Point(
    val x: Int,
    val y: Int,
)

object Day05 {

    private val pointRegex = "(\\d+,\\d+)".toRegex()

    fun calculateNumberOfLineOverlapPoints(inputs: List<String>, ignoreDiagonalLines: Boolean = true): Int {
        val hydrothermalVentsState = mutableMapOf<Point, Int>()

        inputs.forEach { input ->
            val (pointA, pointB) = parsePoints(input)

            if (ignoreDiagonalLines && isLineDiagonal(pointA, pointB)) {
                return@forEach
            }

            val linePoints = calculateAllLinePoints(pointA, pointB)

            linePoints.forEach { hydrothermalVentsState.incrementOrSetToOne(key = it) }
        }

        return hydrothermalVentsState.values.count { it > 1 }
    }

    private fun parsePoints(input: String): Pair<Point, Point> {
        val match = pointRegex.findAll(input)

        val firstGroup = match.toList()[0].groupValues[0].split(",")
        val secondGroup = match.toList()[1].groupValues[1].split(",")

        return Pair(
            Point(firstGroup[0].toInt(), firstGroup[1].toInt()),
            Point(secondGroup[0].toInt(), secondGroup[1].toInt())
        )
    }

    private fun isLineDiagonal(pointA: Point, pointB: Point): Boolean {
        return pointA.x != pointB.x && pointA.y != pointB.y
    }

    private fun calculateAllLinePoints(pointA: Point, pointB: Point): List<Point> {
        val xIncrease = calculateIncreaseValue(pointA.x, pointB.x)
        val yIncrease = calculateIncreaseValue(pointA.y, pointB.y)

        val maxDifference = calculateMaxDifference(pointA, pointB)

        return (0..maxDifference).map {
            Point(pointA.x + (xIncrease * it), pointA.y + (yIncrease * it))
        }
    }

    private fun calculateIncreaseValue(axisPointA: Int, axisPointB: Int): Int {
        return when {
            axisPointA > axisPointB -> -1
            axisPointA < axisPointB -> 1
            else -> 0
        }
    }

    private fun calculateMaxDifference(pointA: Point, pointB: Point): Int {
        return max(abs(pointA.x - pointB.x), abs(pointA.y - pointB.y))
    }
}

fun main() {
    val input = DataParser.parseStrings("day05.txt")

    println("Solutions")
    println("Part one: ${Day05.calculateNumberOfLineOverlapPoints(input)}")
    println("Part two: ${Day05.calculateNumberOfLineOverlapPoints(input, ignoreDiagonalLines = false)}")
}
