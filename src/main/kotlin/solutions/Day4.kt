package solutions

import utils.readInput

private val input = readInput("day4").map { values ->
    values.split("")
        .filter { it.isNotEmpty() }
}
private const val XMAS = "XMAS"

private fun part1(): Int {
    val values = extractString(input)
    return countXmas(values)
}

private fun countXmas(list: List<String>): Int {
    var count = 0
    val regex = Regex("XMAS")
    list.forEach {
        count += regex.findAll(it).count()
        count += regex.findAll(it.reversed()).count()
    }
    return count
}

fun main() {
    println(part1())
}


private fun extractString(list: List<List<String>>): List<String> {
    val listOfVals = mutableListOf<String>()
    var stringValue = ""
    val maxRows = list.size

    for (i in list.indices) {
        stringValue = ""
        for (n in list[i].indices) {
            stringValue += list[i][n]
        }
        listOfVals.add(stringValue)
    }

    val maxColumns = if (list.isNotEmpty()) list.first().size else 0
    for (colIndex in 0 until maxColumns) {
        stringValue = ""
        for (rowIndex in list.indices) {
            if (colIndex < list[rowIndex].size) {
                stringValue += list[rowIndex][colIndex]
            }
        }
        listOfVals.add(stringValue)
    }

    for (startCol in 0 until maxColumns) {
        stringValue = ""
        var rowIndex = 0
        var colIndex = startCol

        while (rowIndex < maxRows && colIndex < list[rowIndex].size) {
            stringValue += list[rowIndex][colIndex]
            rowIndex++
            colIndex++
        }
        if (stringValue.length > 3) {
            listOfVals.add(stringValue)
        }
    }
    for (startRow in 1 until maxRows) {
        stringValue = ""
        var rowIndex = startRow
        var colIndex = 0

        while (rowIndex < maxRows && colIndex < list[rowIndex].size) {
            stringValue += list[rowIndex][colIndex]
            rowIndex++
            colIndex++
        }
        if (stringValue.length > 3) {
            listOfVals.add(stringValue)
        }
    }

    for (startCol in maxColumns - 1 downTo 0) {
        stringValue = ""
        var rowIndex = 0
        var colIndex = startCol

        while (rowIndex < maxRows && colIndex >= 0) {
            stringValue += list[rowIndex][colIndex]
            rowIndex++
            colIndex--
        }
        if (stringValue.length > 3) {
            listOfVals.add(stringValue)
        }
    }
    for (startRow in 1 until maxRows) {
        stringValue = ""
        var rowIndex = startRow
        var colIndex = maxColumns - 1

        while (rowIndex < maxRows && colIndex >= 0) {
            stringValue += list[rowIndex][colIndex]
            rowIndex++
            colIndex--
        }
        if (stringValue.length > 3) {
            listOfVals.add(stringValue)
        }
    }

    return listOfVals.toList()
}
