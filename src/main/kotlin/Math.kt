object Math {

    data class Point(
        val x: Int,
        val y: Int,
    ) {
        fun neighbours(): List<Point> {
            return listOf(
                Point(x + 1, y),
                Point(x - 1, y),
                Point(x, y + 1),
                Point(x, y - 1),
            )
        }
    }
}