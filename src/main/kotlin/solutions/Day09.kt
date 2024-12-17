package solutions

import utils.readInput

private val input = readInput("day09").flatMap { it.split("").filter { it.isNotEmpty() } }.map(String::toInt)

private fun solvePart1(): Long {
    val list = sanitizeString()
    return list.withIndex().sumOf { (index, value) ->
        value * index
    }
}

private fun sanitizeString(): List<Long> {
    val string = generateString()
    var newString = StringBuilder(string)
    var lastIndex = string.lastIndex
    var startIndex = 0

    while (startIndex < lastIndex) {
        if (newString[startIndex] == '.' && newString[lastIndex].isDigit()) {
            newString[startIndex] = newString[lastIndex]
            newString[lastIndex] = '.'
            startIndex++
            lastIndex--
        } else if (newString[startIndex] != '.') {
            startIndex++
        } else if (!newString[lastIndex].isDigit()) {
            lastIndex--
        }
    }
    println(newString)
    return newString.mapNotNull(Char::digitToIntOrNull).map(Int::toLong)
}

private fun generateString(): String {
    var numOrder = 0
    return input.mapIndexed { index, s ->
        if (index % 2 == 0) {
            numOrder.toString().repeat(s).also { numOrder++ }
        } else {
            ".".repeat(s)
        }
    }.joinToString("")
}

fun main() {
    println(solvePart1())
}