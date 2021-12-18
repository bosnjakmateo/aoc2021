object Extensions {

    fun <T> MutableMap<T, Int>.incrementOrSetToOne(key: T) {
        if (this.containsKey(key)) {
            this[key] = this[key]!! + 1
        } else {
            this[key] = 1
        }
    }

    fun <T> MutableMap<T, Long>.plus(key: T, amount: Long) {
        this[key] = this.getOrDefault(key, 0L) + amount
    }

    fun Iterator<Char>.next(size: Int) = (1..size).map { next() }.joinToString("")

    fun <T> Iterator<Char>.executeUntilEmpty(function: (Iterator<Char>) -> T): List<T> {
        val output = mutableListOf<T>()
        while (this.hasNext()) {
            output.add(function(this))
        }
        return output
    }

    fun Iterator<Char>.nextUntilFirst(size: Int, stopCondition: (String) -> Boolean): List<String> {
        val output = mutableListOf<String>()
        do {
            val readValue = next(size)
            output.add(readValue)
        } while (!stopCondition(readValue))
        return output
    }

    fun Array<Array<String>>.print() {
        this.forEach { row ->
            row.forEach { point ->
                print(point)
            }
            println("")
        }
    }
}