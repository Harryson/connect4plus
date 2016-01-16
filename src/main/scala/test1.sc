//object L09_Implicit {
//  /* Adding ! as a method on int's */
//  def fact(n: Int): BigInt =
//    if (n == 0) 1 else fact(n - 1) * n
//
//  class Factorizer(n: Int) {
//    def ! = fact(n)
//  }
//
//  implicit def int2fact(n: Int) = new Factorizer(n)
//
//  (10!)
//}

object Imp {
  def f(i: Int) = i

  val n: Int = f("666")

}

implicit def string2Int(s: String): Int = new Integer(s)

Imp.f("3")