import Math.Point
import java.util.*

object Day15 {

    class Traversal(
        val point: Point,
        val totalRisk: Int
    ) : Comparable<Traversal> {
        override fun compareTo(other: Traversal): Int = this.totalRisk - other.totalRisk
    }

    fun calculateRiskLevel(inputs: List<String>, caveSizeMultiplication: Int = 1): Int {
        val cavern = parseInputs(inputs)
        val destination = Point(
            (cavern.first().size * caveSizeMultiplication) - 1,
            (cavern.size * caveSizeMultiplication) - 1
        )

        val toBeEvaluated = PriorityQueue<Traversal>().apply { add(Traversal(Point(0, 0), 0)) }
        val visited = mutableSetOf<Point>()

        while (toBeEvaluated.isNotEmpty()) {
            val current = toBeEvaluated.poll()

            if (current.point == destination) {
                return current.totalRisk
            }

            if (!visited.contains(current.point)) {
                visited.add(current.point)

                current.point
                    .neighbours()
                    .filter { it.x in (0..destination.x) && it.y in (0..destination.y) }
                    .forEach { toBeEvaluated.offer(Traversal(it, current.totalRisk + getRiskForPoint(cavern, it))) }
            }
        }

        error("This should not happen :)")
    }

    private fun getRiskForPoint(cavern: Array<IntArray>, point: Point): Int {
        val xChange = point.x / cavern.first().size
        val yChange = point.y / cavern.size
        val originalRisk = cavern[point.y % cavern.size][point.x % cavern.first().size]
        val newRisk = originalRisk + xChange + yChange

        return newRisk.takeIf { it < 10 } ?: (newRisk % 9)
    }

    private fun parseInputs(inputs: List<String>): Array<IntArray> {
        return inputs.map { row ->
            row.map { risk ->
                risk.digitToInt()
            }.toIntArray()
        }.toTypedArray()
    }
}

fun main() {
    val input = DataParser.parseStrings("day15.txt")

    println("Solutions")
    println("Part one: ${Day15.calculateRiskLevel(input)}")
    println("Part two: ${Day15.calculateRiskLevel(input, 5)}")
}