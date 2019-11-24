package puzzle6

class Solver {

    fun solve(instructions: List<Instruction>): Result {
        val litLightsMatrix = getInitialLitLightsMatrix()
        val brightnessMatrix = getInitialBrightnessMatrix()
        for (instruction in instructions) {
            for (x in instruction.cornerA.x..instruction.cornerB.x) {
                for (y in instruction.cornerA.y..instruction.cornerB.y) {
                    when {
                        Action.TURN_ON == instruction.action -> {
                            litLightsMatrix[x][y] = true
                            brightnessMatrix[x][y] = brightnessMatrix[x][y] + 1
                        }
                        Action.TURN_OFF == instruction.action -> {
                            litLightsMatrix[x][y] = false
                            brightnessMatrix[x][y] =
                                    if (brightnessMatrix[x][y] - 1 < 0) 0 else brightnessMatrix[x][y] - 1
                        }
                        else -> {
                            litLightsMatrix[x][y] = !litLightsMatrix[x][y]
                            brightnessMatrix[x][y] = brightnessMatrix[x][y] + 2
                        }
                    }
                }
            }
        }

        return Result(
            lights = countLights(litLightsMatrix),
            brightness = sumBrightness(brightnessMatrix)
        )
    }

    private fun getInitialLitLightsMatrix(): Array<Array<Boolean>> {
        var matrix = arrayOf<Array<Boolean>>()
        for (i in 0 until 1000) {
            var array = arrayOf<Boolean>()
            for (j in 0 until 1000) {
                array += false
            }
            matrix += array
        }
        return matrix
    }

    private fun getInitialBrightnessMatrix(): Array<Array<Int>> {
        var matrix = arrayOf<Array<Int>>()
        for (i in 0 until 1000) {
            var array = arrayOf<Int>()
            for (j in 0 until 1000) {
                array += 0
            }
            matrix += array
        }
        return matrix
    }

    private fun countLights(matrix: Array<Array<Boolean>>): Int {
        var lights = 0
        for (array in matrix) {
            for (value in array) {
                if (value) {
                    lights++
                }
            }
        }
        return lights
    }

    private fun sumBrightness(matrix: Array<Array<Int>>): Int {
        var brightness = 0
        for (array in matrix) {
            for (value in array) {
                brightness += value
            }
        }
        return brightness
    }
}
