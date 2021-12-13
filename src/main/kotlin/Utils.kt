object Utils {

    fun <T> MutableMap<T, Int>.incrementOrSetToOne(key: T) {
        if (this.containsKey(key)) {
            this[key] = this[key]!! + 1
        } else {
            this[key] = 1
        }
    }

    fun permutations(vararg values: Int): List<Pair<Int, Int>> {
        return values.flatMap { i -> values.map { j -> Pair(i, j) } }
    }

    fun Array<Array<String>>.print() {
        this.forEach { row ->
            row.forEach { point ->
                print(point)
            }
            println("")
        }
    }

    fun build2dArray(rowSize: Int, columnSize: Int, value: String): Array<Array<String>> {
        return (0..columnSize).map { (0..rowSize).map { value }.toTypedArray() }.toTypedArray()
    }

    inline fun <T> Iterable<T>.one(predicate: (T) -> Boolean): Boolean {
        if (this is Collection && isEmpty()) return true
        return !this.all(predicate) && this.any(predicate)
    }
}