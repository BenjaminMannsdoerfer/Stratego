package de.htwg.se.stratego

import de.htwg.se.stratego.aview.Tui
import de.htwg.se.stratego.controller.Controller
import de.htwg.se.stratego.model.MatchField

import scala.io.StdIn.readLine

object Stratego {

  val controller = new Controller(new MatchField(8,8,false))
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (!input.equals("q"))
  }
}
