package de.htwg.se.stratego.controller

import de.htwg.se.stratego.model.{CharacterList, Game, MatchField, Player}
import de.htwg.se.stratego.util.{Observable, Observer}
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {
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
        controller.matchFieldToString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  8  |     |  6  |  F  | 0\n+-----+-----+-----+-----+\n|  9  |     |     |     | 1\n+-----+-----+-----+-----+\n|  9  |     |     |     | 2\n+-----+-----+-----+-----+\n|     |  8  |  6  |  F  | 3\n+-----+-----+-----+-----+\n**********  WELCOME TO STRATEGO  **********\n\nn:   create a new empty machtfield\ni:   set all character on the matchfield\nu:   move one character up\nd:   move one character down\nr:   move one character to the right\nl:   move one character to the left\na:   attack the character next to you\no:   player one can set his characters\nt:   player two can set his characters\nq:   quit the programm\n")
        observer.updated should be(true)
      }
    }
  }
}
