package puzzle8

import InputReader
import org.junit.Test

const val INPUT_FILE = "input_8.txt"

class Puzzle8 {

    @Test
    fun puzzle8() {
        val instructions = InputReader().getLines(INPUT_FILE)
        val result = Solver().solve(instructions)

        println("Puzzle 8: $result")
    }
}
