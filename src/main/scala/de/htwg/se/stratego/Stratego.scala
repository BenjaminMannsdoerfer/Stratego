package de.htwg.se.stratego

import de.htwg.se.stratego.model.{CharacterList, Field, Figure, Game, GameCharacter, MatchField, Player}

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

    var board = new MatchField(sizeOfBoard, sizeOfBoard, false)
    println(board)

    val game = new Game(playerA, playerB, sizeOfBoard, board)

    //board = game.create()
    board = game.init()

    board = game.moveDown(board, 0,0)

    print(board)

  }
}
