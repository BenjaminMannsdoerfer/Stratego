package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class GameSpec extends WordSpec with Matchers {
  "A Game" when {
    "created with two Players and a empty Matchfield" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      "fill the Matchfield with Characters" in {
        game.init().toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }
    "figure set" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      var board = game.aChar(matchField, 3, 2, 0)
      "out of bounds -> catch it" in{
        game.outOfBounds(matchField, 0, 3) should be("The Figure can not set out of bounds\n!   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
      "not out of bound -> don't catch it" in{
        game.outOfBounds(board,2,0) should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |  F  |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }
    "gets a matchfield with a blueList" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      "filled with characters" in {
        game.aChar(matchField, 0, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }
    "gets a matchfield with a redList" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      "filled with characters" in {
        game.bChar(matchField, 1, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  8  |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      val board = game.aChar(matchField, 0, 0, 0)
      "and now one character moves down" in {
        game.moveDown(board, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|  9  |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      val board = game.aChar(matchField, 1, 3, 0)
      "and now one character moves up" in {
        game.moveUp(board, 3, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|  8  |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      val board = game.aChar(matchField, 2, 0, 1)
      "and now one character moves to the left" in {
        game.moveLeft(board, 0, 1).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  6  |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }
    "gets a matchfield with a red and a blue List each with filled characters" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      val board = game.aChar(matchField, 3, 0, 0)
      "and now one character moves to the right" in {
        game.moveRight(board, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |  F  |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      var board = game.aChar(matchField, 3, 3, 3)
      //board = game.aChar(matchField, 3, 3, 3)
      "and now one character moves to the right out of bounds" in {
        //game.moveRight(board,3,2).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |  6  |  F  | 3\n+-----+-----+-----+-----+\n")
        game.moveRight(board, 3, 3).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |  F  | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }
    "gets a matchfield with a red and a blue List each with filled characters" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      val board = game.aChar(matchField, 2, 3, 2)
      val board1 = game.aChar(board, 3, 3, 3)
      "and now one character moves to the right out on another character" in {
        game.moveRight(board1, 3, 2).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\nq:   quit the programm\n")
      }
    }
  }
}
