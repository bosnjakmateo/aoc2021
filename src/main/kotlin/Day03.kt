object Day03 {

    fun calculatePowerConsumption(inputs: List<String>): Int {
        val commonBitsState = List(inputs.first().length) { 0 }.toMutableList()

        inputs.forEach { input ->
            input.forEachIndexed { index, c ->
                when (c) {
                    '0' -> commonBitsState[index]--
                    '1' -> commonBitsState[index]++
                }
            }
        }

        val (gamma, epsilon) = buildGammaAndEpsilonBits(commonBitsState)

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    private fun buildGammaAndEpsilonBits(commonBits: MutableList<Int>): Pair<String, String> {
        val gammaBits = mutableListOf<String>()
        val epsilonBits = mutableListOf<String>()

        commonBits.forEach {
            when {
                it < 0 -> {
                    gammaBits.add("0")
                    epsilonBits.add("1")
                }
                it > 0 -> {
                    gammaBits.add("1")
                    epsilonBits.add("0")
                }
            }
        }

        return Pair(gammaBits.joinToString(""), epsilonBits.joinToString(""))
    }

    fun calculateLifeSupportRating(inputs: List<String>): Int {
        val oxygenGeneratorRating = calculateRating(
            inputs,
            index = 0
        ) { commonBitState: Int -> if (commonBitState < 0) '0' else '1' }

        val co2Rating = calculateRating(
            inputs,
            index = 0
        ) { commonBitState: Int -> if (commonBitState < 0) '1' else '0' }

        return oxygenGeneratorRating.toInt(2) * co2Rating.toInt(2)
    }

    private fun calculateRating(
        inputs: List<String>,
        index: Int,
        commonBitResolver: (commonBitState: Int) -> Char
    ): String {
        if (inputs.size == 1) {
            return inputs.first()
        }

        var commonBitState = 0

        inputs.forEach {
            when (it[index]) {
                '0' -> commonBitState--
                '1' -> commonBitState++
            }
        }

        val commonBit = commonBitResolver(commonBitState)

        return calculateRating(inputs.filter { it[index] == commonBit }, index + 1, commonBitResolver)
    }
}

fun main() {
    val input = DataParser.parseStrings("day03.txt")

    println("Solutions")
    println("Part one: ${Day03.calculatePowerConsumption(input)}")
    println("Part two: ${Day03.calculateLifeSupportRating(input)}")
}
