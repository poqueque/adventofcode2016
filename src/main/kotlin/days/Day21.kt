package days

import util.InputReader
import util.replaceAt

class Day21 : Day(21) {

    override fun partOne(): Any {
        return scramble("abcdefgh")
    }

    private fun scramble(s: String): String {
        var w = s
        inputList.forEach {
            var data = InputReader.getStringsFromExpression("swap position $ with position $", it)
            if (data != null) {
                val t = w[data[0].toInt()]
                w = w.replaceAt(data[0].toInt(), w[data[1].toInt()]).replaceAt(data[1].toInt(), t)
            }
            data = InputReader.getStringsFromExpression("swap letter $ with letter $", it)
            if (data != null) {
                w = w.replace(data[0], "_")
                w = w.replace(data[1], data[0])
                w = w.replace("_", data[1])
            }
            data = InputReader.getStringsFromExpression("reverse positions $ through $", it)
            if (data != null) {
                var newW = ""
                newW += w.substring(0, data[0].toInt())
                newW += w.substring(data[0].toInt(), data[1].toInt() + 1).reversed()
                newW += w.substring(data[1].toInt() + 1)
                w = newW
            }
            data = InputReader.getStringsFromExpression("rotate left $ step", it)
            if (data != null) {
                var steps = data[0].toInt()
                while (steps > 0) {
                    w = w.substring(1) + w[0]
                    steps--
                }
            }
            data = InputReader.getStringsFromExpression("rotate left $ steps", it)
            if (data != null) {
                var steps = data[0].toInt()
                while (steps > 0) {
                    w = w.substring(1) + w[0]
                    steps--
                }
            }
            data = InputReader.getStringsFromExpression("rotate right $ step", it)
            if (data != null) {
                var steps = data[0].toInt()
                while (steps > 0) {
                    w = w[w.length - 1] + w.substring(0, w.length - 1)
                    steps--
                }
            }
            data = InputReader.getStringsFromExpression("rotate right $ steps", it)
            if (data != null) {
                var steps = data[0].toInt()
                while (steps > 0) {
                    w = w[w.length - 1] + w.substring(0, w.length - 1)
                    steps--
                }
            }
            data = InputReader.getStringsFromExpression("move position $ to position $", it)
            if (data != null) {
                val c = w[data[0].toInt()]
                val newW = w.substring(0, data[0].toInt()) + w.substring(data[0].toInt() + 1)
                w = newW.substring(0, data[1].toInt()) + c + newW.substring(data[1].toInt())
            }
            data = InputReader.getStringsFromExpression("rotate based on position of letter $", it)
            if (data != null) {
                var steps = w.indexOf(data[0]) + 1 + if (w.indexOf(data[0]) > 3) 1 else 0
                while (steps > 0) {
                    w = w[w.length - 1] + w.substring(0, w.length - 1)
                    steps--
                }
            }
        }
        return w
    }

    override fun partTwo(): Any {
        var original = "fbgdceah"
        val target = "fbgdceah"
        while (true) {
            val wStr = scramble(original)
            if (wStr == target) return original
            original = wStr
        }
    }
}
