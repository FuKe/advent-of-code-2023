import kotlin.system.measureTimeMillis

fun main() {
    val puzzleInput: List<String> = readInput("Day01.txt")

    partOneTest()
    val partOneResult: Int
    val millisPartOne = measureTimeMillis {
        partOneResult = partOne(puzzleInput)
    }
    println(partOneResult)
    println("Time millis: $millisPartOne")

    println("----------")

    partTwoTest()
    val partTwoResult: Int
    val millisPartTwo = measureTimeMillis {
        partTwoResult = partTwo(puzzleInput)
    }

    println(partTwoResult)
    println("Time millis: $millisPartTwo")
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
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9"
)
private fun partTwo(puzzleInput: List<String>): Int {
    val numberList: Set<String> = wordToInt.keys + wordToInt.values
    return puzzleInput.sumOf {
        val first: String = it.findAnyOf(numberList)!!.second
        val last: String = it.findLastAnyOf(numberList)!!.second

        val firstInt: Int = wordToInt[first]?.toInt() ?: first.toInt()
        val lastInt: Int = wordToInt[last]?.toInt() ?: last.toInt()

        "$firstInt$lastInt".toInt()
    }
}



private fun partTwoTest() {
    val testData = listOf(
        "two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen"
    )
    val testResult = partTwo(testData)
    assert(testResult == 281)
}
