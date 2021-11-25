package days

class Day09 : Day(9) {

    override fun partOne(): Any {
        val dec = decompress(inputString)
        return dec.length
    }


    fun decompress(string: String) : String {
        var dec = ""
        var i = 0
        while (i < string.length) {
            if (string[i] == '(') {
                val j = string.indexOf(')',i)
                val order = string.substring(i+1,j)
                val data = order.split("x")
                val r1 = data[0].toInt()
                val r2 = data[1].toInt()
                repeat (r2) {
                    for (k in 0 until r1) {
                        dec += string[j+k+1]
                    }
                }
                i = j + r1
            } else {
                dec += string[i]
            }
            i++
        }
        return dec
    }

    private fun decompressCount(string: String) : Long {
        var dec = 0L
        val weights = MutableList(string.length) {1}
        var i = 0
        while (i < string.length) {
            if (string[i] == '(') {
                val j = string.indexOf(')',i)
                val order = string.substring(i+1,j)
                val data = order.split("x")
                val r1 = data[0].toInt()
                val r2 = data[1].toInt()
                for (k in 0 until r1)
                    weights[j+k+1] *= r2
                i = j
            } else {
                dec += weights[i]
            }
            i++
        }
        return dec
    }

    override fun partTwo(): Any {
        val dec = decompressCount(inputString)
        println(dec)
        return dec
    }
}
