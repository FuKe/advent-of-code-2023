import kotlin.math.pow

fun main() {
    val puzzleInput: List<String> = readInput("Day04.txt")

    testPartOne()
    val partOneResult: Long = partOne(puzzleInput)
    println("Part one: $partOneResult")
//
//    testPartTwo()
//    val partTwoResult: Int = partTwo(puzzleInput)
//    println("Part two: $partTwoResult")
}

private fun partOne(puzzleInput: List<String>): Long {
    val parsedCards: List<Pair<List<Int>, List<Int>>> = parseCards(puzzleInput)
    return parsedCards.sumOf { (winningNumbers, myNumbers) ->
        val x = myNumbers.count {
            it in winningNumbers
        } - 1
        2f.pow(x).toLong()
    }
}

private fun partTwo(puzzleInput: List<String>): Int {
    return 0
}

private fun parseCards(puzzleInput: List<String>): List<Pair<List<Int>, List<Int>>> =
    puzzleInput.map {
        val (left, right) = it.split(":").last().split("|")
        val winningNumbers: List<Int> = left.split(" ")
            .filter { numStr -> numStr.isNotBlank() }
            .map { numStr -> numStr.toInt() }
        val myNumbers: List<Int> = right.split(" ")
            .filter { numStr -> numStr.isNotBlank() }
            .map { numStr -> numStr.toInt() }
        Pair(winningNumbers, myNumbers)
    }

private val exampleInput = listOf(
    "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
    "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
    "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
    "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
    "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
    "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
)
private fun testPartOne() {
    val testResult = partOne(exampleInput)
    assert(testResult == 13L) { "Test result of partOne was: $testResult" }
}

private fun testPartTwo() {

}
