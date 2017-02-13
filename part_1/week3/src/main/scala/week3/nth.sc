package src.main.scala.week3

/**
  * Created by wendydherin on 2/4/17.
  */
object nth {

  def nth[T](n: Int, xs: List[T]): T =
    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) xs.head
    else nth(n-1, xs.tail)

  def main(args: Array[String]) =
    val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
    println(list)
    nth(1, list)

}
