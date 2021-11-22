package util

import kotlin.math.min

class CoorMap(inputList: List<String>) {

    var id = 0
    var orientation = 0
    val map = mutableMapOf<Coor,Char>()
    val orientedMaps = mutableListOf<MutableMap<Coor,Char>>()
    var maxX = 0
    var maxY = 0
    val borders = mutableListOf<Int>()
    var N = ""
    var S = ""
    var E = ""
    var W = ""
    var FN = ""
    var FS = ""
    var FE = ""
    var FW = ""
    val bordersStr
        get() = listOf(N,S,E,W,FN,FS,FE,FW)

    val orientedMap
        get() = orientedMaps[orientation]

    init {
        map.clear()
        maxX = inputList[0].length
        maxY = inputList.size
        for (y in inputList.indices) {
            val line = inputList[y].toList()
            for (x in line.indices) {
                map[Coor(x, y)] = line[x]
            }
        }
        var str1 = ""
        var str2 = ""
        for (i in 0 until maxX){
            str1+=map[Coor(i,0)]
            str2+=map[Coor(i,maxY-1)]
        }
        N = str1
        S = str1
        FN = str1.reversed()
        FS = str1.reversed()
        str1 = str1.replace("#","1").replace(".","0")
        str2 = str2.replace("#","1").replace(".","0")
        if (str1.length < 20) {
            borders.add(min(str1.toInt(2), str1.reversed().toInt(2)))
            borders.add(min(str2.toInt(2), str2.reversed().toInt(2)))
        }
        str1 = ""
        str2 = ""
        for (i in 0 until maxY){
            str1+=map[Coor(0,i)]
            str2+=map[Coor(maxX-1,i)]
        }
        W = str1
        E = str1
        FW = str1.reversed()
        FE = str1.reversed()
        str1 = str1.replace("#","1").replace(".","0")
        str2 = str2.replace("#","1").replace(".","0")
        if (str1.length < 20){
            borders.add(min(str1.toInt(2),str1.reversed().toInt(2)))
            borders.add(min(str2.toInt(2),str2.reversed().toInt(2)))
        }

        var r = map
        orientedMaps.add(r)
        r = rotate(r)
        orientedMaps.add(r)
        r = rotate(r)
        orientedMaps.add(r)
        r = rotate(r)
        orientedMaps.add(r)
        r = flip(map)
        orientedMaps.add(r)
        r = rotate(r)
        orientedMaps.add(r)
        r = rotate(r)
        orientedMaps.add(r)
        r = rotate(r)
        orientedMaps.add(r)
    }

    fun rotateThis(){
        orientation++
        if (orientation == 8) orientation = 0
    }

    private fun rotate(map: MutableMap<Coor, Char>): MutableMap<Coor, Char> {
        val maxY = (map.keys.map{it.y}.maxOrNull() ?: 0)
        val retMap = mutableMapOf<Coor,Char>()
        map.entries.forEach {
            retMap[Coor(maxY-it.key.y,it.key.x)] = it.value
        }
        return retMap
    }
    private fun flip(map: MutableMap<Coor, Char>): MutableMap<Coor, Char> {
        val maxY = (map.keys.map{it.y}.maxOrNull() ?: 0)
        val retMap = mutableMapOf<Coor,Char>()
        map.entries.forEach {
            retMap[Coor(it.key.x,maxY-it.key.y)] = it.value
        }
        return retMap
    }

    override fun toString(): String {
        var str = "Tile $id-$orientation:\n"
        for (y in 0 until maxY) {
            for (x in 0 until maxX) {
                str += orientedMap[Coor(x,y)]
            }
            str+= "\n"
        }
        str+= "\n"
        return str
    }

    fun getNeigbours(k: Coor, c: Char): Int {
        var found =0
        for(i in k.x-1 .. k.x+1)
            for (j in k.y-1 .. k.y+1){
                if (i>=0 && j>=0 && i<=maxX && j<=maxY && (i != k.x || j != k.y) && map[Coor(i,j)] == c) found++
            }
        return found
    }

    companion object {
        fun getN(map: MutableMap<Coor, Char>): String {
            val maxX = (map.keys.map{it.x}.maxOrNull() ?: 0)
            val maxY = (map.keys.map{it.y}.maxOrNull() ?: 0)
            var retVal = ""
            for (i in 0..maxX)
                retVal += map[Coor(i,0)]
            return retVal
        }
        fun getS(map: MutableMap<Coor, Char>): String {
            val maxX = (map.keys.map{it.x}.maxOrNull() ?: 0)
            val maxY = (map.keys.map{it.y}.maxOrNull() ?: 0)
            var retVal = ""
            for (i in 0..maxX)
                retVal += map[Coor(i,maxY)]
            return retVal
        }
        fun getE(map: MutableMap<Coor, Char>): String {
            val maxX = (map.keys.map{it.x}.maxOrNull() ?: 0)
            val maxY = (map.keys.map{it.y}.maxOrNull() ?: 0)
            var retVal = ""
            for (i in 0..maxY)
                retVal += map[Coor(maxX,i)]
            return retVal
        }
        fun getW(map: MutableMap<Coor, Char>): String {
            val maxY = (map.keys.map{it.y}.maxOrNull() ?: 0)
            var retVal = ""
            for (i in 0..maxY)
                retVal += map[Coor(0,i)]
            return retVal
        }
    }
}