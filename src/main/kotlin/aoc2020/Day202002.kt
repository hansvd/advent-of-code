package aoc2020

class PasswordPolicy(c:Char, i1:Int, i2:Int, password:String) {
    val isValid: Boolean = password.count { it == c } in i1..i2

    val isValid2: Boolean = let {
        val b1 = if (password[i1-1] == c) 1 else 0
        val b2 = if (password[i2-1] == c) 1 else 0
        b1 + b2 == 1
    }
}

object Day202002 {
    fun part01(lines:Sequence<String>) = lines.mapNotNull { parseLine(it)}.filter { it.isValid}.count()
    fun part02(lines:Sequence<String>) = lines.mapNotNull { parseLine(it)}.filter { it.isValid2}.count()

    private fun parseLine(l:String):PasswordPolicy? {
        val reg = """(\d+)-(\d+) ([a-z]): (.*)""".toRegex()
        val match = reg.matchEntire(l) ?: return null
        return PasswordPolicy(
            match.groupValues[3][0], match.groupValues[1].toInt(), match.groupValues[2].toInt(),
            match.groupValues[4]
        )
    }
}