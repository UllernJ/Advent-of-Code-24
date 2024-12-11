package solutions

import utils.readInput


private val maze = readInput("day6").map { it.split("").toMutableList() }
private const val OBSTACLE = "#"
private fun part1() {
    println(navigateMaze())
}

private fun navigateMaze(): Int {
    val (x, y) = getStartPosition(maze)
    val startSymbol = maze[x][y]
    when (val rotation = getRotation(startSymbol)) {
        "UP" -> navigateUp(x, y)
        "RIGHT" -> navigateRight(x, y)
        "DOWN" -> navigateDown(x, y)
        "LEFT" -> navigateLeft(x, y)
        else -> error("Invalid rotation: $rotation")
    }
    return maze.sumOf { level ->
        level.count { it == "X" }
    }
}

private fun navigateUp(x: Int, y: Int) {
    for (i in x downTo 0) {
        if (maze[i][y] == OBSTACLE) {
            return navigateRight(i + 1, y)
        } else {
            maze[i][y] = "X"
        }
        if (i == 0) {
            return
        }
    }
}


private fun navigateRight(x: Int, y: Int) {
    for (i in y until maze[x].size) {
        if (maze[x][i] == OBSTACLE) {
            return navigateDown(x, i - 1)
        } else {
            maze[x][i] = "X"
        }
        if (i == maze[x].size - 1) {
            return
        }
    }
}

private fun navigateDown(x: Int, y: Int) {
    for (i in x until maze.size) {
        if (maze[i][y] == OBSTACLE) {
            return navigateLeft(i - 1, y)
        } else {
            maze[i][y] = "X"
        }
        if (i == maze.size - 1) {
            return
        }
    }
}


private fun navigateLeft(x: Int, y: Int) {
    for (i in y downTo 0) {
        if (maze[x][i] == OBSTACLE) {
            return navigateUp(x, i + 1)
        } else {
            maze[x][i] = "X"
        }
        if (i == 0) {
            return
        }
    }
}


private fun getRotation(rotationSymbol: String): String? {
    return when (rotationSymbol) {
        "^" -> "UP"
        ">" -> "RIGHT"
        "v" -> "DOWN"
        "<" -> "LEFT"
        else -> null
    }
}

private fun getStartPosition(list: List<List<String>>): Pair<Int, Int> {
    list.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, value ->
            if (getRotation(value) != null) {
                return Pair(rowIndex, columnIndex)
            }
        }
    }
    return Pair(0, 0)
}

fun main() {
    part1()
}