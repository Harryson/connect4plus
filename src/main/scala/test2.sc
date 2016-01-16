object AcronymExample {
  implicit def string2ExtendedString(str: String) = new ExtendedString(str)

  class ExtendedString(str: String) {
    def makeAcronym = str.toCharArray.foldLeft("") { (t, c) =>
      t + (if (c.isUpper) c.toString else "")
    }
  }

  println(acronym)
  val acronym = " HyperText Transfer Protocol".makeAcronym
}

AcronymExample.string2ExtendedString("TesT").makeAcronym


var char: Char = 't'
char.toUpper

var text = "test"
def myfunc = text.toCharArray.foldLeft("") { (t, c) =>
  t + c.toUpper.toString
}

object Test {

  str2MyRichString("TesT").reduce

  class MyRichString(str: String) {
    def reduce = str.toCharArray.foldLeft("") { (t, c) =>
      t + (if (c.isUpper) c.toString else "")
    }
  }

  implicit def str2MyRichString(str: String) = new MyRichString(str)
}

implicit def string2Int(s: String): Int = new Integer(s)

val zahl = "9" - 5
val zahl1 = "10" % 3

class Test1 {

  class Drop(str: String) {
    implicit def string2Int(s: String): Int = new Integer(s)

    def drop = {
      val column: Int = new Integer(str.substring(str.indexOf("at") + 3, str.length));
      println("drop at: " + column)
    }

    //    val result = "Drop coin at 3".drop
  }

  implicit def sentenceToInt(str: String) = new Drop(str)

  val result = "Drop coin at 3".drop
}