package solutions

import utils.readInput

private val input = readInput("day3").toString()
private val REGEX_P1 = "mul\\(\\d+,\\d+\\)".toRegex()
private val REGEX_P2 = "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)".toRegex()
private val NUMBER_REGEX = "\\d+,\\d+".toRegex()
private const val DO = "do()"
private const val DONT = "don't()"

private fun part1(): Int {
    val matches = getMatches(REGEX_P1)
    val valuesList = matches.flatMap { match ->
        match.groupValues.map {
            extractNumbers(it)
        }
    }.toList()
    return valuesList.sumOf { it[0] * it[1] }
}

private fun part2(): Int {
    val matches = getMatches(REGEX_P2)
    val values = matches.flatMap { match ->
        match.groupValues.map { it }
    }.toList()
    val filteredValues = filterInstructions(values)
    return filteredValues.sumOf { it[0] * it[1] }
}

private fun getMatches(regex: Regex): Sequence<MatchResult> {
    return regex.findAll(input)
}

private fun extractNumbers(vals: String): List<Int> {
    val match = NUMBER_REGEX.find(vals)
    if (match != null) {
        return match.groupValues.flatMap { val1 -> val1.split(",").map { it.toInt() } }
    }
    return mutableListOf()
}

private fun filterInstructions(vals: List<String>): List<List<Int>> {
    var doAsTold = true
    val values = mutableListOf<List<Int>>()
    vals.forEach {
        if (it == DO) {
            doAsTold = true
        } else if (it == DONT) {
            doAsTold = false
        } else {
            if (doAsTold) {
                values.add(extractNumbers(it))
            }
        }
    }
    return values
}

fun main() {
    println(part1())
    println(part2())
}