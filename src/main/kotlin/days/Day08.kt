package days

import util.Coor

class Day08 : Day(8) {

    var screen = mutableMapOf<Coor, Boolean>()

    override fun partOne(): Any {
        for (x in 0 until 50)
            for (y in 0 until 6)
                screen[Coor(x,y)] = false
        inputList.forEach{
            val words = it.split(" ")
            println(it)
            when (words[0]) {
                "rect" -> {
                    val d = words[1].split("x")
                    val fillX = d[0].toInt()
                    val fillY = d[1].toInt()
                    for (x1 in 0 until fillX)
                        for (y1 in 0 until fillY)
                            screen[Coor(x1,y1)] = true
                }
                "rotate" -> {
                    when (words[1]) {
                        "row" -> {
                            val row = words[2].substring(2).toInt()
                            val howMany = words[4].toInt()
                            val newRow = mutableListOf<Boolean>()
                            for (x1 in 0 until 50)
                                newRow.add(screen[Coor((50 + x1 - howMany)%50,row)]!!)
                            for (x1 in 0 until 50)
                                screen[Coor(x1,row)]=newRow[x1]
                        }
                        "column" -> {
                            val column = words[2].substring(2).toInt()
                            val howMany = words[4].toInt()
                            val newColumn = mutableListOf<Boolean>()
                            for (y1 in 0 until 6)
                                newColumn.add(screen[Coor(column, (6 + y1 - howMany)%6)]!!)
                            for (y1 in 0 until 6)
                                screen[Coor(column, y1)]=newColumn[y1]
                        }
                    }
                }
            }
            for (y in 0 until 6) {
                for (x in 0 until 50)
                    if (screen[Coor(x, y)]!!)
                        print("#")
                    else
                        print("·")
                println()
            }
        }
        var total = 0
        for (x in 0 until 50)
            for (y in 0 until 6)
                if (screen[Coor(x,y)]!!) total++
        return total
    }

    override fun partTwo(): Any {
        return "AFBUPZBJPS"
    }
}
