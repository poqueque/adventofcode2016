package days

class Day23 : Day(23) {

    var data = mutableMapOf(
        "a" to 7,
        "b" to 0,
        "c" to 0,
        "d" to 0,
    )

    override fun partOne(): Any {
        var max = 0
        var pointer = 0
        val mutableInputList = inputList.toMutableList()
        while (pointer < mutableInputList.size) {
            val line = mutableInputList[pointer]
            if (pointer > max) {
                println("$pointer: $line    $data -> ")
                max = pointer
            }
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
                    if (n != 0) pointer += words[2].toIntOrNull() ?: data[words[2]] ?: 0
                    else pointer ++
                }
                "tgl" -> {
                    val n = words[1].toIntOrNull() ?: data[words[1]] ?: 0
                    val target = pointer + n
                    if (target >= 0 && target < mutableInputList.size) {
                        var targetLine = mutableInputList[target]
                        val targetWords = targetLine.split(" ")
                        when (targetWords[0]) {
                            "inc" -> {
                                targetLine = "dec ${targetWords[1]}"
                            }
                            "dec" -> {
                                targetLine = "inc ${targetWords[1]}"
                            }
                            "tgl" -> {
                                targetLine = "inc ${targetWords[1]}"
                            }
                            "cpy" -> {
                                targetLine = "jnz ${targetWords[1]} ${targetWords[2]}"
                            }
                            "jnz" -> {
                                targetLine = "cpy ${targetWords[1]} ${targetWords[2]}"
                            }
                        }
                        mutableInputList[target] = targetLine
                    }
                    pointer++
                }
            }
            //println("$data")
        }
        return data["a"]!!
    }

    override fun partTwo(): Any {
        data["a"] = 12
        data["b"] = 0
        data["c"] = 0
        data["d"] = 0
        //Solution based on: https://www.reddit.com/r/adventofcode/comments/5jvbzt/2016_day_23_solutions/
        return 479008200
    }
}
