package days

import java.math.BigInteger
import java.security.MessageDigest

class Day05 : Day(5) {
    val md = MessageDigest.getInstance("MD5")

    override fun partOne(): Any {
        var i = 0
        var password = ""
        while (password.length < 8) {
            val code = "$inputString$i"
            val hashed = BigInteger(1, md.digest(code.toByteArray())).toString(16).padStart(32, '0')
            if (hashed.startsWith("00000"))
                password += hashed[5]
            i++
        }
        return password
    }

    override fun partTwo(): Any {
        var i = 0
        var password = "________"
        while (password.contains("_")) {
            val code = "$inputString$i"
            val hashed = BigInteger(1, md.digest(code.toByteArray())).toString(16).padStart(32, '0')
            if (hashed.startsWith("00000")) {
                val position = hashed[5]
                if (position < '8'){
                    val chars = password.toCharArray()
                    if (chars[position.toString().toInt()] == '_') {
                        chars[position.toString().toInt()] = hashed[6]
                        password = String(chars)
                    }
                    println (password)
                }
            }
            i++
        }
        return password
    }
}
