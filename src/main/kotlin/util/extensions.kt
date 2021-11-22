package util

fun String.splitrim(separator: String) : List<String> {
    return this.split(separator).map { it.trim() }
}