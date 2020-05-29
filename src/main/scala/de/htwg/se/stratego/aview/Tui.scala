package de.htwg.se.stratego.aview

import de.htwg.se.stratego.model.{CharacterList, Game, MatchField, Player}
import de.htwg.se.stratego.util.Observer
import de.htwg.se.stratego.controller.Controller

import scala.io.StdIn.readLine

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  val size = 4

  def processInputLine(input: String):Unit = {
    input match {
      case "q" =>
      case "n" => controller.createEmptyMatchfield(size)
        //print("Enter the size of the GameBoard: ")
        //val sizeOfBoard = readLine().toInt
        //val board = matchfield.copy(new MatchField(sizeOfBoard, sizeOfBoard, false).fields)
      case "g" => controller.game()
      case _ => println("wrong input!\nenter \"h\" for more informations!\n")
        //matchfield
      }
  }
  override def update: Unit = println(controller.matchFieldToString)
}
