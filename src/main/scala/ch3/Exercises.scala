package ch3
import doodle.core.Image._
import doodle.core.{Image, _}
import doodle.syntax._

object Exercises {
  val image = circle(10) on circle(20) on circle(30)

  val circles = (circle(20) beside circle(20) beside circle(20)) on circle(60)

  val color = Color.hsl(Angle(0), 0.8.normalized, 0.6.normalized)

  val eye = (circle(10) fillColor color) on
    (circle(20) fillColor Color.cornflowerBlue) on
    (circle(30) fillColor Color.white) on
    (circle(50) fillColor Color.darkBlue)


  val target = circle(20) fillColor Color.red on
    circle (40) fillColor Color.white on
    circle(60) fillColor Color.red

  val complimentaryTriangles: Image = {
    val startingColor = Color.lightCoral
    val triangleShape = triangle(40,60)
    triangleShape fillColor startingColor above
      (
        triangleShape fillColor startingColor.spin(10.degrees) beside
          triangleShape fillColor startingColor.spin(20.degrees)
        )
  }

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

}
