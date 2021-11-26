package days

class Day10 : Day(10) {
    abstract class Container(val id: Int) {
        val items = mutableListOf<Int>()
    }

    class Bot(id: Int) : Container(id) {
        var instruction: BotInstruction? = null
        override fun toString(): String {
            return "Bot $id"
        }
    }

    class Output(id: Int) : Container(id) {
        override fun toString(): String {
            return "Output $id"
        }
    }

    val bots = mutableMapOf<Int, Bot>()
    val outputs = mutableMapOf<Int, Output>()

    data class BotInstruction(val lowTo: Container, val highTo: Container)

    override fun partOne(): Any {
        inputList.forEach {
            var data = getStringsFromExpression("value $ goes to bot $", it)
            if (data != null) {
                if (bots[data[1].toInt()] == null) bots[data[1].toInt()] = Bot(data[1].toInt())
                bots[data[1].toInt()]!!.items.add(data[0].toInt())
            }
            data = getStringsFromExpression("bot $ gives low to $ $ and high to $ $", it)
            if (data != null) {
                if (bots[data[0].toInt()] == null) bots[data[0].toInt()] = Bot(data[0].toInt())
                bots[data[0].toInt()] = bots[data[0].toInt()] ?: Bot(data[0].toInt())
                if (data[1] == "output")
                    if (outputs[data[2].toInt()] == null) outputs[data[2].toInt()] = Output(data[2].toInt())
                if (data[1] == "bot")
                    if (bots[data[2].toInt()] == null) bots[data[2].toInt()] = Bot(data[2].toInt())
                if (data[3] == "output")
                    if (outputs[data[4].toInt()] == null) outputs[data[4].toInt()] = Output(data[4].toInt())
                if (data[3] == "bot")
                    if (bots[data[4].toInt()] == null) bots[data[4].toInt()] = Bot(data[4].toInt())
                val c1 = if (data[1] == "output") outputs[data[2].toInt()] else bots[data[2].toInt()]
                val c2 = if (data[3] == "output") outputs[data[4].toInt()] else bots[data[4].toInt()]
                val botInstruction = BotInstruction(c1!!, c2!!)
                bots[data[0].toInt()]!!.instruction = botInstruction
            }
        }
        //Process instruction if needed
        while (true)
            for (bot in bots.values) {
                if (bot.items.size == 2 && bot.instruction != null) {
                    val high = bot.items.maxOrNull()!!
                    val low = bot.items.minOrNull()!!
                    bot.instruction!!.highTo.items.add(high)
                    bot.instruction!!.lowTo.items.add(low)
                    if (high == 61 && low == 17) return bot.id
                    bot.items.clear()
                }
            }

        return 0
    }

    private fun getStringsFromExpression(expression: String, check: String): List<String>? {
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

    override fun partTwo(): Any {
        bots.clear()
        outputs.clear()
        inputList.forEach {
            var data = getStringsFromExpression("value $ goes to bot $", it)
            if (data != null) {
                if (bots[data[1].toInt()] == null) bots[data[1].toInt()] = Bot(data[1].toInt())
                bots[data[1].toInt()]!!.items.add(data[0].toInt())
            }
            data = getStringsFromExpression("bot $ gives low to $ $ and high to $ $", it)
            if (data != null) {
                if (bots[data[0].toInt()] == null) bots[data[0].toInt()] = Bot(data[0].toInt())
                bots[data[0].toInt()] = bots[data[0].toInt()] ?: Bot(data[0].toInt())
                if (data[1] == "output")
                    if (outputs[data[2].toInt()] == null) outputs[data[2].toInt()] = Output(data[2].toInt())
                if (data[1] == "bot")
                    if (bots[data[2].toInt()] == null) bots[data[2].toInt()] = Bot(data[2].toInt())
                if (data[3] == "output")
                    if (outputs[data[4].toInt()] == null) outputs[data[4].toInt()] = Output(data[4].toInt())
                if (data[3] == "bot")
                    if (bots[data[4].toInt()] == null) bots[data[4].toInt()] = Bot(data[4].toInt())
                val c1 = if (data[1] == "output") outputs[data[2].toInt()] else bots[data[2].toInt()]
                val c2 = if (data[3] == "output") outputs[data[4].toInt()] else bots[data[4].toInt()]
                val botInstruction = BotInstruction(c1!!, c2!!)
                bots[data[0].toInt()]!!.instruction = botInstruction
            }
        }
        //Process instruction if needed
        while (true)
            for (bot in bots.values) {
                if (bot.items.size == 2 && bot.instruction != null) {
                    val high = bot.items.maxOrNull()!!
                    val low = bot.items.minOrNull()!!
                    bot.instruction!!.highTo.items.add(high)
                    bot.instruction!!.lowTo.items.add(low)
                    println ("$bot : $high -> ${bot.instruction!!.highTo}")
                    println ("$bot : $low -> ${bot.instruction!!.lowTo}")
                    bot.items.clear()
                }
                if (outputs[0]!!.items.isNotEmpty() && outputs[1]!!.items.isNotEmpty() && outputs[2]!!.items.isNotEmpty())
                    return outputs[0]!!.items[0]*outputs[1]!!.items[0]*outputs[2]!!.items[0]
            }
    }
}
