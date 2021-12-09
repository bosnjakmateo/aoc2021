object Day09 {

    data class Height(
        val height: Int,
        var visited: Boolean = false,
    )

    private val possibleNeighbours = listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

    fun calculateSumOfRiskLevels(inputs: List<String>): Int {
        val rowSize = inputs.size
        val columnSize = inputs[0].length
        val heightMap = parseInputs(inputs)

        return (0 until rowSize).flatMap { rowIndex ->
            (0 until columnSize).mapNotNull { columnIndex ->
                val currentHeight = heightMap[rowIndex][columnIndex]

                val isLowestOfItsNeighbours = possibleNeighbours.all {
                    val neighbour = heightMap.getNeighbour(rowIndex, columnIndex, it) ?: return@all true

                    currentHeight.height < neighbour.height
                }

                (currentHeight.height + 1).takeIf { isLowestOfItsNeighbours }
            }
        }.sum()
    }

    fun calculateSumOfBasinSizes(inputs: List<String>): Int {
        val rowSize = inputs.size
        val columnSize = inputs[0].length
        val heightMap = parseInputs(inputs)

        return (0 until rowSize).flatMap { rowIndex ->
            (0 until columnSize).mapNotNull { columnIndex ->
                val currentHeight = heightMap[rowIndex][columnIndex]

                val isLowestOfItsNeighbours = possibleNeighbours.all {
                    val neighbour = heightMap.getNeighbour(rowIndex, columnIndex, it) ?: return@all true

                    currentHeight.height < neighbour.height
                }

                calculateBasinSize(heightMap.copyOf(), rowIndex, columnIndex).takeIf { isLowestOfItsNeighbours }
            }
        }.sortedDescending().take(3).reduce { acc, i -> acc * i }
    }

    private fun calculateBasinSize(heightMap: Array<Array<Height>>, rowIndex: Int, columnIndex: Int): Int {
        heightMap[rowIndex][columnIndex].visited = true

        return 1 + possibleNeighbours.sumOf {
            val neighbour = heightMap.getNeighbour(rowIndex, columnIndex, it) ?: return@sumOf 0

            if (neighbour.visited || neighbour.height == 9) {
                return@sumOf 0
            }

            calculateBasinSize(heightMap, rowIndex + it.first, columnIndex + it.second)
        }
    }

    private fun Array<Array<Height>>.getNeighbour(
        rowIndex: Int,
        columnIndex: Int,
        possibleNeighbour: Pair<Int, Int>
    ) = this.getOrNull(rowIndex + possibleNeighbour.first)?.getOrNull(columnIndex + possibleNeighbour.second)

    private fun parseInputs(inputs: List<String>) =
        inputs.map { row -> row.chunked(1).map { height -> Height(height.toInt()) }.toTypedArray() }.toTypedArray()
}

fun main() {
    val input = DataParser.parseStrings("day09.txt")

    println("Solutions")
    println("Part one: ${Day09.calculateSumOfRiskLevels(input)}")
    println("Part one: ${Day09.calculateSumOfBasinSizes(input)}")
}