package puzzle7

import InputReader
import org.junit.Test

const val INPUT_FILE = "input_7.txt"

class Puzzle7 {

    @Test
    fun puzzle7() {
        val instructions = InputReader().getLines(INPUT_FILE)
        val result = Solver().solve(instructions)

        println("Puzzle 7: $result")
    }
}
