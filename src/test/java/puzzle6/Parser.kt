package puzzle6

class Parser {

    fun parse(lines: List<String>): List<Instruction> {
        return lines.map { it -> this.convert(it) }
    }

    private fun convert(line: String): Instruction {
        val action =
            when {
                line.startsWith("toggle") -> Action.TOGGLE
                line.startsWith("turn on") -> Action.TURN_ON
                else -> Action.TURN_OFF
            }

        val coordinates = line
            .replace("turn on ", "")
            .replace("turn off ", "")
            .replace("toggle ", "")
            .split(" through ")
            .map { coordinateAsString ->
                val numbers = coordinateAsString.split(",")
                assert(numbers.size == 2)
                Coordinate(x = numbers[0].toInt(), y = numbers[1].toInt())
            }
        assert(coordinates.size == 2)
        return Instruction(coordinates[0], coordinates[1], action)
    }

}
