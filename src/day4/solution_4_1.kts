import java.io.File

val lines: List<String> = File("input").readLines()

// too lazy to use classes lol
val matrices = mutableListOf<MutableList<MutableList<Pair<Int, Boolean>>>>()

val numbers = lines[0].split(',').map { it.toInt() }

var tmpMatrix = mutableListOf<MutableList<Pair<Int, Boolean>>>()
for (i in 2 until lines.size) {
  var matrixNumbers = lines[i].replace("  ", " ").trim().split(" ")
  if (lines[i].isBlank()) {
    matrices.add(tmpMatrix)
    tmpMatrix = mutableListOf()
  } else {
    val transformedNumbers = matrixNumbers.map { Pair(it.toInt(), false) }.toMutableList()
    tmpMatrix.add(transformedNumbers)
  }
}

fun isBoardWinning(board: MutableList<MutableList<Pair<Int, Boolean>>>): Boolean {
  // Check rows
  for (line in board) {
    var isWinning = true
    for (value in line) {
      if (!value.second) {
        isWinning = false
      }
    }
    if (isWinning) {
      return true
    }
  }

  // Check cols
  for (i in 0 until board.size) {
    var isWinning = true
    for (j in 0 until board.size) {
      if (!board[j][i].second) {
        isWinning = false
      }
    }
    if (isWinning) {
      return true
    }
  }
  return false
}

fun insertNumber(number: Int, board: MutableList<MutableList<Pair<Int, Boolean>>>) {
  for (i in 0 until board.size) {
    for (j in 0 until board.size) {
      if (board[i][j].first == number) {
        board[i][j] = Pair(board[i][j].first, true)
      }
    }
  }
}

fun findWinningMatrix(
  numbers: List<Int>,
  matrices: MutableList<MutableList<MutableList<Pair<Int, Boolean>>>>
): Pair<MutableList<MutableList<Pair<Int, Boolean>>>, Int> {
  for (number in numbers) {
    // select number
    for (matrix in matrices) {
      insertNumber(number, matrix)
    }
    // doublecheck if there's a winner
    for (matrix in matrices) {
      if (isBoardWinning(matrix)) {
        return Pair(matrix, number)
      }
    }
  }
  return Pair(mutableListOf(), 0)
}

val winningCombo = findWinningMatrix(numbers, matrices)

var sumOfUnmarkedNumbers = 0
for (i in 0 until winningCombo.first.size) {
  for (j in 0 until winningCombo.first.size) {
    if (!winningCombo.first[i][j].second) {
      sumOfUnmarkedNumbers += winningCombo.first[i][j].first
    }
  }
}

println("Solution: ${sumOfUnmarkedNumbers * winningCombo.second}")



