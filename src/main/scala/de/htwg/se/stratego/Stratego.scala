package de.htwg.se.stratego

import de.htwg.se.stratego.aview.Tui
import de.htwg.se.stratego.aview.gui.SwingGui
import de.htwg.se.stratego.controller.{CellChanged, Controller}
import de.htwg.se.stratego.model.MatchField

import scala.io.StdIn.readLine

object Stratego {

  val defaultsize = 10
  val controller = new Controller(new MatchField(defaultsize,defaultsize,false))
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  controller.publish(new CellChanged)

  def main(args: Array[String]): Unit = {
    println(controller.welcome())
    var input = ""
    do {
      input = readLine()
      println(tui.processInputLine(input))
    } while (!input.equals("q"))
  }
}
