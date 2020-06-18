package de.htwg.se.stratego.controller

import de.htwg.se.stratego.model.MatchField
import de.htwg.se.stratego.util.Command

class MoveCommand(dir: Char, matchField: MatchField, row: Int, col: Int, controller: Controller) extends Command {
  override def doStep: Unit =   controller.matchField = controller.game.move(dir, matchField, row, col)

  override def undoStep: Unit = controller.matchField =
  dir match {
      case 'u' => controller.game.move('d', matchField, row, col)
      case 'd' => controller.game.move('u', matchField, row, col)
      case 'r' => controller.game.move('l', matchField, row, col)
      case 'l' => controller.game.move('r', matchField, row, col)

    }

  override def redoStep: Unit = controller.matchField = controller.game.move(dir, matchField, row, col)
}
