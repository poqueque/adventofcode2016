package days

class Day06 : Day(6) {

    override fun partOne(): Any {
        val data = mutableListOf(mutableMapOf<Char, Int>())
        val wordLength = inputList[0].length
        repeat(wordLength) { data.add(mutableMapOf()) }
        inputList.forEach {
            for (i in it.indices) {
                data[i][it[i]] = (data[i][it[i]] ?: 0) + 1
            }
        }
        var result = ""
        for (i in 0 until wordLength) {
            result += data[i].toList().sortedBy { (_, value) -> value}.toMap().keys.toList().reversed().first()
        }

        return result
    }

    override fun partTwo(): Any {
        val data = mutableListOf(mutableMapOf<Char, Int>())
        val wordLength = inputList[0].length
        repeat(wordLength) { data.add(mutableMapOf()) }
        inputList.forEach {
            for (i in it.indices) {
                data[i][it[i]] = (data[i][it[i]] ?: 0) + 1
            }
        }
        var result = ""
        for (i in 0 until wordLength) {
            result += data[i].toList().sortedBy { (_, value) -> value}.toMap().keys.toList().first()
        }

        return result
    }
}
