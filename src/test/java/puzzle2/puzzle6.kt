package puzzle2

import InputReader
import org.junit.Test

const val INPUT_FILE = "input_2.txt"

class Puzzle2 {

    @Test
    fun puzzle2() {
        val input = InputReader().getLines(INPUT_FILE)
        val parsedInput = Parser().parse(input)
        val result = Solver().solve(parsedInput)

        println("Puzzle 2: $result")
    }
}
