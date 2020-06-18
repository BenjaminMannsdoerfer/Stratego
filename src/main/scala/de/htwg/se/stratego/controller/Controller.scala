package de.htwg.se.stratego.controller

import de.htwg.se.stratego.util.{Observable, UndoManager}
import de.htwg.se.stratego.model.{CharacterList, Game, MatchField, Player}


class Controller(var matchField:MatchField) extends Observable {


  val list = CharacterList(matchField.fields.matrixSize)
  var playerBlue = Player("PlayerBlue", list.getCharacterList())
  var playerRed = Player("PlayerRed", list.getCharacterList())
  val game = Game(playerBlue, playerRed, matchField.fields.matrixSize, matchField)
  var playerList = List[Player](playerBlue,playerRed)

  var currentPlayerIndex: Int = 0

  var state: ControllerState = EnterPlayers(this)

  private val undoManager = new UndoManager

  def handle(input: String):String = {
    state.handle(input)
  }
  def welcome():String = {
    "Welcome to STRATEGO! " +
      "Please enter first name of Player1 and then of Player2 like (player1 player2)!"
  }

  def setPlayers(input: String): String = {
    input.split(" ").map(_.trim).toList match{
      case player1 :: player2 :: Nil =>
        playerBlue = playerBlue.copy(player1, list.getCharacterList())
        playerRed= playerRed.copy(player2, list.getCharacterList())
        playerList = List[Player](playerBlue,playerRed)
        nextState
        "Hello " + player1 + " and " + player2 + "!\n" + "Set your figures automatically with (i) " +
          "or manually with (s row col figure)\n" +
          "Player " + playerList(currentPlayerIndex) + " it's your turn!"

      case _ => "Please enter the names like (player1 player2)"
    }
  }

  def createEmptyMatchfield(size:Int): String = {
    matchField = new MatchField(size, size, false)
    notifyObservers()
    "created new matchfield"
  }

  def initMatchfield(): String = {
    matchField = game.init()
    nextState
    notifyObservers()
    "matchfield initialized\nMove Figures with (m direction[u,d,r,l] row col)"
  }

  def attack(rowA: Int, colA: Int, rowD:Int, colD:Int): String ={
    matchField = game.attack(matchField, rowA, colA, rowD, colD)
    currentPlayerIndex= nextPlayer

    notifyObservers()
    "attack figure"
  }

  def set(row:Int, col:Int, charac:String): String = {
    currentPlayerIndex match {
      case 0 =>
        if(game.bList.isEmpty){
        currentPlayerIndex=nextPlayer
        }
      case 1 =>
        if(game.rList.isEmpty){
        nextState
        }
    }
    undoManager.doStep(new SetCommand(currentPlayerIndex, row, col, charac, this))
    println(currentPlayerIndex)
    notifyObservers()
    /*
    if(game.bList.size == 0){
      return playerList(1) + " it's your turn!"
    }
     */
    playerList(currentPlayerIndex) + " it's your turn!"
  }

  def move(dir: Char, row:Int, col:Int): String = {
    undoManager.doStep(new MoveCommand(dir, matchField, row, col, this))
    nextPlayer
    notifyObservers()
    "figure in row " + row + " col " + col + " has been moved"
  }

  def matchFieldToString: String = matchField.toString

  def undo: String = {
    undoManager.undoStep
    notifyObservers()
    "undo"
  }

  def redo: String = {
    undoManager.redoStep
    notifyObservers()
    "redo"
  }

  def nextState: Unit = {
    state = state.nextState()
    notifyObservers()
  }


  private def nextPlayer: Int = if (currentPlayerIndex == 0) 1 else 0

  private def nextPlayerMessage: String = playerList(currentPlayerIndex).name + " it's your turn!"
}
