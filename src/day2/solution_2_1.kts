import java.io.File

val lines: List<String> = File("input").readLines()

var depth: Long = 0
var position: Long = 0

lines.forEach { line ->
  val (command, valueStr) = line.split(" ")
  val value = valueStr.toLong()

  when(command) {
    "forward" -> {
      position += value
    }
    "down" -> {
      depth += value
    }
    "up" -> {
      depth -= value
    }
    else -> {
      throw IllegalArgumentException("unexpected input")
    }
  }

}

println("final position: $position")
println("final depth: $depth")

println("final value: ${position*depth}")
