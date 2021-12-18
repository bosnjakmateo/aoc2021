import Day13.FoldDirection.X
import Day13.FoldDirection.Y
import Extensions.print

object Day13 {

    data class Point(
        val x: Int,
        val y: Int,
    )

    data class FoldInstruction(
        val direction: FoldDirection,
        val value: Int,
    )

    enum class FoldDirection { X, Y }

    fun calculateVisibleDotsAfterOneFold(inputs: List<String>) = foldPaper(inputs, 1).size

    fun printCode(inputs: List<String>) = foldPaper(inputs, null).printPaperAsArray()

    private fun foldPaper(inputs: List<String>, numberOfFolds: Int?): Set<Point> {
        val (points, foldInstructions) = parseInputs(inputs)

        return (0 until (numberOfFolds ?: (foldInstructions.size))).scan(points) { foldedPaper, foldStep ->
            val foldInstruction = foldInstructions[foldStep]
            foldedPaper.map { point ->
                when (foldInstruction.direction) {
                    X -> {
                        if (point.x <= foldInstruction.value) {
                            point
                        } else {
                            point.copy(x = foldInstruction.value - (point.x - foldInstruction.value))
                        }
                    }
                    Y -> {
                        if (point.y <= foldInstruction.value) {
                            point
                        } else {
                            point.copy(y = foldInstruction.value - (point.y - foldInstruction.value))
                        }
                    }
                }
            }.toSet()
        }.last()
    }

    private fun Set<Point>.printPaperAsArray() {
        val rowSize = this.maxOf { it.x }
        val columnSize = this.maxOf { it.y }

        val array = Utils.build2dArray(rowSize, columnSize, " ")

        this.forEach { array[it.y][it.x] = "#" }

        array.print()
    }

    private fun parseInputs(inputs: List<String>): Pair<Set<Point>, List<FoldInstruction>> {
        val (rawPoints, rawFolds) = inputs.filter { it.isEmpty() }.partition { !it.contains("fold") }
        return Pair(
            rawPoints.map {
                val split = it.split(",")
                Point(x = split[0].toInt(), y = split[1].toInt())
            }.toSet(),
            rawFolds.map {
                val split = it.split(" ").last().split("=")
                FoldInstruction(direction = if (split[0] == "x") X else Y, value = split[1].toInt())
            }
        )
    }
}

fun main() {
    val input = DataParser.parseStrings("day13.txt")

    println("Solutions")
    println("Part one: ${Day13.calculateVisibleDotsAfterOneFold(input)}")
    println("Part two: ${Day13.printCode(input)}")
}