package days

import util.Coor

class Day22 : Day(22) {

    val sizes = mutableMapOf<Coor,Int>()
    val used = mutableMapOf<Coor,Int>()
    val avail = mutableMapOf<Coor,Int>()

    init {
        reset()
        for (y in 0..28) {
            for (x in 0..34) {
                if (x == 34 && y == 0) print(" G ")
                else if (x == 0 && y == 0) print("(.)")
                else if (used[Coor(x,y)]!! > 200) print(" # ")
                else if (used[Coor(x,y)]!! == 0) print(" _ ")
                else print(" . ")
            }
            println()
        }
    }

    fun reset() {
        inputList.forEach {
            if (it.contains("/dev")) {
                val w = it.split(" ").filter { data -> data != "" }
                val c = w[0].split("-")
                val x = c[1].substring(1).toInt()
                val y = c[2].substring(1).toInt()
                sizes[Coor(x,y)] = w[1].replace("T","").toInt()
                used[Coor(x,y)] = w[2].replace("T","").toInt()
                avail[Coor(x,y)] = w[3].replace("T","").toInt()
            }
        }
    }

    override fun partOne(): Any {
        var viablePairs = 0
        for (c1 in sizes.keys)
            for (c2 in sizes.keys){
                if (used[c1]!! > 0 && c1 != c2 && avail[c2]!! >= used[c1]!!) {
                    viablePairs++
                }
            }
        println("Part 1: $viablePairs")
        return viablePairs
    }
    override fun partTwo(): Any {
        println("Basándonos en el dibujo, primero movemos _ a la posicion al lado de la G (26)." +
                "\nMovemos la G al lado (1). Y ahora hacemos el movimiento de 5 pasos para mover _ al otro lado de la G (5)" +
                "\nRepetimos 33 veces. Total 26 + 1 + 33*5")
        return 27 + 33*5
    }

    fun partTwoOld(): Any {

        var viableConsecutivePairs = 0
        for (c1 in sizes.keys)
            for (c2 in sizes.keys)
                if (used[c1]!! > 0 && c1 != c2 && avail[c2]!! >= used[c1]!! && c1 in c2.around) {
                    viableConsecutivePairs++
                    println ("$c1 -> $c2")
                }
        //Start on 27,12
        val initPos = Coor(27,12)
        val dest1 = Coor(0,28)
        print ("Minim: ")
        println (initPos.x + (dest1.y-1 - initPos.y) + 5*(dest1.y-1) + 1)
        var minSteps = 156000 //156
        repeat(1000000) {
            if (it %10000 ==0) println(it)
            reset()
            var steps = 0
            val alreadyThere = mutableListOf<Coor>()
            var pos = initPos
            while (pos != dest1 && steps < minSteps) {
                val possibleMoves = pos.around.filter {
                    used[it] != null &&
                    avail[pos]!! >= used[it]!! &&
                    !alreadyThere.contains(it)
                }
                if (possibleMoves.isNotEmpty()) {
                    val move = possibleMoves.random()
                    used[pos] = used[pos]!! + used[move]!!
                    avail[pos] = avail[pos]!! - used[move]!!
                    used[move] = 0
                    avail[move] = sizes[move]!!
                    pos = move
                    alreadyThere.add(pos)
                    steps++
                } else steps = 1000000
            }
            if (pos == dest1 && steps < minSteps) {
                minSteps = steps
                println("Steps: $steps ($steps + 5*27 = ${steps+5*27})")
            }
        }
        return minSteps + 5*27
    }
}
