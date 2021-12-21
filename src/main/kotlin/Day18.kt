import java.lang.Math

typealias SnailNumbers = MutableList<Any>

object Day18 {

    object LeftBorder
    object RightBorder

    fun calculateMagnitudeOfFinalSum(inputs: List<String>): Int {
        val snailNumbers = parseInputs(inputs)

        return sumNumbersAndCalculateMagnitude(snailNumbers)
    }

    fun calculateHighestMagnitudeOfFinalSumUsingTwoNumbers(inputs: List<String>): Int {
        val snailNumbers = parseInputs(inputs)

        val permutations = snailNumbers.mapIndexed { index, left ->
            snailNumbers.drop(index + 1).map { right ->
                listOf(left to right, right to left)
            }.flatten()
        }.flatten().map { listOf(it.first, it.second) }

        return permutations.maxOf { sumNumbersAndCalculateMagnitude(it) }
    }

    private fun sumNumbersAndCalculateMagnitude(snailNumbers: List<SnailNumbers>): Int {
        val mutableSnailNumbers = snailNumbers.map { numbers -> numbers.map { it }.toMutableList() }.toMutableList()

        while (true) {
            mutableSnailNumbers.forEach { it.reduce() }

            if (mutableSnailNumbers.size == 1) {
                break
            }

            mutableSnailNumbers.first().addAll(mutableSnailNumbers[1])
            mutableSnailNumbers.first().add(0, LeftBorder)
            mutableSnailNumbers.first().add(mutableSnailNumbers.first().lastIndex, RightBorder)
            mutableSnailNumbers.removeAt(1)
        }

        return mutableSnailNumbers.first().calculateMagnitude()
    }

    private fun SnailNumbers.reduce() {
        while (true) {
            val indices = getIndicesOfPairs()
            var explosion = false

            for (index in indices) {
                val (firstNumberIndex, secondNumberIndex) = index
                if (hasFourNestingsToTheLeft(firstNumberIndex) || hasFourNestingsToTheRight(secondNumberIndex)) {
                    explodeToRight(secondNumberIndex)
                    explodeToLeft(firstNumberIndex)
                    add(firstNumberIndex - 1, 0)
                    explosion = true
                    break
                }
            }

            if (explosion) continue

            if (this.filterIsInstance<Int>().any { it >= 10 }) {
                val index = findLeftMostIndexForSplit()
                split(index)
                continue
            }

            break
        }
    }

    private fun SnailNumbers.explodeToRight(index: Int) {
        (index + 1 until this.size).forEach {
            if (this[it] is Int) {
                this[it] = (this[it] as Int) + (this[index] as Int)
                this.removeAt(index)
                this.removeAt(index)
                return
            }
        }
        this.removeAt(index)
        this.removeAt(index)
    }

    private fun SnailNumbers.explodeToLeft(index: Int) {
        (index - 1 downTo 0).forEach {
            if (this[it] is Number) {
                this[it] = (this[it] as Int) + (this[index] as Int)
                this.removeAt(index - 1)
                this.removeAt(index - 1)
                return
            }
        }
        this.removeAt(index)
        this.removeAt(index - 1)
    }

    private fun SnailNumbers.split(index: Int) {
        val number = this[index] as Int

        val floor = Math.floorDiv(number, 2)
        val ceil = (number + 1) / 2

        this.addAll(index + 1, listOf(LeftBorder, floor, ceil, RightBorder))
        this.removeAt(index)
    }

    private fun SnailNumbers.calculateMagnitude(): Int {
        while (this.size != 1) {
            val (firstNumberIndex, secondNumberIndex) = getIndicesOfPairs().first()

            val sum = ((this[firstNumberIndex] as Int) * 3) + ((this[secondNumberIndex] as Int) * 2)

            this.removeAt(secondNumberIndex + 1)
            this.removeAt(secondNumberIndex)
            this.removeAt(firstNumberIndex)
            this.removeAt(firstNumberIndex - 1)
            this.add(firstNumberIndex - 1, sum)
        }

        return this[0] as Int
    }

    private fun SnailNumbers.getIndicesOfPairs(): List<Pair<Int, Int>> {
        return (1..this.size - 2).mapNotNull {
            if (this[it] is Int && this[it + 1] is Int) {
                Pair(it, it + 1)
            } else {
                null
            }
        }
    }

    private fun SnailNumbers.hasFourNestingsToTheLeft(index: Int): Boolean {
        this.take(index - 1)
            .filter { it is RightBorder || it is LeftBorder }
            .partition { it is LeftBorder }
            .apply { return first.size - second.size > 3 }
    }

    private fun SnailNumbers.hasFourNestingsToTheRight(index: Int): Boolean {
        this.drop(index + 2)
            .filter { it is RightBorder || it is LeftBorder }
            .partition { it is RightBorder }
            .apply { return first.size - second.size > 3 }
    }

    private fun SnailNumbers.findLeftMostIndexForSplit(): Int {
        this.forEachIndexed { index, snailSign ->
            if (snailSign is Int && snailSign >= 10) {
                return index
            }
        }
        error("Should not happen")
    }

    private fun parseInputs(inputs: List<String>): MutableList<SnailNumbers> {
        return inputs.map { input ->
            input.chunked(1).mapNotNull {
                when (it) {
                    "," -> null
                    "[" -> LeftBorder
                    "]" -> RightBorder
                    else -> it.toInt()
                }
            }.toMutableList()
        }.toMutableList()
    }
}

fun main() {
    val input = DataParser.parseStrings("day18.txt")

    println("Solutions")
    println("Part one: ${Day18.calculateMagnitudeOfFinalSum(input)}")
    println("Part two: ${Day18.calculateHighestMagnitudeOfFinalSumUsingTwoNumbers(input)}")
}