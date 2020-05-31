package puzzle9

import java.lang.Integer.parseInt

class Solver {

    fun solve(instructions: List<String>): Result {
        val parsedInstructions = instructions.map { parseInstruction(it) }

        // [London, Dublin, ...]
        val locations = getLocations(parsedInstructions)

        /*
            [
                [London, Dublin, ...],
                [Dublin, London, ...],
                ...
            ]
         */
        val ways = permute(locations)
        val distances = ways.map { getDistance(it, parsedInstructions) }
        return Result(
            shortestDistance = distances.min()!!,
            longestDistance = distances.max()!!
        )
    }

    /*
        [
            [London, Dublin],
            [Dublin, London],
        ]
     */
    private fun permute(locations: List<String>): ArrayList<ArrayList<String>> {
        val arrayList = arrayListOf<String>()
        arrayList.addAll(locations)
        return permute(arrayList, -1)
    }

    private fun permute(permutation: ArrayList<String>, fixedIdx: Int): ArrayList<ArrayList<String>> {
        val notSwappable = permutation.size < 2 || fixedIdx == permutation.size - 2
        if (notSwappable) {
            return arrayListOf(permutation)
        }

        val permutations = arrayListOf<ArrayList<String>>()

        var i = fixedIdx + 1
        while (i != permutation.size) {
            val swapped = swap(permutation, fixedIdx + 1, i)
            permutations.addAll(permute(swapped, fixedIdx + 1))
            i++
        }

        return permutations
    }

    private fun swap(locations: ArrayList<String>, left: Int, right: Int): ArrayList<String> {
        val permutation = arrayListOf<String>()
        permutation.addAll(locations)
        val temp = permutation[left]
        permutation[left] = permutation[right]
        permutation[right] = temp
        return permutation
    }

    private fun getLocations(parsedInstructions: List<ParsedInstruction>): List<String> {
        return parsedInstructions.flatMap { listOf(it.locationA, it.locationB) }.distinct()
    }

    private fun parseInstruction(instruction: String): ParsedInstruction {
        val equality = instruction.split(" = ")
        val locations = equality[0].split(" to ")
        return ParsedInstruction(
            locationA = locations[0],
            locationB = locations[1],
            distance = parseInt(equality[1])
        )
    }

    data class ParsedInstruction(
        val locationA: String,
        val locationB: String,
        val distance: Int
    )

    private fun getDistance(locations: ArrayList<String>, parsedInstructions: List<ParsedInstruction>): Int {
        var sum = 0
        for ((index, value) in locations.withIndex()) {
            if (index + 1 < locations.size) {
                val locationB = locations[index + 1]
                val distance = parsedInstructions.find {
                    (it.locationA == value && it.locationB == locationB) ||
                            (it.locationB == value && it.locationA == locationB)
                }!!.distance
                sum += distance
            }
        }
        return sum
    }
}
