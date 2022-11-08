package days

import util.Coor
import util.InputReader

abstract class Day(dayNumber: Int) {

    // lazy delegate ensures the property gets computed only on first access
    protected val inputMap: Map<Coor,String> by lazy { InputReader.getInputAsCoorMap(dayNumber) }
    protected val inputList: List<String> by lazy { InputReader.getInputAsList(dayNumber) }
    protected val inputString: String by lazy { InputReader.getInputAsString(dayNumber) }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}
