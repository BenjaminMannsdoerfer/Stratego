package de.htwg.se.stratego.controller

import de.htwg.se.stratego.util.Observable
import de.htwg.se.stratego.model.{CharacterList, Game, GameCharacter, MatchField, Player}

import scala.io.StdIn.readLine

class Controller(var matchField:MatchField) extends Observable {
  def createEmptyMatchfield(size:Int):Unit = {
    matchField = new MatchField(size,size,false)
    notifyObservers()
  }

  def initMatchfield(game:Game,playerBlue:Player, playerRed:Player): Unit = {
    val game = Game(playerBlue,playerRed,matchField.fields.matrixSize,matchField)
    game.init()
  }

  def addChar(row:Int, col:Int, charac:GameCharacter):Unit = {
    matchField = matchField.addChar(row,col,charac)
    notifyObservers()
  }

  def matchFieldToString: String = matchField.toString

  def game(): Unit = {
    val size = matchField.fields.matrixSize
    val list = CharacterList(size)

    println("Welcome to Stratego!")

    print("Name of first PLAYER: ")

    val playerBlue = Player("PlayerBlue", list.getCharacterList())

    print("Name of second PLAYER: ")

    val playerRed = Player("PlayerRed", list.getCharacterList())

    //println(board)

    val game = Game(playerBlue, playerRed, size, matchField)

    //board = game.create()
    var board = game.init()

    board = game.moveDown(board, 0,0)
    /*board = game.moveUp(board, 3,0)
    board = game.moveUp(board, 3,3)
    board = game.moveLeft(board,2 ,3)*/
    //board = game.moveRight(board, 1, 0)
  }
}
