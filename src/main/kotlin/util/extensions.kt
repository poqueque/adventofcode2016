package util

fun String.splitrim(separator: String) : List<String> {
    return this.split(separator).map { it.trim() }
}

fun String.replaceAt(index: Int, char: Char) : String {
    val sb = StringBuilder(this).also { it.setCharAt(index, char) }
    return sb.toString()
}