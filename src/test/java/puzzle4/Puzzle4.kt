package puzzle4

import org.junit.Test

const val INPUT = "bgvyzdsv"

class Puzzle4 {

    @Test
    fun puzzle4() {
        val result = Solver().solve(INPUT)

        println("Puzzle 4: $result")
    }

}
