package de.htwg.se.stratego.aview

import de.htwg.se.stratego.util.Observer
import de.htwg.se.stratego.controller.{CandidatesChanged, CellChanged, Controller, GameStatus, MatchFieldSizeChanged}

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
    case event: CellChanged => printTui
    case event: CandidatesChanged => printCandidates
  }

  def printTui: Unit = {
    println(controller.matchFieldToString)
    println(GameStatus.getMessage(controller.gameStatus))
  }
  def printCandidates: Unit = {
    println("Candidates: ")
    for (row <- 0 until size; col <- 0 until size) {
      if (controller.matchField.fields.field(row, col).isSet) println("("+row+","+col+"):"+controller.matchField.fields.field(row, col).isSet)
    }
  }
}
