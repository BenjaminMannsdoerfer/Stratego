package de.htwg.se.stratego

import de.htwg.se.stratego.aview.Tui
import de.htwg.se.stratego.model.{MatchField}

import scala.io.StdIn.readLine

object Stratego {

  val tui = new Tui

  def main(args: Array[String]): Unit = {
    var board = new  MatchField(4,4,false)
    var input = ""
    print(tui.legend())
    do {
      input = readLine()
      board = tui.processInputLine(input,board)
    } while (!input.equals("q"))
  }
}
