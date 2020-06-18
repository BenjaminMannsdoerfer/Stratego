package de.htwg.se.stratego.aview

import de.htwg.se.stratego.util.Observer
import de.htwg.se.stratego.controller.Controller

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  val size = 4

  def processInputLine(input: String):String = {
    input match {
      case "q" =>"Bye bye! :)"
      case "n" => controller.createEmptyMatchfield(size)
      case "z" => controller.undo
      case "y" => controller.redo
      case _ => controller.handle(input)
    }
  }

  override def update: Unit = println(controller.matchFieldToString)
}
