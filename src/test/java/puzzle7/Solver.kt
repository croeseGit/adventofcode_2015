package puzzle7

import java.lang.RuntimeException

class Solver {

    private val wires = mutableMapOf<String, String>()

    private val solvedOperands = mutableMapOf<String, Int>()

    fun solve(instructions: List<String>): Result {
        instructions.forEach {
            val parts = it.split(" -> ")
            wires[parts[1]] = parts[0]
        }

        val signalA = solve("a")
        solvedOperands.clear()
        wires["b"] = signalA.toString()
        val signalAWithDifferentB = solve("a")
        return Result(
            signalA = signalA,
            signalAWithDifferentB = signalAWithDifferentB
        )
    }

    fun solve(operand: String): Int {
        if (operand.toIntOrNull() != null) {
            return operand.toInt()
        }
        if (solvedOperands[operand] != null) {
            return solvedOperands[operand]!!
        }
        val wire = wires[operand] ?: throw RuntimeException("Not solvable. Operand $operand not found")

        val solvedOperand = when {
            wire.toIntOrNull() != null -> {
                wire.toInt()
            }
            wire.contains("NOT") -> solve(wire.replace("NOT ", "")).inv()
            wire.contains("AND") -> {
                val operands = wire.split(" AND ")
                solve(operands[0]) and solve(operands[1])
            }
            wire.contains("OR") -> {
                val operands = wire.split(" OR ")
                solve(operands[0]) or solve(operands[1])
            }
            wire.contains("LSHIFT") -> {
                val operands = wire.split(" LSHIFT ")
                solve(operands[0]) shl solve(operands[1])
            }
            wire.contains("RSHIFT") -> {
                val operands = wire.split(" RSHIFT ")
                solve(operands[0]) shr solve(operands[1])
            }
            else -> solve(wire)
        }

        solvedOperands[operand] = solvedOperand
        return solvedOperand
    }
}
