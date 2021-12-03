import java.io.File
import java.lang.System.lineSeparator

object DataParser {
    fun parseInts(fileName: String) =
        File(DataParser::class.java.getResource("/inputs/${fileName}")!!.toURI())
            .readText()
            .split(lineSeparator())
            .map { it.toInt() }
}