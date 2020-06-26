package de.htwg.se.stratego.aview

import de.htwg.se.stratego.controller.controllerComponent.{FieldChanged, GameStatus, MatchFieldSizeChanged, PlayerChanged}
import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller

import scala.swing.Reactor

class Tui(controller: Controller) extends Reactor {

  listenTo(controller)
  val size = controller.matchField.fields.matrixSize

  def processInputLine(input: String):String = {
    input match {
      case "q" =>"Bye bye! :)"
      case "n" => controller.createEmptyMatchfield(size)
      case "z" => controller.undo
      case "y" => controller.redo
      case _ => controller.handle(input)
    }
  }

  reactions +={
    case event: MatchFieldSizeChanged => printTui
    case event: FieldChanged => printTui
    case event: PlayerChanged => "Player Changed"
  }

  def printTui: Unit = {
    println(controller.matchFieldToString)
    println(GameStatus.getMessage(controller.gameStatus))
  }

}
