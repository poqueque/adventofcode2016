package days

class Day16 : Day(16) {

    override fun partOne(): Any {
        var state = listOf(true,false,true,true,true,true,false,false,true,true,false,false,false,true,true,true,true)
        val length = 272

        while (state.size < length) {
            state = dragonCurve(state)
        }
        state = state.take(length)
        var c = checksum(state)
        while (c.size % 2 == 0) c = checksum(c)
        return c.joinToString("") { if (it) "1" else "0" }
    }

    private fun checksum(data: List<Boolean>): List<Boolean> {
        val res = mutableListOf<Boolean>()
        for (i in 0 until data.size / 2) {
            res.add(data[2 * i] == data[2 * i + 1])
        }
        return res
    }

    private fun dragonCurve(state: List<Boolean>): List<Boolean> {
        val b = state.reversed().map{ !it }
        return state + listOf(false) + b
    }

    override fun partTwo(): Any {
        var state = listOf(true,false,true,true,true,true,false,false,true,true,false,false,false,true,true,true,true)
        val length = 35651584

        while (state.size < length) {
            state = dragonCurve(state)
        }
        state = state.take(length)
        var c = checksum(state)
        while (c.size % 2 == 0) {
            c = checksum(c)
        }
        return c.joinToString("") { if (it) "1" else "0" }
    }
}
