package de.htwg.se.stratego

import com.google.inject.Guice
import de.htwg.se.stratego.aview.Tui
import de.htwg.se.stratego.aview.gui.{PlayerFrame, SetFrame, SwingGui}
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged}
import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.MatchField

import scala.io.StdIn.readLine

object Stratego {

  val injector = Guice.createInjector(new StrategoModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  val gui = new PlayerFrame(controller)

  def main(args: Array[String]): Unit = {
    //println(controller.welcome())
    var input = ""
    do {
      input = readLine()
      println(tui.processInputLine(input))
    } while (!input.equals("q"))
  }
}
