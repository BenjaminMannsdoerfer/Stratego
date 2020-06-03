package de.htwg.se.stratego.controller

import de.htwg.se.stratego.util.Observable
import de.htwg.se.stratego.model.{CharacterList, Game, GameCharacter, MatchField, Player}

import scala.io.StdIn.readLine

class Controller(var matchField:MatchField) extends Observable {
  val list = CharacterList(matchField.fields.matrixSize)
  val playerBlue = Player("PlayerBlue", list.getCharacterList())
  val playerRed = Player("PlayerRed", list.getCharacterList())
  val game = Game(playerBlue, playerRed, matchField.fields.matrixSize, matchField)


  def createEmptyMatchfield(size:Int):Unit = {
    matchField = new MatchField(size,size,false)
    notifyObservers()
  }

  def initMatchfield(): Unit = {
    matchField = game.init()
    notifyObservers()
  }

  def addChar(row:Int, col:Int, charac:GameCharacter):Unit = {
    matchField = matchField.addChar(row,col,charac)
    notifyObservers()
  }

  def moveDown(row:Int, col:Int): Unit = {
    matchField = game.moveDown(matchField, row, col)
    notifyObservers()
  }

  def moveUp(row:Int, col:Int): Unit = {
    matchField = game.moveUp(matchField, row, col)
    notifyObservers()
  }

  def moveRight(row:Int, col:Int): Unit = {
    matchField = game.moveRight(matchField, row, col)
    notifyObservers()
  }

  def moveLeft(row:Int, col:Int): Unit = {
    matchField = game.moveLeft(matchField, row, col)
    notifyObservers()
  }


  def matchFieldToString: String = matchField.toString

  def game(row:Int, col:Int): Unit = {
    val size = matchField.fields.matrixSize
    val list = CharacterList(size)

    println("Welcome to Stratego!")

    //print("Name of first PLAYER: ")

    val playerBlue = Player("PlayerBlue", list.getCharacterList())

    //print("Name of second PLAYER: ")

    val playerRed = Player("PlayerRed", list.getCharacterList())

    //println(board)

    val game = Game(playerBlue, playerRed, size, matchField)

    //board = game.create()
    //var board = game.init()
    matchField = game.moveDown(matchField, row, col)
    //matchField = board
    /*board = game.moveUp(board, 3,0)
    board = game.moveUp(board, 3,3)
    board = game.moveLeft(board,2 ,3)*/
    //board = game.moveRight(board, 1, 0)
    //matchField = board
    notifyObservers()
  }
}
