package days

import util.Coor
import kotlin.math.min

class Day24 : Day(24) {

    private val maxNum = 7
    private val pos = mutableListOf<Coor>()
    private val distances = mutableMapOf<Pair<Int,Int>,Int>()

    override fun partOne(): Any {
        for (i in 0..maxNum) {
            pos.add(inputMap.entries.first { it.value == i.toString() }.key)
            println("Pos $i: ${pos[i]}")
        }

        for (p0 in 0..maxNum){
            setDistances(p0)
        }
        val order= MutableList(maxNum) { it + 1 }
        var minTotal = 1000000
        for (i in 0..100000){
            order.shuffle()
            var total: Int = distances[Pair(0,order[0])]!!
            for (k in 1 until maxNum)
                total += distances[Pair(order[k-1],order[k])]!!
            if (total < minTotal){
                minTotal = total
                print(minTotal)
                print(" 0")
                for (i in 0 until maxNum)
                print ("-> ${order[i]}")
                println()
            }
        }
        return minTotal
    }

    private fun setDistances(p0: Int) {
        val tracedMap = inputMap.toMutableMap()
        var distance = 0
        var pointers = mutableListOf<Coor>()
        pointers.add(pos[p0])
        while (pointers.isNotEmpty()) {
            distance++
            val newPointers = mutableListOf<Coor>()
            for (p in pointers){
                tracedMap[p] = "X"
                val neighbours = listOf(p.left(),p.up(),p.right(),p.down())
                for (n in neighbours) {
                    if ((tracedMap[n] ?: "") == "." && !newPointers.contains(n)) {
                        newPointers.add(n)
                    }
                    if ((tracedMap[n] ?: "").toIntOrNull() != null) {
                        newPointers.add(n)
                        distances[Pair(p0,(tracedMap[n] ?: "").toInt())] = distance
                        println ("Distance $p0 -> ${(tracedMap[n] ?: "").toInt()} = $distance")
                    }
                }
            }
            //printMap(tracedMap)
            pointers = newPointers
        }
    }

    private fun printMap(tracedMap: Map<Coor,String>) {
        for (y in 0..40) {
            for (x in 0..178)
                print(tracedMap[Coor(x, y)])
            println()
        }
    }

    override fun partTwo(): Any {
        val order= MutableList(maxNum) { it + 1 }
        var minTotal = 1000000
        for (i in 0..100000){
            order.shuffle()
            var total: Int = distances[Pair(0,order[0])]!!
            for (k in 1 until maxNum)
                total += distances[Pair(order[k-1],order[k])]!!
            total += distances[Pair(order[maxNum-1],0)]!!
            if (total < minTotal){
                minTotal = total
                print(minTotal)
                print(" 0")
                for (i in 0 until maxNum)
                    print (" -> ${order[i]}")
                print (" -> 0")
                println()
            }
        }
        return minTotal
    }
}
