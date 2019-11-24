package puzzle3

class Solver {

    fun solve(content: String): Result {
        val inputSanta = StringBuilder()
        val inputRobotSanta = StringBuilder()
        for ((index, char) in content.withIndex()) {
            if (index % 2 == 0) {
                inputSanta.append(char)
            } else {
                inputRobotSanta.append(char)
            }
        }

        val gridSantaOnly = createGrid(content)
        val gridSanta = createGrid(inputSanta.toString())
        val gridRobotSanta = createGrid(inputRobotSanta.toString())

        return Result(
            housesSantaOnly = getHouses(gridSantaOnly),
            housesWithRobot = getHouses(gridSanta, gridRobotSanta)
        )
    }

    private fun createGrid(content: String): MutableMap<Int, MutableMap<Int, Int>> {
        val currentPosition = Position()
        val grid = mutableMapOf<Int, MutableMap<Int, Int>>()
        grid[currentPosition.y] = createRow(currentPosition.x)

        for (char in content) {
            when (char) {
                '^' -> currentPosition.y += 1
                'v' -> currentPosition.y += -1
                '>' -> currentPosition.x += 1
                '<' -> currentPosition.x += -1
                else -> throw RuntimeException("Unexpected character $char in input")
            }
            when {
                grid[currentPosition.y] == null -> {
                    grid[currentPosition.y] = createRow(currentPosition.x)
                }
                grid[currentPosition.y]!![currentPosition.x] == null -> grid[currentPosition.y]!![currentPosition.x] = 1
                else -> grid[currentPosition.y]!![currentPosition.x]!!.plus(1)
            }
        }

        return grid
    }

    private fun getHouses(grid: MutableMap<Int, MutableMap<Int, Int>>): Int {
        var houses = 0
        for ((_, row) in grid) {
            for (x in row) {
                houses++
            }
        }
        return houses
    }

    private fun getHouses(
        gridSanta: MutableMap<Int, MutableMap<Int, Int>>,
        gridRobotSanta: MutableMap<Int, MutableMap<Int, Int>>
    ): Int {
        var houses = getHouses(gridSanta)

        for ((y, row) in gridRobotSanta) {
            for ((x, _) in row) {
                if (gridSanta[y] == null) {
                    houses++
                } else if (gridSanta[y]!![x] == null) {
                    houses++
                }
            }
        }

        return houses
    }

    private fun createRow(x: Int): MutableMap<Int, Int> {
        val row = mutableMapOf<Int, Int>()
        row[x] = 1
        return row
    }

    data class Position(var x: Int = 0, var y: Int = 0)
}
