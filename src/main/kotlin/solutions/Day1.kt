package solutions

import utils.readInput
import kotlin.math.abs


private val input = readInput("day1")
    .flatMap { it.split(" ").filter { it.isNotEmpty() }.map(String::toInt) }

fun part1(list1: List<Int>, list2: List<Int>) =
    (list1.sorted() zip list2.sorted()).sumOf { (v1, v2) -> abs(v1 - v2) }

fun part2(list1: List<Int>, list2: List<Int>): Int {
    var sum = 0
    list1.forEach { value ->
        sum += value * list2.count { it == value }
    }
    return sum
}

fun main() {
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()
    input.forEachIndexed { index, value ->
        if (index % 2 == 0) {
            rightList.add(value)
        } else {
            leftList.add(value)
        }
    }
    println(part1(leftList, rightList))
    println(part2(leftList, rightList))
}
