package de.htwg.se.stratego.controller

import de.htwg.se.stratego.model.Figure.FigureVal
import de.htwg.se.stratego.model.{CharacterList, Game, GameCharacter, MatchField, Player}
import de.htwg.se.stratego.util.{Observable, Observer}
import org.scalatest.{Matchers, WordSpec}

class MoveCommandSpec extends WordSpec with Matchers {
  "A MoveCommandSpec" when {
    val matchField = new MatchField(4, 4, false)
    val controller = new Controller(matchField)
    controller.initMatchfield()
    val command = new MoveCommand('u', matchField,3 ,0,controller)
    val matchField2 = matchField.addChar(1,2, new GameCharacter(FigureVal("9",9)))
    val command2 = new MoveCommand('r', matchField2,1 ,2,controller)
    val command3 = new MoveCommand('l', matchField2,1 ,2,controller)

    "created" should {
      "undoStep" in {
        command.undoStep should be()
        command2.undoStep should be()
        command3.undoStep should be()
      }
    }
  }
}