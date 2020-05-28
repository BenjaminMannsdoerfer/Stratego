package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class GameSpec extends WordSpec with Matchers {
  "A Game" when { "created with two Players and a empty Matchfield" should {
    val matchField = new MatchField(4,4,true)
    val characList = new CharacterList(4)
    val playerBlue = new Player("PlayerBlue", characList.getCharacterList())
    val playerRed = new  Player("PlayerRed", characList.getCharacterList())
    val game = new Game(playerBlue, playerRed, 4, matchField)
    "fill the Matchfield with Characters" in {
      game.init() should be()
    }
  }

  }
}
