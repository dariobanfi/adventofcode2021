import java.io.File

val lines: List<String> = File("input").readLines()

var prevMeasurement = lines[0].toLong()

var increaseInMeasurementCount = 0

lines.forEach { line ->
  val measurement = line.toLong()
  if (measurement > prevMeasurement) {
    increaseInMeasurementCount++
  }
  prevMeasurement = measurement
}

println(increaseInMeasurementCount)
