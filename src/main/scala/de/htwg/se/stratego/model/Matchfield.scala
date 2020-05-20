package de.htwg.se.stratego.model

import scala.util.Random

case class Matchfield(size: Int, playerA: Player, playerB: Player) {


  def searchVectors(x: Int, vec: Vector[Vector[String]]) =
    for {
      i <- 0 until vec.size
      j <- 0 until vec(i).size
      if vec(i)(j) == x
    } yield (i, j)

  def randomA(): Seq[Character] = {
    val randomList = Random.shuffle(playerA.characterList)
    return randomList
  }

  def randomB(): Seq[Character] = {
    val randomList = Random.shuffle(playerB.characterList)
    return randomList
  }

  def setFigures(row: Int, col: Int, sizeA: Int, sizeB: Int): String  = {
    if (col <= sizeA) {
      val characListRandA = randomA()
      return (row+col).toString

    } else if (col >= sizeB) {
      val characListRandB = randomB()
      return characListRandB(row).toString
    } else {
      return " "
    }
    return ""
  }

  def configure(row: Int, col: Int): String = {
    size match {
      case 4 =>
        return setFigures(row, col, size - 4, size - 1)
      case 5 =>
        return setFigures(row, col, size - 5, size - 1)

      case 6 =>
        return setFigures(row, col, size - 5, size - 2)

      case 7 =>
        return setFigures(row, col, size - 6, size - 2)

      case 8 =>
        return setFigures(row, col, size - 6, size - 3)

      case 9 =>
        return setFigures(row, col, size - 7, size - 3)

      case 10 =>
        return setFigures(row, col, size - 7, size - 4)
    }
  }

  def frame(row:Int): String = {
    val plus = "+"
    val line = "-"
    val combine = (plus + line * 5) * row + plus
    return combine
  }

  def content(row:Int): String = {
    val pipe = "|"
    val placeholder = "X"
    val combine = s"$pipe  X  "
    return combine
  }

  override def toString:String = {
    val n = size - 1
    val pipe = "|"
    val new_line = "\n"
    var s = ""

    s += frame(size) + new_line
    for (i <- 0 to n) {

      for (j <- 0 to n) {
        s += content(size).replace("X", configure(j, i))
        if (j == n) {
          s += pipe + new_line
          s += frame(size) + new_line
        }
      }
    }
    return s
  }

}