package puzzle8

class Solver {

    fun solve(instructions: List<String>): Result {
        val discrepancy = instructions.fold(0) {
                acc, it ->
            acc + getNumberOfCharactersOfCode(it) - getNrOfCharactersInMemory(it)
        }

        return Result(discrepancy)
    }

    private fun getNumberOfCharactersOfCode(text: String): Int = text.length

    private fun getNrOfCharactersInMemory(text: String): Int {
        val hexRegex = "[\\\\][x][0-9a-f][0-9a-f]".toRegex()
        val quoteRegex = "[\\\\][\"]".toRegex()
        val backslashRegex = "[\\\\][\\\\]".toRegex()

        val cleanedText = text
            .run { replaceWithDash(this, hexRegex) }
            .run { replaceWithDash(this, quoteRegex) }
            .run { replaceWithDash(this, backslashRegex) }
            .run { cleanSurroundingQuotes(this) }

        return cleanedText.length
    }

    private fun replaceWithDash(text: String, regex: Regex): String {
        val match = regex.find(text) ?: return text
        val newText = "${text.substring(0, match.range.first)}-${text.substring(match.range.last + 1, text.length)}"
        return replaceWithDash(newText, regex)
    }

    private fun cleanSurroundingQuotes(text: String): String = text.substring(1, text.length - 1)
}
