package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class MatchFieldSpec extends WordSpec with Matchers {

  "A MatchField" when { "created with size 4 an all fields set" should {
    val matchField = new MatchField(4, 4, true)
    "has a Matrix" in {
      matchField.fields should be(matchField.fields)
    }
    "has a nice String representation" in {
      matchField.toString should be("+-----+-----+-----+-----+\n|  X  |  X  |  X  |  X  |\n+-----+-----+-----+-----+\n|  X  |  X  |  X  |  X  |\n+-----+-----+-----+-----+\n|  X  |  X  |  X  |  X  |\n+-----+-----+-----+-----+\n|  X  |  X  |  X  |  X  |\n+-----+-----+-----+-----+\n")
    }

  }
    "created with size 4 an all fields empty" should {
      val matchField = new MatchField(4,4,false)
      "has a Matrix"  in {
        matchField.fields should be(matchField.fields)
      }
      "has a nice String representation" in {
        matchField.toString should be("+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n|     |     |     |     |\n+-----+-----+-----+-----+\n")
      }

    }}
}