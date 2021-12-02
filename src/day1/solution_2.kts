import java.io.File

val lines: List<String> = File("input").readLines()

var prevWindow = Double.MAX_VALUE

var increaseInMeasurementCount = 0

for (i in 0..lines.size - 3) {
  val currentWindow = lines[i].toDouble() + lines[i+1].toDouble() + lines[i+2].toDouble()
  if (currentWindow > prevWindow) {
    increaseInMeasurementCount++
  }
  prevWindow = currentWindow
}

println(increaseInMeasurementCount)
