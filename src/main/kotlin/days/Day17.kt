package days

import util.Coor
import util.MathUtils.md5Hash

class Day17 : Day(17) {

    override fun partOne(): Any {
        val passCode = "vwbaicqe"
        val paths = mutableListOf("")

        while (true) {
            for (p in paths.toList()) {
                paths.remove(p)
                val hash = md5Hash("$passCode$p")
                if (hash[0] > 'a') paths.add("${p}U")
                if (hash[1] > 'a') paths.add("${p}D")
                if (hash[2] > 'a') paths.add("${p}L")
                if (hash[3] > 'a') paths.add("${p}R")
            }
            for (p in paths.toList()) {
                val init = Coor(0, 0)
                for (i in p) {
                    when (i) {
                        'U' -> init.y--
                        'D' -> init.y++
                        'L' -> init.x--
                        'R' -> init.x++
                    }
                    if (init.x < 0 || init.y < 0) paths.remove(p)
                }
                if (init == Coor(3, 3)) return p
            }
            if (paths.size == 0) return "--"
        }
    }

    override fun partTwo(): Any {

        val passCode = "vwbaicqe"
        val paths = mutableListOf("")
        var longest = ""

        while (true) {
            for (p in paths.toList()) {
                paths.remove(p)
                val hash = md5Hash("$passCode$p")
                if (hash[0] > 'a') paths.add("${p}U")
                if (hash[1] > 'a') paths.add("${p}D")
                if (hash[2] > 'a') paths.add("${p}L")
                if (hash[3] > 'a') paths.add("${p}R")
            }
            for (p in paths.toList()) {
                val init = Coor(0, 0)
                for (i in p) {
                    when (i) {
                        'U' -> init.y--
                        'D' -> init.y++
                        'L' -> init.x--
                        'R' -> init.x++
                    }
                    if (init.x < 0 || init.y < 0) paths.remove(p)
                    if (init.x > 3 || init.y > 3) paths.remove(p)
                }
                if (init == Coor(3, 3)) {
                    paths.remove(p)
                    if (p.length > longest.length) {
                        longest = p
                    }
                }
            }
            if (paths.size == 0) return longest.length
        }
    }
}
