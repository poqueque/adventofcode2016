package days

const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"
const val ANSI_WHITE = "\u001B[37m"
const val KEYS = "0123456789abcdefghijklmnopqrstuvwxyz"

class Day11 : Day(11) {

    private val bad = 100

    companion object {
        var totalProperties: List<String>? = null
        var totalElements: List<String>? = null
    }

    private var initialFloors = mutableListOf(
        inputList[0].split(" ").filter { it != "" }.toMutableList(),
        inputList[1].split(" ").filter { it != "" }.toMutableList(),
        inputList[2].split(" ").filter { it != "" }.toMutableList(),
        inputList[3].split(" ").filter { it != "" }.toMutableList(),
    )

    private var history = mutableListOf<State>()
    private var validStates = mutableListOf<State>()

    data class Move(val direction: String, val inElevator: List<String>)

    data class State(
        var elevator: Int,
        var moves: Int,
        var floor0: MutableList<String>,
        var floor1: MutableList<String>,
        var floor2: MutableList<String>,
        var floor3: MutableList<String>,
        var checked: Boolean = false,
    ) {
        val inFloor
            get() =
                when (elevator) {
                    0 -> floor0.sorted()
                    1 -> floor1.sorted()
                    2 -> floor2.sorted()
                    3 -> floor3.sorted()
                    else -> floor0.sorted()
                }

        val upperFloor
            get() =
                when (elevator) {
                    0 -> floor1.sorted()
                    1 -> floor2.sorted()
                    2 -> floor3.sorted()
                    3 -> listOf()
                    else -> listOf()
                }
        val lowerFloor
            get() =
                when (elevator) {
                    0 -> listOf()
                    1 -> floor0.sorted()
                    2 -> floor1.sorted()
                    3 -> floor2.sorted()
                    else -> listOf()
                }

        val floors
            get() = listOf(floor0, floor1, floor2, floor3)

        fun key(): String {
            var list = ""
            for (prop in totalProperties!!) {
                var positions = 0
                if (floor0.contains("${prop}M")) positions += 0
                if (floor1.contains("${prop}M")) positions += 1
                if (floor2.contains("${prop}M")) positions += 2
                if (floor3.contains("${prop}M")) positions += 3
                if (floor0.contains("${prop}G")) positions += 0
                if (floor1.contains("${prop}G")) positions += 4
                if (floor2.contains("${prop}G")) positions += 8
                if (floor3.contains("${prop}G")) positions += 12
                list += KEYS[positions]
            }
            return list.toCharArray().sorted().joinToString()
        }

        override fun equals(other: Any?): Boolean {
            if (other is State) {
                if (key() == other.key()
                    && elevator == other.elevator
                ) return true
            }
            return false
        }

        override fun hashCode(): Int {
            var result = elevator
            result = 31 * result + floor0.hashCode()
            result = 31 * result + floor1.hashCode()
            result = 31 * result + floor2.hashCode()
            result = 31 * result + floor3.hashCode()
            return result
        }

        fun isFinishState() = totalElements!!.size == floor3.size

        fun duplicate(
            elevator: Int = this.elevator,
            moves: Int = this.moves,
            floor0: MutableList<String> = this.floor0.toMutableList(),
            floor1: MutableList<String> = this.floor1.toMutableList(),
            floor2: MutableList<String> = this.floor2.toMutableList(),
            floor3: MutableList<String> = this.floor3.toMutableList(),
            checked: Boolean = this.checked
        ): State = State(elevator, moves, floor0, floor1, floor2, floor3, checked)
    }

    override fun partOne(): Any {
        var t0 = System.currentTimeMillis()
        var minMoves = 100000
        totalElements = initialFloors.flatten().sorted()
        totalProperties = totalElements!!.map { it.substring(0, 2) }.distinct().sorted()
        val initState = State(
            0,
            0,
            initialFloors[0].toMutableList(),
            initialFloors[1].toMutableList(),
            initialFloors[2].toMutableList(),
            initialFloors[3].toMutableList()
        )
        validStates.add(initState)
        var statesToCheck = validStates.filter { !it.checked }
        var last5 = mutableListOf<Long>()
        while (statesToCheck.isNotEmpty()) {
            val t1 = (System.currentTimeMillis() - t0) / 1000
            last5.add(t1)
            if (last5.size > 5) last5 = last5.drop(1).toMutableList()
            println(
                "validStates = ${validStates.size} statesToCheck = ${statesToCheck.size} [$t1] [${
                    last5.average().toInt()
                }]"
            )
            t0 = System.currentTimeMillis()
            for (state in statesToCheck) {
                val validMoves = getValidMoves(state)
                for (validMove in validMoves) {
                    val newState = makeMove(validMove, state)
                    if (newState.isFinishState() && newState.moves < minMoves) {
                        minMoves = newState.moves
                        println("moves: ${newState.moves}")
                        checkState(true, newState)
                    } else {
                        if (!checkState(false, newState) && !validStates.contains(newState)) {
                            validStates.add(newState)
                            //checkState(true, newState)
                            //println("moves: ${newState.moves}")
                            //if (newState.moves == 11) print("")
                        }
                    }
                }
                state.checked = true
            }
            statesToCheck = validStates.filter { !it.checked }
        }
        return minMoves
    }

    private fun getValidMoves(state: State): List<Move> {
        var canDown = true
        var canUp = true
        val validMoves = mutableListOf<Move>()
        if (state.elevator == 0) canDown = false
        if (state.elevator == 3) canUp = false
        if (state.elevator == 1 && state.floor0.isEmpty()) canDown = false
        if (state.elevator == 2 && state.floor0.isEmpty() && state.floor1.isEmpty()) canDown = false
        if (state.elevator == 3 && state.floor0.isEmpty() && state.floor1.isEmpty() && state.floor2.isEmpty()) canDown =
            false
        for (i in state.inFloor)
            for (j in state.inFloor) {
                val inElevator = listOf(i, j).distinct()
                if (canDown)
                    if (inElevator.size != 2 || inElevator[0].substring(0, 2) != inElevator[1].substring(0, 2)) {
                        var ok = true
                        val finalFloor = state.lowerFloor + inElevator
                        for (element in totalElements!!) {
                            if (element.endsWith("M") && finalFloor.contains(element)
                                && !finalFloor.contains(element.replace("M", "G"))
                                && finalFloor.any { it.endsWith("G") }
                            ) {
                                ok = false
                            }
                        }
                        if (ok) validMoves.add(Move("D", inElevator))
                    }
                if (canUp) {
                    var ok = true
                    val finalFloor = state.upperFloor + inElevator
                    for (element in totalElements!!) {
                        if (element.endsWith("M") && finalFloor.contains(element)
                            && !finalFloor.contains(element.replace("M", "G"))
                            && finalFloor.any { it.endsWith("G") }
                        ) {
                            ok = false
                        }
                    }
                    if (ok) validMoves.add(Move("U", inElevator))
                }
            }
        return validMoves
    }

    private fun makeMove(move: Move, initState: State): State {
        val newState = initState.duplicate(checked = false)
        if (move.direction == "U" && newState.elevator < 3) {
            if (move.inElevator.size in 1..2) {
                newState.elevator++
                for (element in move.inElevator) {
                    newState.floors[newState.elevator - 1].remove(element)
                    newState.floors[newState.elevator].add(element)
                }
                newState.moves++
            }
        }
        if (move.direction == "D" && newState.elevator > 0 && move.inElevator.size in 1..2) {
            newState.elevator--
            for (element in move.inElevator) {
                newState.floors[newState.elevator + 1].remove(element)
                newState.floors[newState.elevator].add(element)
            }
            newState.moves++
        }
        return newState
    }

    private fun checkState(print: Boolean = false, state: State): Boolean {
        var data = ""
        var error = false
        for (i in 3 downTo 0) {
            data += "F${i + 1} "
            data += if (state.elevator == i) "E" else "."
            for (element in totalElements!!) {
                if (element.endsWith("M") && state.floors[i].contains(element)
                    && !state.floors[i].contains(element.replace("M", "G"))
                    && state.floors[i].any { it.endsWith("G") }
                ) {
                    data += ANSI_RED
                    error = true
                }
                data += if (state.floors[i].contains(element)) " $element" else "  . "
                data += ANSI_RESET
            }
            data += "\n"
        }
        if (print) println(data)
        return error
    }

    override fun partTwo(): Any {
        initialFloors[0].add("ELG")
        initialFloors[0].add("ELM")
        initialFloors[0].add("DIG")
        initialFloors[0].add("DIM")
        return 0
    }
}
