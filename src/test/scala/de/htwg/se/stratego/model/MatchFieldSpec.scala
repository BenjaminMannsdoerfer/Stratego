package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class MatchFieldSpec extends WordSpec with Matchers {

  "A MatchField" when { "created with size 4 an all fields set" should {
    val matchField = new MatchField(4, 4, true)

    "has a Matrix" in {
      matchField.fields should be(matchField.fields)
    }
    "has a nice String representation" in {
      matchField.toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
    }

  }
    "created with size 4 an all fields empty" should {
      val matchField = new MatchField(4,4,false)
      "has a Matrix"  in {
        matchField.fields should be(matchField.fields)
      }
      "has a nice String representation" in {
        matchField.toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }

    }

    "created with size 4 and a Charater" should {
      val matchField = new MatchField(4, 4, true)
      val charac = GameCharacter(Figure.Flag)
      "has been added to the Matchfield" in {
        matchField.addChar(0, 0, charac,Colour.FigureCol(0)).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  F  |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }
    "created with size 4 and a set Character" should {
      val matchField = new MatchField(4, 4, true)
      val charac = GameCharacter(Figure.Flag)
      "is again empty, because the Character has been removed" in {
        matchField.removeChar(0,0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }
  }
}