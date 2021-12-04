package days

import util.InputReader.getStringsFromExpression
import java.text.FieldPosition

class Day15 : Day(15) {

    data class Disc(val id: String, val positions: Int, val initialPosition: Int) {
        fun positionAt(time: Int) = (initialPosition + time) % positions
    }

    private val discs = mutableListOf<Disc>()

    init {
        inputList.forEach {
            val data = getStringsFromExpression("Disc $ has $ positions; at time=0, it is at position $", it.replace(".",""))
            if (data != null)
                discs.add(Disc(data[0], data[1].toInt(), data[2].toInt()))
        }
    }

    override fun partOne(): Any {
        var t = 0
        while (true) {
            t++
            if (throwBall(t)) return t
        }
    }

    private fun throwBall(start: Int): Boolean {
        var t = start
        var d = 0
        while (d < discs.size) {
            t++
            if (discs[d].positionAt(t) != 0) return false
            d++
        }
        return true
    }

    override fun partTwo(): Any {
        discs.add(Disc("#New",11,0))
        var t = 0
        while (true) {
            t++
            if (throwBall(t)) return t
        }
    }
}
