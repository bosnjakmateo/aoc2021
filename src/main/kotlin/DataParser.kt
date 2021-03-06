import java.io.File
import java.lang.System.lineSeparator

object DataParser {

    fun parseInts(fileName: String, separator: String = lineSeparator()) =
        File(DataParser::class.java.getResource("/inputs/${fileName}")!!.toURI())
            .readText()
            .split(separator)
            .map { it.toInt() }

    fun parseStrings(fileName: String, filterOutNewLines: Boolean = false): List<String> {
        val inputs = File(DataParser::class.java.getResource("/inputs/${fileName}")!!.toURI())
            .readText()
            .split(lineSeparator())
            .map { it.trim() }

        return if (filterOutNewLines) {
            inputs.filter { it != "" }
        } else {
            inputs
        }
    }

    fun parseString(fileName: String): String {
        return File(DataParser::class.java.getResource("/inputs/${fileName}")!!.toURI())
            .readText()
            .split(lineSeparator())
            .map { it.trim() }
            .first()
    }
}