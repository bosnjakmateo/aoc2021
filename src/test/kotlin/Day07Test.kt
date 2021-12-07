import org.junit.Test
import kotlin.test.assertEquals

class Day07Test {

    private val input = DataParser.parseInts("day07.txt", separator = ",")

    @Test
    fun `should calculate fuel consumption at constant rate`() {
        assertEquals(37, Day07.calculateFuelConsumptionAtConstantRate(input))
    }

    @Test
    fun `should calculate fuel consumption at incremental`() {
        assertEquals(168, Day07.calculateFuelConsumptionAtIncrementalRate(input))
    }
}