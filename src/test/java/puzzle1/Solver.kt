package puzzle1

import java.lang.RuntimeException

const val FLOOR_BASEMENT = -1

class Solver {

    fun solve(content: String): Result {

        val result = Result()

        for ((index, char) in content.withIndex()) {
            when (char) {
                '(' -> result.floor++
                ')' -> result.floor--
                else -> throw RuntimeException("Unexpected character $char in input")
            }
            if (result.positionOfBeingAtBasement == null && result.floor == FLOOR_BASEMENT) {
                result.positionOfBeingAtBasement = index + 1
            }
        }

        return result
    }
}
