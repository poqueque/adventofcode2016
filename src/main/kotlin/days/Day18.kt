package days

class Day18 : Day(18) {

    override fun partOne(): Any {
        var prevRow = "^..^^.^^^..^^.^...^^^^^....^.^..^^^.^.^.^^...^.^.^.^.^^.....^.^^.^.^.^.^.^.^^..^^^^^...^.....^....^."
        var safeTiles = prevRow.count { it == '.' }
        for (i in 1 until 40) {
            var thisRow = ""
            for (j in prevRow.indices) {
                var check = ""
                if (j > 0) check += prevRow[j - 1] else check += "."
                check += prevRow[j]
                if (j < prevRow.length-1) check += prevRow[j + 1] else check += "."
                thisRow += when (check) {
                    "^^." -> "^"
                    ".^^" -> "^"
                    "^.." -> "^"
                    "..^" -> "^"
                    else -> "."
                }
            }
            safeTiles += thisRow.count { it == '.' }
            prevRow = thisRow
        }
        return safeTiles
    }

    override fun partTwo(): Any {
        var prevRow = "^..^^.^^^..^^.^...^^^^^....^.^..^^^.^.^.^^...^.^.^.^.^^.....^.^^.^.^.^.^.^.^^..^^^^^...^.....^....^."
        var safeTiles = prevRow.count { it == '.' }
        for (i in 1 until 400000) {
            var thisRow = ""
            for (j in prevRow.indices) {
                var check = ""
                if (j > 0) check += prevRow[j - 1] else check += "."
                check += prevRow[j]
                if (j < prevRow.length-1) check += prevRow[j + 1] else check += "."
                thisRow += when (check) {
                    "^^." -> "^"
                    ".^^" -> "^"
                    "^.." -> "^"
                    "..^" -> "^"
                    else -> "."
                }
            }
            safeTiles += thisRow.count { it == '.' }
            prevRow = thisRow
        }
        return safeTiles
    }
}
