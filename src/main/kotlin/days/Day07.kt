package days

class Day07 : Day(7) {

    override fun partOne(): Any {
        var tls = 0
        inputList.forEach {
            if (isTls(it)) tls++
        }
        return tls
    }

    private fun isTls(ip: String): Boolean {
        var p1 = '_'
        var p2 = '_'
        var p3 = '_'
        var inHyperNet = false
        var isTls = false
        ip.forEach {
            if (it == '[') {
                inHyperNet = true
            } else if (it == ']') {
                inHyperNet = false
            } else {
                if (it == p1 && p2 == p3 && p1 != p2) {
                    if (inHyperNet) {
                        return false
                    } else {
                        isTls = true
                    }
                }
            }
            p1 = p2
            p2 = p3
            p3 = it
        }
        return isTls
    }

    override fun partTwo(): Any {
        var ssl = 0
        inputList.forEach {
            if (isSsl(it)) ssl++
        }
        return ssl
    }

    private fun isSsl(ip: String): Boolean {
        val abas = mutableListOf<String>()
        val babs = mutableListOf<String>()
        var inHyperNet = false
        var p1 = '_'
        var p2 = '_'

        ip.forEach {
            if (it == '[') {
                inHyperNet = true
            } else if (it == ']') {
                inHyperNet = false
            } else {
                if (it == p1 && p1 != p2) {
                    if (inHyperNet) {
                        babs.add("$p2$p1$p2")
                    } else {
                        abas.add("$p1$p2$p1")
                    }
                }
            }
            p1 = p2
            p2 = it
        }
        abas.forEach{
            if (babs.contains(it)) return true
        }
        return false
    }
}
