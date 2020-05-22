package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {
  "A Field" when { "new" should {
    val field = Field(true)
    "can be set"  in {
      field.isSet should be(true)
    }

    "has a nice String representation" in {
      field.toString should be("true")
    }
  }}
    "A Field" when { "new" should {
      val field = Field(false)
      "is not set"  in {
        field.isSet should be(false)
      }
  }}
}
