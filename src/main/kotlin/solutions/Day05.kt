package solutions

import utils.readInput

private val input = readInput("day5").map { it.split(",") }
private val puzzle = input.filter { it.size > 2 }.map { it.map(String::toInt) }
private val map = mutableMapOf<Int, MutableList<Int>>()
private fun part1(): Int {
    return calculateQueue()
}

private fun calculateQueue(): Int {
    populateRuleset()
    val validList = puzzle.filter { containsIllegalNumber(it) }
    return validList.sumOf {
        it[it.size / 2]
    }
}

private fun containsIllegalNumber(list: List<Int>): Boolean {
    return list.drop(1)
        .zipWithNext()
        .all { (val1, val2) -> map[val1]?.contains(val2) == true }
}

private fun populateRuleset() {
    val list = input.filter { it.size == 1 }.flatten()
    list.forEach {
        it.split("|").map(String::toInt).zipWithNext { val1, val2 ->
            if (map.containsKey(val1)) {
                map[val1]?.add(val2)
            } else {
                map[val1] = mutableListOf(val2)
            }
        }
    }
}

fun main() {
    println(part1())
}