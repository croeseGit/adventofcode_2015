package puzzle2

class Solver {

    fun solve(presents: List<Present>): Result {
        val result = Result()
        presents.forEach {
            result.wrappingPaper += getWrappingPaper(it)
            result.ribbon += getRibbon(it)
        }
        return result
    }

    private fun getWrappingPaper(present: Present): Int {
        val surfaceA = present.l * present.w
        val surfaceB = present.w * present.h
        val surfaceC = present.h * present.l
        val smallestSurface = minOf(surfaceA, surfaceB, surfaceC)

        return smallestSurface + 2 * (surfaceA + surfaceB + surfaceC)
    }

    private fun getRibbon(present: Present): Int {
        val sortedDimensions = listOf(present.l, present.w, present.h).sorted()
        val ribbonToWrap = 2 * sortedDimensions[0] + 2 * sortedDimensions[1]
        val ribbonBow = present.l * present.w * present.h

        return ribbonBow + ribbonToWrap
    }
}
