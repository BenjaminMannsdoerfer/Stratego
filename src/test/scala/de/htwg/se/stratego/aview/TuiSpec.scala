package de.htwg.se.stratego.aview

import de.htwg.se.stratego.aview.Tui
import de.htwg.se.stratego.controller.Controller
import de.htwg.se.stratego.model.MatchField
import org.scalatest.{Matchers, WordSpec}

class TuiSpec  extends WordSpec with Matchers{

  "A Stratego Tui" should {
    val controller = new Controller(new MatchField(4, 4, false))
    val tui = new Tui(controller)

    "create and empty Matchfield on input 'n'" in {
      tui.processInputLine("n")
      controller.matchField should be(new MatchField(4, 4, false))
    }
    "set all Characters on Matchfield" in {
      tui.processInputLine("i")
      controller.initMatchfield() should be()
    }

    "move a Character downwards on input 'd 0 0'" in {
      controller.initMatchfield()
      tui.processInputLine("d 0 0")
      controller.moveDown(0, 1) should be()
    }
    "move a Character upwards on input 'u 3 0'" in {
      controller.initMatchfield()
      tui.processInputLine("u 3 0")
      controller.moveUp(3, 1) should be()
    }
    "move a Character to the right" in {
      controller.initMatchfield()
      controller.moveRight(0, 0) should be()
    }
    "move a Character to the left" in {
      controller.initMatchfield()
      controller.moveLeft(0, 3) should be()
    }
    "attack a Characters on input 'a 0 0 0 1" in {
      controller.initMatchfield()
      tui.processInputLine("a 0 0 0 1")
      controller.attack(0, 1, 0, 2) should be()
    }
    "set a Character of playerA on input ''" in {
      tui.processInputLine("o 0 0 F")
      controller.setBlueField(0, 1, "9")
    }
    "set a Character of playerB on input ''" in {
      tui.processInputLine("t 0 0 F")
      controller.setRedField(0, 1, "9")
    }
    "quit" in {
      tui.processInputLine("q")
    }
    "do nothing when input is bullshit" in {
      tui.processInputLine("bullshit")
    }





  }



}