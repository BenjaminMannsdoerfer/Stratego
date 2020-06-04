package de.htwg.se.sudoku.aview

import de.htwg.se.stratego.aview.Tui
import de.htwg.se.stratego.controller.Controller
import de.htwg.se.stratego.model.MatchField
import org.scalatest.{Matchers, WordSpec}

class TuiSpec  extends WordSpec with Matchers{

  "A Stratego Tui" should {
    val controller = new Controller(new MatchField(4,4,false))
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
      controller.moveDown(1,0) should be ()
    }
    "move a Character upwards on input 'u 3 0'" in {
      controller.initMatchfield()
      tui.processInputLine("u 3 0")
      controller.moveUp(2,0) should be ()
    }
    "move a Character to the right on input 'r 2 0'" in {
      controller.initMatchfield()
      tui.processInputLine("u 3 0")
      tui.processInputLine("r 2 0")
      controller.moveRight(2,1) should be ()
    }
    "move a Character to the left on input 'l 2 3'" in {
      controller.initMatchfield()
      tui.processInputLine("u 3 3")
      tui.processInputLine("l 2 3")
      controller.moveLeft(2,2) should be ()
    }
    "quit" in {
      tui.processInputLine("q")
    }
    "do nothing when input is bullshit" in {
      tui.processInputLine("bullshit")
    }





  }



}