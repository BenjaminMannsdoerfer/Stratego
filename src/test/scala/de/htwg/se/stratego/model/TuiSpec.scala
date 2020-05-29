package de.htwg.se.stratego.aview
import de.htwg.se.stratego.model.MatchField
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {

  "A Tui" when { "new" should {
    val tui = new Tui
    val board = new  MatchField(4,4,false)
    "can show the legend"  in {
      //tui.legend() should be("**********  WELCOME TO STRATEGO  **********\n\nn:   create a new machtfield of youre specified size \ng:   start the Game\np:   print the matchfield\nh:   show the helpdesk\nq:   quit the programm\n")
    }
    "can execute different stuff depending on the input" in {
      tui.processInputLine("h", board) should be(board)
      tui.processInputLine("x", board) should be(board)
      tui.processInputLine("q", board) should be(board)
      tui.processInputLine("p", board) should be(board)
      //tui.processInputLine("n", board) should be(board)
    }
    "can start a game" in {
      //tui.game(board) should be(board)
    }


  }}


}
