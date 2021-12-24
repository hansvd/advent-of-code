

object Day24 {
    class Var(private val name: String, var value:Int = 0) {
        fun set(v:Int) {
            value = v
        }

        override fun toString(): String {
            return if (name[0].isDigit()) value.toString() else "$name=$value"
        }
    }
    data class ALUState(var input:String,val w:Var = Var("w"), val x:Var=Var("x"), val y:Var=Var("y"), val z:Var=Var("z")) {
        fun result():Long = 0
        fun getVar(name:String):Var {
                        return when(name) {
                "w" -> w
                "x" -> x
                "y" -> y
                "z" -> z
                else -> Var(name,Integer.parseInt(name))
            }
        }
        fun calc(var1:String, var2:String,f:(v1:Var,v2:Var) -> Unit) {
            val v1 = getVar(var1)
            val v2 = getVar(var2)
            f(v1,v2)
        }

        override fun toString(): String {
            return "$input -> $w, $x, $y, $z"
        }

//        fun parse(input:String):Var {

//        }
    }
    interface Instruction {
        fun calculate(state:ALUState):ALUState

    }
    data class InputInstruction(val variable:String):Instruction {
        override fun calculate(state:ALUState):ALUState {
            val i = state.input[0].code - '0'.code
            val v = state.getVar(variable)
            v.set(i)
            state.input = state.input.substring(1)
            return state
        }
    }

    data class AddInstruction(val var1:String, val var2:String):Instruction {
        override fun calculate(state: ALUState): ALUState {
            state.calc(var1,var2) { v1,v2 -> v1.set(v1.value + v2.value)}
            return state
        }
    }

    data class MulInstruction(val var1:String, val var2:String):Instruction {
        override fun calculate(state: ALUState): ALUState {
            state.calc(var1,var2) { v1,v2 -> v1.set(v1.value * v2.value)}
            return state
        }
    }

    data class DivInstruction(val var1:String, val var2:String):Instruction {
        override fun calculate(state: ALUState): ALUState {
            state.calc(var1,var2) { v1,v2 -> v1.set(v1.value / v2.value)}
            return state
        }
    }

    data class ModInstruction(val var1:String, val var2:String):Instruction {
        override fun calculate(state: ALUState): ALUState {
            state.calc(var1,var2) { v1,v2 -> v1.set(v1.value % v2.value)}
            return state
        }
    }

    data class EqlInstruction(val var1:String, val var2:String):Instruction {
        override fun calculate(state: ALUState): ALUState {
            state.calc(var1,var2) { v1,v2 -> v1.set(if (v1.value == v2.value) 1 else 0)}
            return state
        }
    }

    class ALU(private val instructions:List<Instruction>) {
        fun calculate(input:String):ALUState = instructions.fold(ALUState(input)) { s, i -> i.calculate(s) }
    }

    fun calculate(lines: Sequence<String>, input:String): ALUState {
        val monad = parseInput(lines)
        return monad.calculate(input)
    }

    fun parseInput(lines:Sequence<String>):ALU {
        return ALU(lines.map { parseInstruction(it)}.toList())
    }

    private fun parseInstruction(line: String):Instruction {
        val p = line.split(' ')
        return when (p[0]) {
            "inp" -> InputInstruction(p[1])
            "add" -> AddInstruction(p[1],p[2])
            "mul" -> MulInstruction(p[1],p[2])
            "div" -> DivInstruction(p[1],p[2])
            "mod" -> ModInstruction(p[1],p[2])
            "eql" -> EqlInstruction(p[1],p[2])
            else -> { throw NotImplementedError()}
        }
    }
}