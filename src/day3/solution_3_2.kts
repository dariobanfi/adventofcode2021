import java.io.File

val lines: List<String> = File("input").readLines()

enum class Type {
  OXIGEN,
  CO2
}

fun getSolution(inputList: List<String>, type: Type): String {
  var solutionList = inputList.toMutableList()
  var counter = 0
  while(solutionList.size > 1) {
    var countOfZeroes = 0
    var countOfOnes  = 0
    solutionList.forEach { line ->
      if (line[counter].digitToInt() == 0) countOfZeroes++ else countOfOnes++
    }

    val bitToTakeNext = if (type == Type.OXIGEN) {
      if (countOfOnes >= countOfZeroes) '1' else '0'
    } else {
      if (countOfZeroes <= countOfOnes) '0' else '1'
    }

    val filteredList = solutionList.filter { it[counter] == bitToTakeNext }.toMutableList()
    counter++
    solutionList = filteredList
  }
  return solutionList.first()
}
val oxigen = getSolution(lines, Type.OXIGEN)
val co2 = getSolution(lines, Type.CO2)

println(oxigen.toInt(2)*co2.toInt(2))
