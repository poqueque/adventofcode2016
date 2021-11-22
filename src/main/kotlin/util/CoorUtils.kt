enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Coor3(val x: Int, val y: Int, val z: Int) {
    operator fun plus(d: Coor3): Coor3 {
        return Coor3(x + d.x, y + d.y, z + d.z)
    }

    operator fun plus(i: Int): Coor3 {
        return Coor3(x + i, y + i, z + i)
    }

    operator fun minus(d: Coor3): Coor3 {
        return Coor3(x - d.x, y - d.y, z - d.z)
    }
}

