package de.htwg.se.stratego.model

import scala.util.Random

case class Field(xField:Int, yField:Int) {
  def changeKord(xKord:Int,yKord:Int): Field = copy(xKord,yKord)

  def setFigures(playerA: Player, playerB: Player, sizeA: Int, sizeB: Int): String  = {
    if (xField <= sizeA) {
      val characListRandA = Random.shuffle(playerA.characterList)
      for (i <- characListRandA) {
        return i.toString
      }
    } else if (xField >= sizeB) {
      val characListRandB = Random.shuffle(playerB.characterList)
      for (i <- characListRandB) {
        return i.toString
      }
    } else {
      return " "
    }
    return ""
  }


  def configure(playerA: Player, playerB: Player, size: Int): String = {
    size match {
      case 4 =>
        return setFigures(playerA, playerB, size - 4, size - 1)
      case 5 =>
        return setFigures(playerA, playerB, size - 5, size - 1)

      case 6 =>
        return setFigures(playerA, playerB, size - 5, size - 2)

      case 7 =>
        return setFigures(playerA, playerB, size - 6, size - 2)

      case 8 =>
        return setFigures(playerA, playerB, size - 6, size - 3)

      case 9 =>
        return setFigures(playerA, playerB, size - 7, size - 3)

      case 10 =>
        return setFigures(playerA, playerB, size - 7, size - 4)
    }
  }

}
