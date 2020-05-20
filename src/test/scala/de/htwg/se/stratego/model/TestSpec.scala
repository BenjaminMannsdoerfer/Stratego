package de.htwg.se.stratego.model

import org.scalatest.{Matchers, WordSpec}
class TestSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Test("Your Name")
    "have a name"  in {
      player.name should be("Your Name")
    }
    "have a nice String representation" in {
      player.toString should be("Your Name")
    }
  }}
}
