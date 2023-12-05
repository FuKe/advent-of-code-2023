fun main() {
    testPartOne()
}

private fun partOne(puzzleInput: List<String>): Int {
    var sum = 0
    val specialCharRegex = Regex("[^A-Za-z0-9.]")


    puzzleInput.forEachIndexed { verticalIndex, s ->
        var lastWholeNumIndexRange: IntRange? = null

        s.windowed(5, 1, partialWindows = true).forEachIndexed { horizontalIndex, partial ->
            val optNumber: Int? = partial.replace(Regex("[^0-9]"), "").toIntOrNull()

            if (optNumber != null && optNumber > 10) {
                val numIndexStart = partial.indexOfFirst { c -> c.isDigit() } + horizontalIndex
                val numIndexEnd = partial.indexOfLast { c -> c.isDigit() } + horizontalIndex

                val skip = lastWholeNumIndexRange?.contains(numIndexStart) ?: false
                if (!skip) {
                    val minLookupIndex = (numIndexStart - 1).coerceAtLeast(0)
                    val maxLookupIndex = (numIndexEnd + 2).coerceAtMost(s.length - 1)

                    val relevantLookupChunks: List<String> = when (verticalIndex) {
                        0 -> listOf(puzzleInput[1])
                        puzzleInput.size-1 -> listOf(puzzleInput[verticalIndex-1])
                        else -> listOf(puzzleInput[verticalIndex-1], puzzleInput[verticalIndex+1])
                    }.map {
                        it.substring(minLookupIndex, maxLookupIndex)
                    } + partial

                    if (relevantLookupChunks.any { specialCharRegex.find(it) != null }) {
                        sum += optNumber
                    }

                    lastWholeNumIndexRange = IntRange(numIndexStart, numIndexEnd)
                }
            }
        }


//        s.windowed(5, 1, partialWindows = true).forEachIndexed { horizontalIndex, partial ->
//            val lookupIndexEnd = (horizontalIndex + 5).coerceAtMost(s.length-1)
//            val optNumber: Int? = partial.replace(Regex("[^0-9]"), "").toIntOrNull()
//
//            if (optNumber != null && optNumber > 10) {
//                val numIndexStart = partial.indexOfFirst { c -> c.isDigit() } + horizontalIndex
//                val numIndexEnd = partial.indexOfLast { c -> c.isDigit() } + horizontalIndex
//
//                val skip = lastWholeNumIndexRange?.contains(numIndexStart) ?: false
//                if (!skip) {
//                    val relevantLookupLines: List<String> = when (verticalIndex) {
//                        0 -> listOf(puzzleInput[1])
//                        puzzleInput.size-1 -> listOf(puzzleInput[verticalIndex-1])
//                        else -> listOf(puzzleInput[verticalIndex-1], puzzleInput[verticalIndex+1])
//                    }.map {
//                        it.substring(horizontalIndex, lookupIndexEnd)
//                    } + partial
//
//                    if (relevantLookupLines.any { specialCharRegex.find(it) != null }) {
//                        sum += optNumber
//                    }
//
//                    lastWholeNumIndexRange = IntRange(numIndexStart, numIndexEnd)
//                }
//            }
//        }
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
