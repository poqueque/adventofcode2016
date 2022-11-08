package days

class Day25 : Day(25) {

    var data = mutableMapOf(
        "a" to 0,
        "b" to 0,
        "c" to 0,
        "d" to 0,
    )

    override fun partOne(): Any {
        var k = 0
        var r = 0
        while (r == 0){
            k++
            r = run(k)
        }
        return r
    }

    fun run(initData: Int): Int {
        var iterations = 0
        var max = 0
        var pointer = 0
        data = mutableMapOf(
            "a" to initData,
            "b" to 0,
            "c" to 0,
            "d" to 0,
        )
        val mutableInputList = inputList.toMutableList()
        val list = mutableListOf<Int>()
        while (pointer < mutableInputList.size) {
            val line = mutableInputList[pointer]
            if (pointer > max) {
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
                "out" -> {
                    if (list.lastOrNull() == data[words[1]]) {
                        return 0
                    }
                    list.add(data[words[1]] ?: -1)
                    if (list.size > 100)
                        return initData
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
        }
        return 0
    }

    override fun partTwo(): Any {
        return 0
    }
}
