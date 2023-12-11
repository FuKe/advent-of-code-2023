fun main() {
    val puzzleInput: List<String> = readInput("Day03.txt")

    testPartOne()
    val partOneResult: Int = partOne(puzzleInput)
    println("Part one: $partOneResult")

    testPartTwo()
}

private fun partOne(puzzleInput: List<String>): Int {
    var sum = 0
    val specialCharRegex = Regex("[^A-Za-z0-9.]")
    val numberRegex = Regex("\\d+")

    puzzleInput.forEachIndexed { verticalIndex, s ->
        var optNumberMatch: MatchResult? = numberRegex.find(s)

        while (optNumberMatch != null) {
            val minLookupIndex = (optNumberMatch.range.first - 1).coerceAtLeast(0)
            val maxLookupIndex = (optNumberMatch.range.last + 2).coerceAtMost(s.length - 1)

            val relevantLookupChunks: List<String> = when (verticalIndex) {
                0 -> listOf(puzzleInput[1])
                puzzleInput.size-1 -> listOf(puzzleInput[verticalIndex-1])
                else -> listOf(puzzleInput[verticalIndex-1], puzzleInput[verticalIndex+1])
            }.map {
                it.substring(minLookupIndex, maxLookupIndex)
            } + s.substring(minLookupIndex, maxLookupIndex)

            if (relevantLookupChunks.any { specialCharRegex.find(it) != null }) {
                val number: Int = optNumberMatch.value.toInt()
                sum += number
            }
            optNumberMatch = optNumberMatch.next()
        }
    }

    return sum
}

private fun partTwo(puzzleInput: List<String>): Int {
    var sum = 0

    val gearRegex = Regex("\\*")
    val numberRegex = Regex("\\d+")

    puzzleInput.forEachIndexed { index, s ->
        var optGearMatch: MatchResult? = gearRegex.find(s)

        while (optGearMatch != null) {
            val minLookupIndex = (optGearMatch.range.first - 3).coerceAtLeast(0)
            val maxLookupIndex = (optGearMatch.range.last + 4).coerceAtMost(s.length - 1)

            val relevantLookupChunks: List<String> = when (index) {
                0 -> listOf(puzzleInput[1])
                puzzleInput.size-1 -> listOf(puzzleInput[index-1])
                else -> listOf(puzzleInput[index-1], puzzleInput[index+1])
            }.map{
                it.substring(minLookupIndex, maxLookupIndex)
            }.plus(s.substring(minLookupIndex, maxLookupIndex))

            if (relevantLookupChunks.size > 1) {
                sum += relevantLookupChunks.sumOf {
                    numberRegex.find(it)?.value?.toInt() ?: 0
                }
            }

            optGearMatch = optGearMatch.next()
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

private fun testPartTwo() {
    val testResult = partTwo(examplePuzzleInput)
    assert(testResult == 467835) { "Test result was: $testResult" }
}
