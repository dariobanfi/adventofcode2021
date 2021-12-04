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

fun findLastWinningMatrix(
  numbers: List<Int>,
  matrices: MutableList<MutableList<MutableList<Pair<Int, Boolean>>>>
): Pair<MutableList<MutableList<Pair<Int, Boolean>>>, Int> {
  val boardsWhichWon = hashMapOf<Int, Boolean>()
  for (i in 0 until matrices.size) {
    boardsWhichWon[i] = false
  }

  for (number in numbers) {
    // select number
    println("extracting $number boardsWhichWon $boardsWhichWon")
    for (matrix in matrices) {
      insertNumber(number, matrix)
    }
    // doublecheck if there's a winner
    for (i in 0 until matrices.size) {
      if (isBoardWinning(matrices[i])) {
        if (isLastWinner(boardsWhichWon, i)) {
          return Pair(matrices[i], number)
        } else {
          boardsWhichWon[i] = true
        }
      }
    }
  }
  return Pair(mutableListOf(), 0)
}

val winningCombo = findLastWinningMatrix(numbers, matrices)

var sumOfUnmarkedNumbers = 0
for (i in 0 until winningCombo.first.size) {
  for (j in 0 until winningCombo.first.size) {
    if (!winningCombo.first[i][j].second) {
      sumOfUnmarkedNumbers += winningCombo.first[i][j].first
    }
  }
}

println("Solution: ${sumOfUnmarkedNumbers * winningCombo.second}")



fun isLastWinner(boardsWhichWon: HashMap<Int, Boolean>, i: Int): Boolean {
  for (boardEntry in boardsWhichWon.entries) {
    if (boardEntry.key == i ) {
      if (boardEntry.value) {
        return false
      }
    } else {
      if (!boardEntry.value) {
        return false
      }
    }
  }

  return true;
}
