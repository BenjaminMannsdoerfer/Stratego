package de.htwg.se.stratego.model.fileIoComponent

import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Game, MatchField}
import de.htwg.se.stratego.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class FileIoSpec extends WordSpec with Matchers {
  "FileIO" when {
    "a new Game created" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      val controller = new Controller(matchField)
      //game.init()

      "save and load the Gamestate with Json" in {
        import de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl.FileIO
        val fileIOJson = new FileIO()
        fileIOJson.save(matchField,controller.currentPlayerIndex)
        fileIOJson.load.toString() should be("(   0     1     2     3  \n+-----+-----+-----+-----+\n|" +
          "     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----" +
          "+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |" +
          "     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create" +
          " a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n,0)")
      }
      "save and load the Gamestate with XML" in {
        import de.htwg.se.stratego.model.fileIoComponent.fileIoXmlImpl.FileIO
        val fileIOXML = new FileIO()
        fileIOXML.save(matchField,controller.currentPlayerIndex)
        fileIOXML.load.toString() should be("(   0     1     2     3  \n+-----+-----+-----+-----+\n|" +
          "     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+----" +
          "-+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |    " +
          " |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   " +
          "create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n,0)")
      }
    }
  }
}
