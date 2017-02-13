object session {

  def sqrt(x: Double): Double = {

    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double): Boolean =
      abs(guess * guess - x) / x < 0.001

    def abs(x: Double): Double = if (x<0) -x else x

    def improve(guess: Double): Double =
      (guess + x/guess) / 2

    sqrtIter(1.0)
  }

  def factorial(n: Int): Int = {
    def loop(acc: Int, n: Int): Int =

      if (n == 0) acc
      else loop(n * acc, n -1)

    loop(1, n)

  }
  sqrt(540)
  factorial(4)
}

