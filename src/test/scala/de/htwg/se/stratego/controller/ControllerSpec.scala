package de.htwg.se.stratego.controller

import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Game, MatchField}
import de.htwg.se.stratego.model.playerComponent.Player
import de.htwg.se.stratego.util.{Observable, Observer}
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {
/*
  "A Controller" when {
    "observed by an Observer" should {
      val matchField = new MatchField(4, 4, false)
      val controller = new Controller(matchField)
      val characterList = new CharacterList(4)
      val playerBlue = new Player("PlayerBlue",characterList.getCharacterList())
      val playerRed = new Player("PlayerRed", characterList.getCharacterList())
      val game = new Game(playerBlue,playerRed,4,matchField)
      val observer = new Observer {
        var updated: Boolean = updated

        def isUpdated: Boolean = updated

        override def update(): Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after an empty matchfield" in {
        controller.createEmptyMatchfield(4)
        observer.updated should be(true)
        controller.matchField.fields.matrixSize should be(4)
      }
      "notify its Observer after an set matchfield" in {
        controller.initMatchfield()
        observer.updated should be(true)
      }

      "notify its Observer after print matchfield" in {
        controller.matchFieldToString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  9  |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        observer.updated should be(true)
      }
      "welcome" in {
        controller.welcome() should be("Welcome to STRATEGO! Please enter first name of Player1 and then of Player2 like (player1 player2)!")
      }
      "set Players" in {
        controller.setPlayers("p1 p2") should be("Hello p1 and p2!\nSet your figures automatically with (i) or manually with (s row col figure)\nPlayer p1 it's your turn!")
      }
      "attack a figure" in {
        controller.initMatchfield()
        controller.attack(0, 0, 0, 3) should be("Congratulations p1! You're the winner!\nGame finished! Play new Game with (n)!")
        controller.attack(0,0,0,1) should be ("p2 it's your turn!")
      }
      "set a figure" in {
        controller.set(0, 0, "F") should be("p2 it's your turn!")
        controller.set(0, 1, "6") should be("p2 it's your turn!")
        controller.set(0, 2, "8") should be("p2 it's your turn!")
        controller.set(0, 3, "9") should be("p2 it's your turn!")
        controller.set(3,0,"F") should be("p2 it's your turn!")
        controller.currentPlayerIndex=1
        controller.set(3,1,"9") should be("p2 it's your turn!")
        controller.set(3,2,"8") should be("p2 it's your turn!")
        controller.set(3,3,"6") should be("p2 it's your turn!")

      }
      "move a figure" in {
        controller.initMatchfield()
        controller.move('d',0,0) should be ("p1 it's your turn!")
      }
      "undo" in {
        controller.undo should be ("undo")
        controller.move('u', 3, 0) should be("p2 it's your turn!")
      }
      "redo" in {
        controller.redo should be ("redo")
      }


    }
  }

*/
}
