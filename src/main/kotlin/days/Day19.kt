package days

class Day19 : Day(19) {

    override fun partOne(): Any {
        val elves = (1..1834903).filter { it % 2 == 1 }.toMutableList()
        var pointer = elves.size-1
        while (elves.size > 1) {
            pointer++
            if (pointer >= elves.size) pointer -= elves.size
            elves.removeAt(pointer)
            if (elves.size % 10000 == 0)
                println(elves.size)
        }
        return elves[0]
    }

    override fun partTwo(): Any {
        val elves = (1..3014603).toMutableList()
        var pointer = 0
        while (elves.size > 1) {
            val toRemove = (pointer + elves.size / 2) % elves.size
            elves.removeAt(toRemove)
            if (toRemove > pointer) pointer++
            if (pointer >= elves.size) pointer -= elves.size
            if (elves.size % 10000 == 0)
                println(elves.size)
        }
        return elves[0]
    }
}
