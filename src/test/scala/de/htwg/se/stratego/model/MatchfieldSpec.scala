package de.htwg.se.stratego.model

import org.scalatest.{Matchers, WordSpec}
class MatchfieldSpec extends WordSpec with Matchers {
  "A Matchfield" when {
    "no set to any value " should {
      val emptyMatchfield = Figure
      "have value 0" in {
        emptyMatchfield.Bomb
      }
    }
  }
}
