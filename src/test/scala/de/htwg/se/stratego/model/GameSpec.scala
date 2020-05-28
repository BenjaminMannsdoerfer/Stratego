package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class GameSpec extends WordSpec with Matchers {
  "A Game" when { "created with two Players and a empty Matchfield" should {
    val matchField = new MatchField(4,4,true)
    val characList = CharacterList(4)
    val playerBlue = Player("PlayerBlue", characList.getCharacterList())
    val playerRed = Player("PlayerRed", characList.getCharacterList())
    val game = Game(playerBlue, playerRed, 4, matchField)
    "fill the Matchfield with Characters" in {
      game.init().toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n")
    }
  }
    "gets a matchfield with a blueList" should {
      val matchField = new MatchField(4, 4, true)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      "filled with characters" in {
        game.aChar(matchField, 0, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n")
      }
    }
      "gets a matchfield with a redList" should {
        val matchField = new MatchField(4, 4, true)
        val characList = CharacterList(4)
        val playerBlue = Player("PlayerBlue", characList.getCharacterList())
        val playerRed = Player("PlayerRed", characList.getCharacterList())
        val game = Game(playerBlue, playerRed, 4, matchField)
        "filled with characters" in {
          game.bChar(matchField, 1, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  8  |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n")
        }
      }

    /*"gets a matchfield with a red and a blue List each with filled characters" should {
      val matchField = new MatchField(4, 4, true)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      game.matchField.addChar(0,0,GameCharacter(Figure.Bomb))
      "and now one character move down" in {

        game.moveDown(matchField,0,0).toString should be()
      }
    }*/
  }
}
