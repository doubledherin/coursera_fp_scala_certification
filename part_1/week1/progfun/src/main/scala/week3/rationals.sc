object rationals {
  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)
  val r = new Rational(2)
  val a = new Rational(16, 8)
  val b = new Rational(8, 8)
  a - b



  val s = x - y - z
  val t = y + y

  s < t

  t < s

  s max t
  t max s

  class Rational(x: Int, y: Int) {
    require(y !=0, "Denominator must be nonzero.")

    def this(x: Int) = this(x, 1)

    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    val numer: Int = x
    val denom: Int  = y

    def + (that: Rational) =
      new Rational(
        numer * that.denom + denom * that.numer,
        denom * that.denom)

    def unary_- = new Rational(-numer, denom)

    def - (that: Rational) = this + -that

    def < (that: Rational) = numer * that.denom < denom * that.numer

    def max(that: Rational) = if (this < that) that else this

    override def toString = numer / gcd(numer, denom) + "/" + denom / gcd(numer, denom)
  }

}

