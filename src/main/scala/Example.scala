import doodle.core.Image._
import doodle.core.{Image, _}
import doodle.syntax._

// To use this example, open the SBT console and type:
//
// Example.image.draw
object Example {
  val image = circle(10) on circle(20) on circle(30)

  val spunCircles =
    (circle(100) fillColor Color.red) beside
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

}
