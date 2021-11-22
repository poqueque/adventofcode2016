package util

data class Instruction (val value1: Value?, val value2: Value?, val op: Operator?) {
    override fun toString(): String {
        return "$value1 $op $value2"
    }
}

class Value {
    var isNumber: Boolean = false
    var string: String? = null
    var integer: Int? = null

    constructor (s: String) {
        val number = s.toIntOrNull()
        isNumber = number != null
        if (number != null) integer = number else string = s
    }

    override fun toString(): String {
        if (isNumber) return "#$integer"
        return "$string"
    }
}

data class Operator (val op: String?) {
    override fun toString(): String {
        return "$op"
    }
}