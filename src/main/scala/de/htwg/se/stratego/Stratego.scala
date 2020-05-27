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
    //val listB = playerB.characterList
    //print(board.set(0,0,listB(0)))


    /*val characterG = new GameCharacter(Figure.General)
    val fieldG = new Field(true, Some(characterG))
    val up1 = board.fields.updateField(0, 0, fieldG)
    val board2 = board.copy(up1)
    print(board2)

    val characterF = new GameCharacter(Figure.Flag)
    val fieldF = new Field(true, Some(characterF))
    val up3 = board2.fields.updateField(0, 1, fieldF)
    val board3 = board.copy(up3)
    print(board3)

    val characterCo = new GameCharacter(Figure.Colonel)
    val fieldCo = new Field(true, Some(characterCo))
    val up4 = board3.fields.updateField(0, 2, fieldCo)
    val board4 = board.copy(up4)
    print(board4)

    val characterCa = new GameCharacter(Figure.Captain)
    val fieldCa = new Field(true, Some(characterCa))
    val up5 = board4.fields.updateField(0, 3, fieldCa)
    val board5 = board.copy(up5)
    print(board5)

    println()*/


    val game = new Game(playerA, playerB, sizeOfBoard, board)

    var rowB: List[Int] = List()
    var colB: List[Int] = List()
    var rowR: List[Int] = List()
    var colR: List[Int] = List()

    println(playerA.name + "!")
    for (i <- playerA.characterList) {
      println("Where do you want to put " + i + "? ")
      println("Enter y value of field:")
      rowB ::= readLine().toInt
      println("Enter x value of field:")
      colB ::= readLine().toInt
      println()
    }

    println(playerB.name + "! You´re turn!")
    for (j <- playerB.characterList) {
      println("Where do you want to put " + j + "? ")
      println("Enter y value of field:")
      rowR ::= readLine().toInt
      println("Enter x value of field:")
      colR ::= readLine().toInt
      println()
    }
    //row.foreach(println)
    //col.foreach(println)
    //print(game.create(sizeOfBoard, rowB, colB, rowR, colR))
    board = game.create(sizeOfBoard, rowB, colB, rowR, colR)
    //
    board = game.moveDown(board, 0,0)
    //board = board.removeChar(0,0)
    //print(board)

    print(board)









  }
}
