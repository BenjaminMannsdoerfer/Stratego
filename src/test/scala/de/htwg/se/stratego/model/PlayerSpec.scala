package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("PlayerName", new CharacterList(4).getCharacterList())
    "has a name"  in {
      player.name should be("PlayerName")
    }
    "has a CharacterList" in {
      player.characterList should be(Seq(Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Captain),
        Character(Figure.Flag)))
    }
    "has a nice String representation" in {
      player.toString should be("PlayerName")
    }
  }}
}
