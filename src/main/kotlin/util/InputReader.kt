package util

import java.io.File

object InputReader {

    fun getInputAsString(day: Int): String {
        return fromResources(day).readText()
    }

    fun getInputAsList(day: Int): List<String> {
        return fromResources(day).readLines()
    }

    fun getStringsFromExpression(expression: String, check: String): List<String>? {
        val expData = expression.split(" ")
        val checkData = check.split(" ")
        if (expData.size != checkData.size) return null
        val strs = mutableListOf<String>()
        for (i in expData.indices) {
            if (expData[i] == "$") strs.add(checkData[i])
            else if (expData[i] != checkData[i]) return null
        }
        return strs;
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun fromResources(day: Int): File {
        return File(javaClass.classLoader.getResource("input_day_${day.toString().padStart(2,'0')}.txt").toURI())
    }
}
