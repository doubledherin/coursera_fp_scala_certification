object exercise2 {
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int = {
      if (a > b) 0
      else f(a) + sumF(a+1, b)
    }
    sumF
  }

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a+1, b)
  }
  def square(x: Int): Int = x * x
  def cube(x: Int): Int = x * x * x
  def factorial(n: Int): Int = {
    product(x => x)(1, n)
  }

  factorial(4)
  sum(square)(2, 5)
  product(cube)(2, 6)

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, base: Int)(a: Int, b: Int): Int = {
    if (a > b) base else combine(f(a), mapReduce(f, combine, base)(a+1, b))
  }
}