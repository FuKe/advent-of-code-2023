fun main() {
    testPartOne()
}

private fun partOne(puzzleInput: List<String>): Int {
    var sum = 0
    val specialCharRegex = Regex("[^A-Za-z0-9.]")

    puzzleInput.forEach { s ->
        s.windowed(3, 1, true).forEachIndexed { index, partial ->
            val lookupIndexEnd = (index + 4).coerceAtMost(s.length-1)
            val partialWithSurrounding = s.substring((index-1).coerceAtLeast(0), lookupIndexEnd)

            val optNumber: Int? = partial.toIntOrNull()

            if (optNumber != null) {
                val relevantLookupLines: List<String> = when (index) {
                    0 -> listOf(puzzleInput[1])
                    puzzleInput.size-1 -> listOf(puzzleInput[index-1])
                    else -> listOf(puzzleInput[index-1], puzzleInput[index+1])
                }.map {
                    it.substring(index, lookupIndexEnd)
                } + partialWithSurrounding

                if (relevantLookupLines.any { specialCharRegex.find(it) != null }) {
                    sum += optNumber
                }
            }

        }
    }

    return sum
}

private val examplePuzzleInput = listOf(
    "467..114..",
    "...*......",
    "..35..633.",
    "......#...",
    "617*......",
    ".....+.58.",
    "..592.....",
    "......755.",
    "...$.*....",
    ".664.598.."
)

private fun testPartOne() {
    val testResult = partOne(examplePuzzleInput)
    assert(testResult == 4361) { "Test result was: $testResult" }
}
