import java.io.File

val lines: List<String> = File("input").readLines()

val counter = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

lines.forEach { code ->
  for (i in code.indices) {
    val bit = Character.getNumericValue(code[i])
    counter[i] += bit
  }
}

var gamma = ""
var epsilon = ""

counter.forEach {
  val mid = lines.size/2
  if (it>mid) {
    gamma += "1"
    epsilon += "0"
  } else {
    gamma += "0"
    epsilon += "1"
  }
}

println(epsilon.toInt(2)*gamma.toInt(2))
