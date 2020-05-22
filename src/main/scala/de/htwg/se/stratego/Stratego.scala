package de.htwg.se.stratego

import de.htwg.se.stratego.model.{MatchField, CharacterList, Player, Field}

import scala.io.StdIn.readLine

object Stratego {

  def main(args: Array[String]): Unit = {

    println("Welcome to Stratego!")

    print("Enter the size of the GameBoard: ")
    val sizeOfBoard = readLine().toInt

    val list = new CharacterList(sizeOfBoard.toInt)

    print("Name of first PLAYER: ")

    val playerA= new Player(readLine(), list.getCharacterList())

    print("Name of second PLAYER: ")

    val playerB = new Player(readLine(), list.getCharacterList())


    val board = new MatchField(sizeOfBoard, sizeOfBoard, true)
    val field = new Field(false)
    val up = board.fields.updateField(0, 2, field)
    println(board.fields)
    println(board)
    val board2 = board.copy(up)
    print(board2)
  }
}
