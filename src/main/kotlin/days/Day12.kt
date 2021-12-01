package days

class Day12 : Day(12) {

    var data = mutableMapOf(
        "a" to 0,
        "b" to 0,
        "c" to 0,
        "d" to 0,
    )

    override fun partOne(): Any {
        var pointer = 0
        while (pointer < inputList.size) {
            val line = inputList[pointer]
            val words = line.split(" ")
            when (words[0]) {
                "cpy" -> {
                    val n = words[1].toIntOrNull() ?: data[words[1]] ?: 0
                    data[words[2]] = n
                    pointer++
                }
                "inc" -> {
                    data[words[1]] = (data[words[1]] ?: 0) + 1
                    pointer++
                }
                "dec" -> {
                    data[words[1]] = (data[words[1]] ?: 0) - 1
                    pointer++
                }
                "jnz" -> {
                    val n = words[1].toIntOrNull() ?: data[words[1]] ?: 0
                    if (n != 0) pointer += words[2].toIntOrNull() ?: 0
                    else pointer ++
                }
            }
        }
        return data["a"]!!
    }

    override fun partTwo(): Any {
        data["a"] = 0
        data["b"] = 0
        data["c"] = 1
        data["d"] = 0
        return partOne()
    }
}
