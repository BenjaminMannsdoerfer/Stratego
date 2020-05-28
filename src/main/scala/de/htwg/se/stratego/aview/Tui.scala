package de.htwg.se.stratego.aview

import de.htwg.se.stratego.model.{MatchField,Game, Player}
import scala.io.StdIn.{readLine, readInt}

class Tui {

  def welcome():String = {
    val welcome = "**********  WELCOME TO STRATEGO  **********\n\n"
    val n = "n:   create a new game with a gameboard of youre specified size \n"
    val q = "q:   quit programm\n"
    var m = readLine()
    println()
    return welcome + n + q
  }


  def processInputLine(input: String, matchfield:MatchField, sizeOfBoard: Int):MatchField = {
    input match {
      case "q" => matchfield
      case "n"=> matchfield.copy(new MatchField(sizeOfBoard, sizeOfBoard, false).fields)
      case _ => matchfield
      }

  }

}
