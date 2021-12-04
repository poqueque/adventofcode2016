package days

import util.MathUtils.md5Hash

class Day14 : Day(1) {

    override fun partOne(): Any {

        data class Hash(val md5: String, val indexFound: Int, val repeatedChar: Char, var validatedAt: Int)

        val prefix = "jlmsuwbz"
        var i = 0
        val triples = mutableListOf<Hash>()
        val keys = mutableListOf<Hash>()
        var k = 0
        while (i < 300000) {
            triples.filter { it.indexFound + 1000 < i }.toList().forEach {
                triples.remove(it)
            }

            val salt = "$prefix$i"
            val md5 = md5Hash(salt)

            if (i == 18)
                println(i)

            triples.toList().forEach {
                if (md5.contains("${it.repeatedChar}${it.repeatedChar}${it.repeatedChar}${it.repeatedChar}${it.repeatedChar}")) {
                    it.validatedAt = i
                    keys.add(it)
                    println("${keys.size} -> ${it.indexFound}: ${it.validatedAt} ${it.md5} -> $md5")
                    triples.remove(it)
                }
            }

            if (keys.size >= 64)
                k++
            if (k == 2000) {
                return keys.sortedBy { hash -> hash.indexFound }[63].indexFound
            }

            val r3 = repeatedChar3(md5)
            if (r3 != null) triples.add(Hash(md5, i, r3, 0))

            i++
        }

        return 0
    }

    private fun repeatedChar3(md5: String): Char? {
        for (i in md5.indices)
            if (i > 1 && md5[i] == md5[i - 1] && md5[i] == md5[i - 2])
                return md5[i]
        return null
    }

    override fun partTwo(): Any {

        data class Hash(val md5: String, val indexFound: Int, val repeatedChar: Char, var validatedAt: Int)

        val prefix = "jlmsuwbz"
        var i = 0
        val triples = mutableListOf<Hash>()
        val keys = mutableListOf<Hash>()
        var k = 0
        while (i < 300000) {
            triples.filter { it.indexFound + 1000 < i }.toList().forEach {
                triples.remove(it)
            }

            val salt = "$prefix$i"
            var stretchedMd5 = md5Hash(salt)
            repeat(2016) {
                stretchedMd5 = md5Hash(stretchedMd5)
            }

            if (i == 18)
                println(i)

            triples.toList().forEach {
                if (stretchedMd5.contains("${it.repeatedChar}${it.repeatedChar}${it.repeatedChar}${it.repeatedChar}${it.repeatedChar}")) {
                    it.validatedAt = i
                    keys.add(it)
                    println("${keys.size} -> ${it.indexFound}: ${it.validatedAt} ${it.md5} -> $stretchedMd5")
                    triples.remove(it)
                }
            }

            if (keys.size >= 64)
                k++
            if (k == 2000) {
                return keys.sortedBy { hash -> hash.indexFound }[63].indexFound
            }

            val r3 = repeatedChar3(stretchedMd5)
            if (r3 != null) triples.add(Hash(stretchedMd5, i, r3, 0))

            i++
        }

        return 0
    }
}
