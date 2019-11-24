package puzzle5

import InputReader
import org.junit.Test

const val INPUT_FILE = "input_5.txt"

class Puzzle5 {

    @Test
    fun puzzle5() {
        val lines = InputReader().getLines(INPUT_FILE)
        val result = Solver().solve(lines)

        println("Puzzle 5: $result")
    }
}
