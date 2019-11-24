package puzzle6

import InputReader
import org.junit.Test

const val INPUT_FILE = "input_6.txt"

class Puzzle6 {

    @Test
    fun puzzle6() {
        val input = InputReader().getLines(INPUT_FILE)
        val parsedInput = Parser().parse(input)
        val result = Solver().solve(parsedInput)

        println("Puzzle 6: $result")
    }
}
