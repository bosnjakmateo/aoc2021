@file:Suppress("MoveVariableDeclarationIntoWhen")

import Extensions.executeUntilEmpty
import Extensions.next
import Extensions.nextUntilFirst

object Day16 {

    class Result(
        var version: Int = 0,
        var value: Long = 0
    )

    fun calculateSumOfVersionNumbers(input: String): Int {
        val bits = convertToBits(input)
        return resolvePackets(bits).version
    }

    fun calculateBitsTransmission(input: String): Long {
        val bits = convertToBits(input)
        return resolvePackets(bits).value
    }

    private fun resolvePackets(bits: Iterator<Char>): Result {
        val version = bits.next(3).toInt(2)
        var result = Result(version = version)

        val packetTypeId = bits.next(3).toInt(2)
        when (packetTypeId) {
            4 -> result.value = bits.readLiteralValue()
            else -> {
                val lengthTypeId = bits.next(1).toInt(2)

                when (lengthTypeId) {
                    0 -> bits.getSubPackets().executeUntilEmpty { resolvePackets(it) }.also {
                        result = calculateResult(packetTypeId, result, it)
                    }
                    1 -> (1..bits.getNumberOfSubPackets()).map { resolvePackets(bits) }.also {
                        result = calculateResult(packetTypeId, result, it)
                    }
                }
            }
        }

        return result
    }

    private fun Iterator<Char>.readLiteralValue() =
        this.nextUntilFirst(5) { it.startsWith("0") }.joinToString("") { it.drop(1) }.toLong(2)

    private fun Iterator<Char>.getSubPackets(): Iterator<Char> {
        val lengthOfSubPackets = this.next(15).toInt(2)
        return this.next(lengthOfSubPackets).iterator()
    }

    private fun Iterator<Char>.getNumberOfSubPackets() = this.next(11).toInt(2)

    private fun calculateResult(packetTypeId: Int, result: Result, results: List<Result>) =
        result.apply {
            version += results.sumOf { it.version }

            value = when (packetTypeId) {
                0 -> results.sumOf { it.value }
                1 -> results.fold(1L) { acc, next -> acc * next.value }
                2 -> results.minOf { it.value }
                3 -> results.maxOf { it.value }
                5 -> if (results[0].value > results[1].value) 1 else 0
                6 -> if (results[0].value < results[1].value) 1 else 0
                7 -> if (results[0].value == results[1].value) 1 else 0
                else -> error("Invalid packet type")
            }
        }

    private fun convertToBits(input: String) =
        input.map { it.digitToInt(16).toString(2).padStart(4, '0') }.flatMap { it.toList() }.iterator()
}

fun main() {
    val input = DataParser.parseString("day16.txt")

    println("Solutions")
    println("Part one: ${Day16.calculateSumOfVersionNumbers(input)}")
    println("Part two: ${Day16.calculateBitsTransmission(input)}")
}