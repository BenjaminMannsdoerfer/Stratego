package de.htwg.se.stratego.controller

import de.htwg.se.stratego.util.{Observable, UndoManager}
import de.htwg.se.stratego.model.{CharacterList, Game, GameCharacter, MatchField, Player}

class Controller(var matchField:MatchField) extends Observable {
  val list = CharacterList(matchField.fields.matrixSize)
  val playerBlue = Player("PlayerBlue", list.getCharacterList())
  val playerRed = Player("PlayerRed", list.getCharacterList())
  val game = Game(playerBlue, playerRed, matchField.fields.matrixSize, matchField)

  private val undoManager = new UndoManager


  def createEmptyMatchfield(size:Int): Unit = {
    matchField = new MatchField(size, size, false)
    notifyObservers()
  }

  def initMatchfield(): Unit = {
    matchField = game.init()
    notifyObservers()
  }

  def attack(rowA: Int, colA: Int, rowD:Int, colD:Int): Unit ={
    matchField = game.attack(matchField, rowA, colA, rowD, colD)
    notifyObservers()
  }

  def set(player: Int, row:Int, col:Int, charac:String): Unit = {
    undoManager.doStep(new SetCommand(player, row, col, charac, this))
    notifyObservers()
  }

  def move(dir: Char, row:Int, col:Int): Unit = {
    undoManager.doStep(new MoveCommand(dir, matchField, row, col, this))
    notifyObservers()
  }

  def matchFieldToString: String = matchField.toString

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers()
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers()
  }
}
