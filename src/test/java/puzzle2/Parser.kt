package puzzle2

class Parser {

    fun parse(lines: List<String>): List<Present> {
        return lines.map { it -> this.convert(it) }
    }

    private fun convert(line: String): Present {
        val parts = line.split('x')
        return Present(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
    }
}
