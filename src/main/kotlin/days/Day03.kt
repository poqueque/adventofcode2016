package days

class Day03 : Day(3) {

    override fun partOne(): Any {
        var valid = 0
        for (data in inputList) {
            val sidesStr = data.split(" ").toMutableList()
            sidesStr.removeIf { e -> e == "" }
            val sides = sidesStr.map { e -> e.toInt() }
            if (sides[0] + sides[1] > sides[2] && sides[0] + sides[2] > sides[1] && sides[1] + sides[2] > sides[0])
                valid++
        }
        return valid
    }

    override fun partTwo(): Any {
        var valid = 0
        val data = inputList.map{
            val sidesStr = it.split(" ").toMutableList()
            sidesStr.removeIf { e -> e == "" }
            sidesStr.map { e -> e.toInt() }
        }
        for (i in 0 until data.size/3) {
            if (data[3*i][0] + data[3*i+1][0] > data[3*i+2][0] && data[3*i][0] + data[3*i+2][0] > data[3*i+1][0] && data[3*i+1][0] + data[3*i+2][0] > data[3*i][0])
                valid ++
            if (data[3*i][1] + data[3*i+1][1] > data[3*i+2][1] && data[3*i][1] + data[3*i+2][1] > data[3*i+1][1] && data[3*i+1][1] + data[3*i+2][1] > data[3*i][1])
                valid ++
            if (data[3*i][2] + data[3*i+1][2] > data[3*i+2][2] && data[3*i][2] + data[3*i+2][2] > data[3*i+1][2] && data[3*i+1][2] + data[3*i+2][2] > data[3*i][2])
                valid ++
        }
        return valid
    }
}
