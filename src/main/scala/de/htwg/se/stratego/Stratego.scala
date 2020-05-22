package de.htwg.se.stratego

import de.htwg.se.stratego.model.{CharacterList, Field, GameCharacter, MatchField, Player, Figure}

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


    val board = new MatchField(sizeOfBoard, sizeOfBoard, false)
    println(board)

    val characterG = new GameCharacter(Figure.General)
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



  }
}
