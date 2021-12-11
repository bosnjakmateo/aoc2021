object Utils {

    fun <T> MutableMap<T, Int>.incrementOrSetToOne(key: T) {
        if (this.containsKey(key)) {
            this[key] = this[key]!! + 1
        } else {
            this[key] = 1
        }
    }

    fun permutations(vararg values: Int): List<Pair<Int, Int>> {
        return values.flatMap { i -> values.map { j-> Pair(i, j) } }
    }

    inline fun <T> Iterable<T>.one(predicate: (T) -> Boolean): Boolean {
        if (this is Collection && isEmpty()) return true
        return !this.all(predicate) && this.any(predicate)
    }
}