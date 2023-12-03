fun main() {
    val puzzleInput: List<String> = readInput("Day02.txt")

    testPartOne()
    val partOneResult: Int = partOne(puzzleInput)
    println(partOneResult)

}

private fun partOne(puzzleInput: List<String>): Int {
    val maxByColor = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    return puzzleInput.sumOf { game ->
        val gameId: Int = getGameId(game)
        val parsedGame: List<Map<String, Int>> = parseGame(game)

        var gamePossible = true
        parsedGame.forEach { round ->
            round.entries.forEach { (color, number) ->
                if (maxByColor[color]!! < number) {
                    gamePossible = false
                }
            }
        }

        if (gamePossible) gameId else 0
    }
}

/* Parse a game to the following format:
* Each element in the returned list represents A ROUND in the game (rounds are separated by semicolons).
* Each key in the map represents A COLOR in the given round, with the value being the NUMBER OF the given color.
*  */
private fun parseGame(game: String): List<Map<String, Int>> {
    val rounds: List<String> = game.split(":").last().split(";")

    return rounds.map { round ->
        round.split(",").associate {
            val numberAndColor = it.trim().split(" ")
            val number: Int = numberAndColor.first().toInt()
            numberAndColor.last() to number
        }
    }
}

private fun getGameId(game: String): Int =
    game.split(":").first().split(" ").last().toInt()


private fun testPartOne() {
    val puzzleInput: List<String> = listOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    )

    val testResult = partOne(puzzleInput)
    assert(testResult == 8)
}
