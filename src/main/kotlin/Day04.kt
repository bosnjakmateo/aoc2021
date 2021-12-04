class BingoBoard {

    private val horizontalState: MutableMap<Int, Int> = mutableMapOf(0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0)
    private val verticalState: MutableMap<Int, Int> = mutableMapOf(0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0)
    private val bingoFields: MutableList<BingoFiled> = mutableListOf()

    fun addBingoField(number: Int, horizontalIndex: Int, verticalIndex: Int) {
        bingoFields.add(BingoFiled(number, horizontalIndex, verticalIndex))
    }

    fun markNumber(number: Int) {
        bingoFields.firstOrNull { it.number == number }?.apply {
            isMarked = true
            horizontalState[horizontalIndex] = horizontalState[horizontalIndex]!! + 1
            verticalState[verticalIndex] = verticalState[verticalIndex]!! + 1
        }
    }

    fun isWinner() = horizontalState.any { it.value == 5 } || verticalState.any { it.value == 5 }

    fun sumOfUnmarkedNumbers() = bingoFields.filter { !it.isMarked }.sumOf { it.number }

    data class BingoFiled(
        val number: Int,
        val horizontalIndex: Int,
        val verticalIndex: Int,
        var isMarked: Boolean = false
    )
}

object Day04 {

    fun calculateBingoScoreOfFirstWinner(inputs: List<String>): Int {
        val drawnNumbers = inputs.first().split(",").map { it.toInt() }
        val bingoBoards = buildBingoBoards(inputs)

        var winningBingoBoard: BingoBoard? = null
        var winningNumber = 0

        for (drawnNumber in drawnNumbers) {
            if (winningBingoBoard != null) {
                break
            }

            for (bingoBoard in bingoBoards) {
                bingoBoard.markNumber(drawnNumber)

                if (bingoBoard.isWinner()) {
                    winningBingoBoard = bingoBoard
                    winningNumber = drawnNumber
                    break
                }
            }
        }

        return winningBingoBoard!!.sumOfUnmarkedNumbers() * winningNumber
    }

    fun calculateBingoScoreOfLastWinner(inputs: List<String>): Int {
        val drawnNumbers = inputs.first().split(",").map { it.toInt() }
        val bingoBoards = buildBingoBoards(inputs)

        var winningBingoBoard: BingoBoard? = null
        var winningNumber = 0

        for (drawnNumber in drawnNumbers) {
            for (bingoBoard in bingoBoards) {
                if (bingoBoard.isWinner()) {
                    continue
                }

                bingoBoard.markNumber(drawnNumber)

                if (bingoBoard.isWinner()) {
                    winningBingoBoard = bingoBoard
                    winningNumber = drawnNumber
                }
            }
        }

        return winningBingoBoard!!.sumOfUnmarkedNumbers() * winningNumber
    }

    private fun buildBingoBoards(inputs: List<String>) =
        inputs
            .drop(1)
            .windowed(size = 5, step = 5)
            .map { rawBingoBoard ->
                val bingoBoard = BingoBoard()

                rawBingoBoard.forEachIndexed { horizontalIndex, row ->
                    row.trim().split(Regex("( )+")).forEachIndexed { verticalIndex, number ->
                        bingoBoard.addBingoField(number.toInt(), horizontalIndex, verticalIndex)
                    }
                }

                bingoBoard
            }
}

fun main() {
    val input = DataParser.parseStrings("day04.txt", filterOutNewLines = true)

    println("Day three solutions")
    println("Part one: ${Day04.calculateBingoScoreOfFirstWinner(input)}")
    println("Part two: ${Day04.calculateBingoScoreOfLastWinner(input)}")
}
