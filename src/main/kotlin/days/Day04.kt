package days

class Day04 : Day(4) {

    class Room(inputData: String) {
        var groups = listOf<String>()
        var sectorId = 0
        var checkSum = ""
        val letters = "abcdefghijklmnopqrstuvwxyz"

        init {
            val a = inputData.split("[")
            checkSum = a[1].replace("]", "")
            groups = a[0].split("-")
            sectorId = groups.last().toInt()
            groups = groups.dropLast(1)
        }

        fun calculateCheckSum(): String {
            val m = mutableMapOf<Char, Int>()
            for (g in groups) {
                for (w in g) {
                    m[w] = (m[w] ?: 0) + 1
                }
            }
            val sm = m.entries.sortedBy { it.key }
            val sm2 = sm.sortedByDescending { it.value }
            val sm3 = sm2.map { it.key }
            return sm3.take(5).joinToString("")
        }

        fun isValid() : Boolean {
            return checkSum == calculateCheckSum()
        }

        private fun rotate(letter: Char, i: Int): Char {
            val index = (letters.indexOf(letter) + sectorId) % letters.length
            return letters[index]
        }

        fun decryptedName(): String {
            var decripted = ""
            for (g in groups) {
                for (w in g)
                    decripted += rotate(w, sectorId)
                decripted += " "
            }
            return decripted.trim()
        }
    }

    override fun partOne(): Any {
        var sectorIdSum = 0
        for (data in inputList) {
            val r = Room(data)
            if (r.isValid()) {
                sectorIdSum += r.sectorId
            }
        }
        return sectorIdSum
    }

    override fun partTwo(): Any {
        for (data in inputList) {
            val r = Room(data)
            if (r.isValid()) {
                val d = r.decryptedName()
                if (d.contains("northpole")) {
                    println("[${r.sectorId}] $d")
                    return r.sectorId
                }
            }
        }
        return 0
    }
}
