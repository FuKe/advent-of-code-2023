fun main() {
    val puzzleInput: List<String> = readInput("Day01.txt")

    partOneTest()

    val partOneResult = partOne(puzzleInput)
    println(partOneResult)
}

private fun partOne(puzzleInput: List<String>) =
    puzzleInput.map {
        it.filter { char -> char.isDigit() }
    }.sumOf {
        "${it.first()}${it.last()}".toInt()
    }

private fun partOneTest() {
    val testData = listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")
    val testResult = partOne(testData)
    assert(testResult == 142)
}

private val wordToInt = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)
private fun partTwo(puzzleInput: List<String>): Int {
    TODO()
}



private fun partTwoTest() {
    val testData = listOf(
        "two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen"
    )
    val testResult = partTwo(testData)
    assert(testResult == 281)
}
