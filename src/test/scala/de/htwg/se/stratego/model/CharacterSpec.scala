package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class CharacterSpec extends WordSpec with Matchers {
  "A Character" when { "new" should {
    val character = Character(Figure.Bomb)
    "has a figure"  in {
      character.figure should be(Figure.Bomb)
    }
    "has a nice String representation" in {
      character.toString should be("B")
    }
  }}
}
