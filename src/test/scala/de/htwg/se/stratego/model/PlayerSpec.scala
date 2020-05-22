package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("PlayerName", new CharacterList(4).getCharacterList())
    "has a name"  in {
      player.name should be("PlayerName")
    }
    "has a CharacterList" in {
      player.characterList should be(Seq(GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Flag)))
    }
    "has a nice String representation" in {
      player.toString should be("PlayerName")
    }
  }}
}
