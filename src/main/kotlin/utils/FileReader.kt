package utils

import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String) = Path("src/main/kotlin/text_files/$name.txt").readText().trim().lines()
