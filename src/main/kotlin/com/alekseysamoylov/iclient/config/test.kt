// Kotlin

fun main(args: Array<String>) {
  for (i in 1 .. 100) {
    if (multipliesOfThree(i) && multipliesOfFive(i)) {
      println("$fizz$buzz")
    } else if (multipliesOfThree(i)) {
      println(fizz)
    } else if (multipliesOfFive(i)) {
      println(buzz)
    } else {
      println(i)
    }
  }
}

private fun multipliesOfThree(i: Int): Boolean {
  return i % 3 == 0
}
private fun multipliesOfFive(i: Int): Boolean {
  return i % 5 == 0
}

const val fizz = "Fizz"
const val buzz = "Buzz"
