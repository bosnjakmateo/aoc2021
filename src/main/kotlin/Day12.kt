object Day12 {

    fun calculateNumberOfPaths(inputs: List<String>): Int {
        val paths = parseInputs(inputs)
        return calculateAllPossiblePaths("start", paths, mutableListOf(), false)
    }

    fun calculateNumberOfPathsWithTimeWasting(inputs: List<String>): Int {
        val paths = parseInputs(inputs)
        return calculateAllPossiblePaths("start", paths, mutableListOf(), true)
    }

    private fun calculateAllPossiblePaths(
        cave: String,
        paths: Map<String, List<String>>,
        currentPath: MutableList<String>,
        timeWasting: Boolean
    ): Int {
        val numberOfAllowedSmallCavesVisits = resolveSmallCaveVisitsAllowed(currentPath, timeWasting)

        when {
            cave.isSmallCave() && currentPath.isOverSmallCaveVisitLimit(cave, numberOfAllowedSmallCavesVisits) ||
            cave == "start" && currentPath.contains(cave) -> return 0
            cave == "end" -> return 1
        }

        currentPath.add(cave)

        return paths[cave]?.sumOf {
            calculateAllPossiblePaths(
                it,
                paths,
                mutableListOf<String>().plus(currentPath).toMutableList(),
                timeWasting
            )
        } ?: return 0
    }

    private fun resolveSmallCaveVisitsAllowed(
        currentPath: MutableList<String>,
        timeWasting: Boolean,
    ): Int {
        return when {
            !timeWasting || currentPath.groupBy { it }.any { it.value.size == 2 && it.value[0].isSmallCave() } -> 1
            else -> 2
        }
    }

    private fun MutableList<String>.isOverSmallCaveVisitLimit(cave: String, limit: Int) =
        this.count { it == cave } >= limit

    private fun String.isSmallCave() = all { it.isLowerCase() }

    private fun parseInputs(inputs: List<String>): Map<String, List<String>> {
        return inputs.flatMap { input ->
            val split = input.split("-")
            listOf(Pair(split[0], split[1]), Pair(split[1], split[0]))
        }.groupBy({ it.first }, { it.second })
    }
}

fun main() {
    val input = DataParser.parseStrings("day12.txt")

    println("Solutions")
    println("Part one: ${Day12.calculateNumberOfPaths(input)}")
    println("Part two: ${Day12.calculateNumberOfPathsWithTimeWasting(input)}")
}