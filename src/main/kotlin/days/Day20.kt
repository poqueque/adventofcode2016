package days

class Day20 : Day(20) {

    private val ranges = mutableListOf<Pair<Long, Long>>()
    private val max = 4294967295

    init {
        inputList.forEach { range ->
            val d = range.split("-").map { it.toLong() }
            ranges.add(Pair(d[0], d[1]))
        }
    }

    override fun partOne(): Any {
        for (i in 0 until max )
            if (isValidIP(i)) return i
        return 0
    }

    private fun isValidIP(i: Long): Boolean {
        for (range in ranges)
            if (i in range.first..range.second)
                return false
        return true
    }

    override fun partTwo(): Any {
        val validRanges = mutableListOf(Pair(0L, max))
        var i = 0
        for (range in ranges) {
            i++
            for (validRange in validRanges.toList()) {
                if (range.first >= validRange.first && range.first <= validRange.second
                    && range.second >= validRange.second
                ) {
                    validRanges.remove(validRange)
                    if (validRange.first <= range.first - 1)
                        validRanges.add(Pair(validRange.first, range.first - 1))
                } else if (range.first <= validRange.first && range.second >= validRange.first
                    && range.second <= validRange.second
                ) {
                    validRanges.remove(validRange)
                    if (range.second + 1 <= validRange.second)
                        validRanges.add(Pair(range.second + 1, validRange.second))
                } else if (range.first >= validRange.first &&
                    range.first <= validRange.second &&
                    range.second >= validRange.first &&
                    range.second <= validRange.second
                ) {
                    validRanges.remove(validRange)
                    if (validRange.first <= range.first - 1)
                        validRanges.add(Pair(validRange.first, range.first - 1))
                    if (range.second + 1 <= validRange.second)
                        validRanges.add(Pair(range.second + 1, validRange.second))
                } else if (range.first <= validRange.first && range.second >= validRange.second) {
                    validRanges.remove(validRange)
                }

            }
        }
        return validRanges.sumOf { it.second - it.first +1 }
    }
}
