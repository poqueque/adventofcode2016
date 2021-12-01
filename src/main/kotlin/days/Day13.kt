package days

import util.Coor

class Day13 : Day(0) {

    val favNum = 1358
    val map = mutableMapOf<Coor,Char>()
    val objective = Coor(31,39)

    fun what(c: Coor): Char {
        val d = c.x*c.x + 3*c.x + 2*c.x*c.y + c.y + c.y*c.y + favNum
        val bin = d.toString(2).count { c -> c=='1' } % 2
        return if (bin == 0) '.' else '#'
    }

    override fun partOne(): Any {
        map[Coor(1,1)] = what(Coor(1,1))
        val positions = mutableListOf(Coor(1,1))
        var steps = 0
        while (map[objective] == null) {
            for (p in positions.toList()) {
                if (map[p] == '.') map[p] = 'O'
                positions.remove(p)
                for (a in p.around) {
                    if (a.x >= 0 && a.y>=0) {
                        map[a] = map[a] ?: what(a)
                        if (map[a] == '.') positions.add(a)
                    }
                }
            }
            println ("Steps $steps")
            printMap()
            steps++
        }
        return steps
    }

    private fun printMap(){
        val xmax = map.keys.maxOf { it.x }
        val ymax = map.keys.maxOf { it.y }
        print("   ")
        for (i in 0..xmax) print (i/10)
        println()
        print("   ")
        for (i in 0..xmax) print (i%10)
        println()
        for (y in 0..ymax) {
            print(y.toString().padStart(2, '0'))
            print(" ")
            for (x in 0..xmax) print(map[Coor(x,y)] ?: ' ')
            println()
        }

    }

    override fun partTwo(): Any {
        map.clear()
        map[Coor(1,1)] = what(Coor(1,1))
        val positions = mutableListOf(Coor(1,1))
        var steps = 0
        var reached = 0
        while (steps <= 50) {
            for (p in positions.toList()) {
                if (map[p] == '.') {
                    map[p] = 'O'
                    reached++
                }
                positions.remove(p)
                for (a in p.around) {
                    if (a.x >= 0 && a.y>=0) {
                        map[a] = map[a] ?: what(a)
                        if (map[a] == '.') {
                            positions.add(a)
                        }
                    }
                }
            }
            println ("Steps $steps")
            printMap()
            steps++
        }
        return reached
    }
}
