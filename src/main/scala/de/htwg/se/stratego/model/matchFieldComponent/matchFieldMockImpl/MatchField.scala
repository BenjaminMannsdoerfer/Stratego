
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{Colour, Field, GameCharacter, Matrix}

case class MatchField(fields: Matrix[Field]) extends MatchFieldInterface {
  override def addChar(row: Int, col: Int, char: GameCharacter, colour: Colour.FigureCol): MatchFieldInterface = this

  override def removeChar(row: Int, col: Int): MatchFieldInterface = this

  override def legend: String = ""

  override def frame(row: Int): String = ""
}