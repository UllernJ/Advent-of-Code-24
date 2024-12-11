package solutions

import utils.readInput

private val input = readInput("day11").flatMap {it.split(" ")}.map(String::toLong)

private fun part1() {
    val list = manipulateSpaceTime(input, 25)
    println("Number of stones: ${list.size}")
}

private fun manipulateSpaceTime(input: List<Long>, iterations: Int): List<Long> {
    if (iterations == 0) {
        return input
    }
    val newList = mutableListOf<Long>()
    input.forEach { value ->
        val valueStr = value.toString()
        if (value == 0.toLong()) {
            newList.add(1)
        } else if (valueStr.length % 2 == 0) {
            val mid = valueStr.length / 2
            val leftSide = valueStr.substring(0, mid)
            val rightSide = valueStr.substring(mid)
            newList.add(leftSide.toLong())
            newList.add(rightSide.toLong())
        } else {
            newList.add(value * 2024)
        }
    }
    return manipulateSpaceTime(newList, iterations - 1)
}


fun main() {
    part1()
}