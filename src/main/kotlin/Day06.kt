import java.math.BigInteger

object Day06 {

    fun calculateNumberOfLanternfish(inputs: List<Int>, days: Int): BigInteger {
        var lanternfishState = MutableList(9) { BigInteger.ZERO }
        inputs.forEach { lanternfishState[it]++ }

        repeat(days) {
            val newLanternfishState = MutableList(9) { BigInteger.ZERO }

            (8 downTo 0).forEach {
                if (it == 0) {
                    newLanternfishState[6] += lanternfishState[0]
                    newLanternfishState[8] += lanternfishState[0]
                    return@forEach
                }

                newLanternfishState[it - 1] += lanternfishState[it]
            }

            lanternfishState = newLanternfishState
        }

        return lanternfishState.sumOf { it }
    }
}

fun main() {
    val input = DataParser.parseInts("day06.txt", separator = ",")

    println("Solutions")
    println("Part one: ${Day06.calculateNumberOfLanternfish(input, 80)}")
    println("Part two: ${Day06.calculateNumberOfLanternfish(input, 256)}")
}
