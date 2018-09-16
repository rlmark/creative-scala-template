package ch7

import doodle.core.Image._
import doodle.core.{Angle, Color, Image}

import scala.annotation.tailrec

object RecursionExercises {

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

    @tailrec
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

    @tailrec
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

  def gradientBoxes(count: Int, fillColor: Color): Image = {
    val basicSquare = rectangle(40, 40)
    count match {
      case 0 => Image.empty
      case n => basicSquare.fillColor(fillColor) beside gradientBoxes(n -1, fillColor.spin(Angle(0.25)))
    }
  }

  def gradientBoxesTailRec(count: Int): Image = {
    val basicSquare = rectangle(40, 40)

    @tailrec
    def loop(innerCount: Int, current: Image, color : Color): Image = {
      innerCount match {
        case 0 => current
        case n =>
          loop(n - 1, current beside basicSquare.fillColor(color), color.spin(Angle(0.25)) )
      }
    }
    loop(count, Image.empty, Color.royalBlue)
  }

  val testGradient1 = gradientBoxes(10, Color.royalBlue)
  val testGradient2 = gradientBoxesTailRec(10)
}
