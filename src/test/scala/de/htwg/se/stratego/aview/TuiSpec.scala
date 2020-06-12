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

    "attack a Characters on input 'a 0 0 0 1" in {
      controller.initMatchfield()
      tui.processInputLine("a 0 0 0 1")
      controller.attack(0, 1, 0, 2) should be()
    }
    "quit" in {
      tui.processInputLine("q")
    }
    "do nothing when input is bullshit" in {
      tui.processInputLine("bullshit")
    }





  }



}