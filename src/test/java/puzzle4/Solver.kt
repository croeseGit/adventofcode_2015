package puzzle4

import java.math.BigInteger
import java.security.MessageDigest

class Solver {

    fun solve(content: String): Result {

        val result = Result()

        result.numberProducesMD5With5Zeros = findMD5StartingWith(content, "00000")
        result.numberProducesMD5With6Zeros = findMD5StartingWith(content, "000000")

        return result
    }

    private fun findMD5StartingWith(input: String, prefix: String): Int? {
        for (i in 1..10000000) {
            if ("$input$i".md5().startsWith(prefix)) {
                return i
            }
        }
        return null
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }
}
