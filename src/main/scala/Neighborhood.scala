
import doodle.core.{Color, Image}
import doodle.core.Image._
object Neighborhood {
  val roadSegment = (rectangle(150, 10) fillColor Color.yellow beside rectangle(50, 10) fillColor Color.black) above
  rectangle(200, 40) fillColor Color.black

  def repeatImageBeside(int: Int, image: Image): Image = {
    if(int == 0 ) Image.empty else image beside repeatImageBeside(int - 1, image)
  }
  val road = repeatImageBeside(5, roadSegment)

}
