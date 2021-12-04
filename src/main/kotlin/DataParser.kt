import java.io.File
import java.lang.System.lineSeparator

object DataParser {

    fun parseInts(fileName: String) =
        File(DataParser::class.java.getResource("/inputs/${fileName}")!!.toURI())
            .readText()
            .split(lineSeparator())
            .map { it.toInt() }

    fun parseStrings(fileName: String, filterOutNewLines: Boolean = false): List<String> {
        val inputs = File(DataParser::class.java.getResource("/inputs/${fileName}")!!.toURI())
            .readText()
            .split(lineSeparator())

        return if (filterOutNewLines) {
            inputs.filter { it != "" }
        } else {
            inputs
        }
    }
}