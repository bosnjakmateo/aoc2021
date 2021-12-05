object Utils {

    fun <T> MutableMap<T, Int>.incrementOrSetToOne(key: T) {
        if (this.containsKey(key)) {
            this[key] = this[key]!! + 1
        } else {
            this[key] = 1
        }
    }
}