package solutions

import utils.readInput
import kotlin.math.pow

private val input = readInput("day7").flatMap { it.split(",") }

private fun part1(): Long {
    return input.sumOf { isValid(it) }
}

private fun isValid(list: String): Long {
    val listOfOperators = listOf("*", "+")
    val values = list.split(":", " ")
        .filter { it.isNotEmpty() }
        .map(String::toLong)

    val totalCombinations = listOfOperators.size.toDouble().pow((values.size - 1).toDouble()).toInt()
    val targetValue = values[0]

    for (combinationIndex in 1 until totalCombinations) {
        var sum = values.drop(1)[0]
        var currentCombination = combinationIndex

        for (i in 1 until values.drop(1).size) {
            val vals = values.drop(1)
            val operator = listOfOperators[currentCombination % listOfOperators.size]
            if (operator == "+") {
                sum += vals[i]
            } else {
                sum *= vals[i]
            }
            currentCombination /= listOfOperators.size
        }
        if (sum == targetValue) {
            return sum
        }
    }
    return 0
}

fun main() {
    println(part1())
}