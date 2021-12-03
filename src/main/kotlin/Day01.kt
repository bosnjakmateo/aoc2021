import Day01.calculateIncrease

object Day01 {
    fun calculateIncrease(measurements: List<Int>, windowSize: Int): Int {
        return measurements
            .windowed(windowSize)
            .zipWithNext()
            .count { it.second.sum() > it.first.sum() }
    }
}

fun main() {
    val measurements = DataParser.parseInts("day01.txt")

    println("Day one solutions")
    println("Part one: ${calculateIncrease(measurements, 1)}")
    println("Part two: ${calculateIncrease(measurements, 3)}")
}
