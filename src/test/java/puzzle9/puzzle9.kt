package puzzle9

import InputReader
import org.junit.Test

const val INPUT_FILE = "input_9.txt"

class Puzzle9 {

    @Test
    fun puzzle9() {
        val instructions = InputReader().getLines(INPUT_FILE)
        val result = Solver().solve(instructions)

        println("Puzzle 9: $result")
    }
}
