package util

import kotlin.math.atan2
import kotlin.math.sqrt

data class Coor(var x: Int, var y: Int) {
    operator fun plus(d: Coor): Coor {
        return Coor(x + d.x, y + d.y)
    }

    operator fun minus(d: Coor): Coor {
        return Coor(x - d.x, y - d.y)
    }

    operator fun times(d: Coor): Int {
        return x * d.x + y * d.y
    }

    operator fun times(i: Int): Coor {
        return Coor(x * i, y * i)
    }

    fun mod(): Double {
        return sqrt(x*x+y*y.toDouble())
    }

    fun hides(d2: Coor): Boolean {
        val angle1 = atan2(y.toDouble(), x.toDouble())
        val angle2 = atan2(d2.y.toDouble(), d2.x.toDouble())
        val mod1 = sqrt(x.toDouble() * x.toDouble() + y.toDouble() * y.toDouble())
        val mod2 = sqrt(d2.x.toDouble() * d2.x.toDouble() + d2.y.toDouble() * d2.y.toDouble())
        return (angle1 == angle2 && mod1 < mod2)
    }
}



