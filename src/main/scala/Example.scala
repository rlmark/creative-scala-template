import doodle.core.Image._
import doodle.core.{Image, _}
import doodle.syntax._

// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {
  val image = circle(10) on circle(20) on circle(30)

  val circles = (circle(20) beside (circle(20)) beside circle(20)) on circle(60)

  val color = Color.hsl(Angle(0), 0.8.normalized, 0.6.normalized)

  val eye = (circle(10) fillColor color) on
    (circle(20) fillColor Color.cornflowerBlue) on
    (circle(30) fillColor Color.white) on
    (circle(50) fillColor Color.darkBlue)

  val spunCircles = (circle(100) fillColor Color.red) beside
    (circle(100) fillColor Color.red.spin(15.degrees)) beside
    (circle(100) fillColor Color.red.spin(30.degrees))

  def spinCircles(iterations: Int): Image = {
    val endingColor = Color.green
    if (iterations <= 0) {
      circle(20).fillColor(endingColor)
    } else {
      val newCircle = circle(20) fillColor endingColor.spin((iterations * 10).degrees)
      newCircle beside spinCircles(iterations - 1)
    }
  }

  val complimentaryTriangles: Image = {
    val startingColor = Color.lightCoral
    val triangleShape = triangle(40,60)
    triangleShape fillColor startingColor above
      (
        triangleShape fillColor startingColor.spin(10.degrees) beside
        triangleShape fillColor startingColor.spin(20.degrees)
      )
  }

  val target = circle(20) fillColor Color.red on
    circle (40) fillColor Color.white on
    circle(60) fillColor Color.red


  // Note that color continues to color all shapes following that do not have a color in order
  // of EXECUTION not of order on the page
  val targetPicture =
  target above
  rectangle(20, 80) above
  rectangle(40, 20) fillColor Color.brown above
  rectangle(90, 39) fillColor Color.green

  val targetPicture2 =
    rectangle(90, 39) fillColor Color.green below
    rectangle(40, 20) fillColor Color.brown below
    rectangle(20, 80) below
    target

  def cross(int: Int): Image = {
    val smallCircle = circle(20)
    def loop(current: Image, i: Int): Image = {
      i match {
        case 0 => current
        case n =>
          val xAxis = smallCircle beside current beside smallCircle
          val cross = smallCircle above xAxis above smallCircle
          loop(cross, n - 1)
      }
    }
    loop(smallCircle, int)
  }
  val testCross = cross(3)

  def repeatImageBeside(int: Int, image: Image): Image = {
    if(int == 0 ) Image.empty else image beside repeatImageBeside(int - 1, image)
  }
  def repeatImageBelow(int: Int, image: Image): Image = {
    if(int == 0 ) Image.empty else image below repeatImageBelow(int - 1, image)
  }

  // 2 loops LOL READ THE BOOK THIS IS SOLVING THE WRONG PROBLEM REBECCA!
  def chessboard1(count: Int): Image = {
    val red = rectangle(10, 10) fillColor Color.red
    val black = rectangle(10, 10) fillColor Color.black
    val singleSquareUnit = (red beside black) above (black beside red)

    val row = repeatImageBeside(count, singleSquareUnit)
    repeatImageBelow(count, row)
  }

    val testChess1 = chessboard1(3)

  def chessboard(count: Int): Image = {
    val red = rectangle(10, 10) fillColor Color.red
    val black = rectangle(10, 10) fillColor Color.black
    val singleSquareUnit = (red beside black) above (black beside red)

    count match {
      case 0 => singleSquareUnit
      case n =>
        val unit = chessboard(n - 1)
        (unit beside unit) above (unit beside unit)
    }
  }

  def chessboardTailRec(count: Int): Image = {
    val red = rectangle(10, 10) fillColor Color.red
    val black = rectangle(10, 10) fillColor Color.black
    val singleSquareUnit = (red beside black) above (black beside red)

    def loop(innerCount: Int, current: Image): Image = {
      innerCount match {
        case 0 => current
        case n =>
          loop(n - 1, (current beside current) above (current beside current))
      }
    }
    loop(count, singleSquareUnit)
  }

  val testChess2 = chessboard(3)
  val testChessTailrec = chessboardTailRec(3)

  def sierpinski(count: Int): Image = {
    val basicTriangle = triangle(20, 30)
    count match {
      case 0 => basicTriangle
      case n => val unit = sierpinski(n - 1)
        unit above ( unit beside unit)
    }
  }

  def sierpinskiTailRec(count: Int): Image = {
    val basicTriangle = triangle(20, 30)

    def loop(innerCount: Int, current: Image): Image = {
      innerCount match {
        case 0 => current
        case n => loop(n - 1, current above (current beside current))
      }
    }
    loop(count, basicTriangle)
  }

  val testSierpinski1 = sierpinski(4)
  val testSierpinski2 = sierpinskiTailRec(4)


}
