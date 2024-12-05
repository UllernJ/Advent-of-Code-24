package solutions

import utils.readInput

private val input = readInput("day2")
    .map { it.split(" ").map(String::toInt) }


fun part1(): Int {
    return input.count { isSafe(it) }
}

fun part2(): Int {
    return input.count { safeDampener(it) }
}

fun isSafe(list: List<Int>): Boolean {
    val differences = list.zipWithNext { left, right -> right - left }
    val allPositiveInRange = differences.all { it in 1..3 }
    val allNegativeInRange = differences.all { it in -3..-1 }
    return allPositiveInRange || allNegativeInRange
}

fun safeDampener(list: List<Int>): Boolean {
    for (index in list.indices) {
        val newList = list.toMutableList().apply { removeAt(index) }
        if (isSafe(newList)) {
            return true
        }
    }
    return false
}

fun main() {
    println(part1())
    println(part2())
}