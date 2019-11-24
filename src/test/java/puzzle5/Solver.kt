package puzzle5

import java.util.function.Predicate

class Solver {

    fun solve(words: List<String>): Result {
        val result = Result()

        result.countNiceWordsWithOldRules = words.count { it -> isNice(it, getOldRules()) }
        result.countNiceWordsWithNewRules = words.count { it -> isNice(it, getNewRules()) }

        return result
    }

    private fun isNice(word: String, predicates: List<Predicate<String>>): Boolean {
        return predicates.all { predicate -> predicate.test(word) }
    }

    private fun getOldRules(): List<Predicate<String>> {
        return listOf(
            Predicate { word -> listOf("ab", "cd", "pq", "xy").none { it -> word.contains(it) } },
            Predicate { word -> word.count { char -> listOf('a', 'e', 'i', 'o', 'u').contains(char) } > 2 },
            Predicate { word -> `contains at least one letter that appears twice in a row`(word) }
        )
    }

    private fun `contains at least one letter that appears twice in a row`(word: String): Boolean {
        if (word.count() == 0) {
            return false
        }
        var lastChar = word[0]
        for ((index, char) in word.withIndex()) {
            if (index == 0) {
                continue
            }
            if (char == lastChar) {
                return true
            }
            lastChar = char
        }

        return false
    }

    private fun getNewRules(): List<Predicate<String>> {
        return listOf(
            Predicate { word -> `has at least one letter which repeats with exactly one letter between them`(word) },
            Predicate { word ->
                `contains a pair of any two letters that appears at least twice in the string without overlapping`(
                    word
                )
            }
        )
    }

    private fun `has at least one letter which repeats with exactly one letter between them`(word: String): Boolean {
        for ((index, _) in word.withIndex()) {
            if (index == 0 || index == word.length - 1) {
                continue
            }

            val leftLetter = word[index - 1]
            val rightLetter = word[index + 1]

            if (leftLetter == rightLetter) {
                return true
            }
        }

        return false
    }

    private fun `contains a pair of any two letters that appears at least twice in the string without overlapping`(word: String): Boolean {
        for ((index, _) in word.withIndex()) {
            if (index == word.length - 1) {
                break
            }
            val letter1 = word[index]
            val letter2 = word[index + 1]

            val pair = "$letter1$letter2"

            val rest = word.drop(index + 2)

            if (rest.contains(pair)) {
                return true
            }
        }

        return false
    }
}
