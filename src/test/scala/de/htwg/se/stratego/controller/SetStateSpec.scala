package de.htwg.se.stratego.controller

import de.htwg.se.stratego.model.{CharacterList, Game, MatchField, Player}
import de.htwg.se.stratego.util.{Observable, Observer}
import org.scalatest.{Matchers, WordSpec}

class SetStateSpec extends WordSpec with Matchers {
  "A SetStateSpec" when {
    val matchField = new MatchField(4, 4, false)
    val controller = new Controller(matchField)
    controller.initMatchfield()
    val state = SetState(controller)
    "created" should {
      "handle" in {
        state.handle("i") should be("matchfield initialized\nMove Figures with (m direction[u,d,r,l] row col) or attack with (a row col row col)\nPlayerBlue it's your turn!")
        state.handle("s") should be("initialize matchfield with (i) or set a figure with (s row col figure) ")
        state.handle("bullshit") should be("initialize matchfield with (i) or set a figure with (s row col figure) ")
      }

    }
  }
}