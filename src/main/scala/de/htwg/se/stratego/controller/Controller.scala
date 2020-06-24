package de.htwg.se.stratego.controller

import de.htwg.se.stratego.util.{Observable, UndoManager}
import de.htwg.se.stratego.model.{CharacterList, Game, MatchField, Player}

import scala.swing.Publisher


class Controller(var matchField:MatchField) extends Publisher {


  val list = CharacterList(matchField.fields.matrixSize)
  var playerBlue = Player("PlayerBlue", list.getCharacterList())
  var playerRed = Player("PlayerRed", list.getCharacterList())
  val game = Game(playerBlue, playerRed, matchField.fields.matrixSize, matchField)
  var playerList = List[Player](playerBlue,playerRed)

  var currentPlayerIndex: Int = 0

  var state: ControllerState = EnterPlayer(this)

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
    publish(new CellChanged)
    "created new matchfield\nPlease enter the names like (player1 player2)"
  }

  def initMatchfield(): String = {
    matchField = game.init()
    nextState
    publish(new CellChanged)
    "matchfield initialized\nMove Figures with (m direction[u,d,r,l] row col) or attack with (a row col row col)\n" +
    playerList(currentPlayerIndex) + " it's your turn!"

  }

  def attack(rowA: Int, colA: Int, rowD:Int, colD:Int): String ={
    if(matchField.fields.field(rowD,colD).character.get.figure.value==0 && matchField.fields.field(rowA, colA).isSet.equals(true) && matchField.fields.field(rowD, colD).isSet.equals(true)){ //both fields are set and attacked figure is flag
      matchField = game.Context.attack(matchField, rowA, colA, rowD, colD,currentPlayerIndex)
      nextState
      currentPlayerIndex=0
      return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
        "Game finished! Play new Game with (n)!"
    }
    matchField = game.Context.attack(matchField, rowA, colA, rowD, colD,currentPlayerIndex)
    currentPlayerIndex= nextPlayer

    publish(new CellChanged)
    playerList(currentPlayerIndex) + " it's your turn!"
  }

  def set(row:Int, col:Int, charac:String): String = {
    currentPlayerIndex match {
      case 0 =>
        if(game.bList.size == 0){
          currentPlayerIndex=nextPlayer
        }
        undoManager.doStep(new SetCommand(currentPlayerIndex, row, col, charac, this))
      case 1 =>
        undoManager.doStep(new SetCommand(currentPlayerIndex, row, col, charac, this))
        if(game.rList.size == 0){
          currentPlayerIndex=nextPlayer
          nextState
        }



    }
    publish(new CellChanged)
    if(game.rList.size == 0){
      return "matchfield initialized\nMove Figures with (m direction[u,d,r,l] row col) or attack with (a row col row col)\n" +
        playerList(currentPlayerIndex) + " it's your turn!"
    }
    if(game.bList.size == 0){
      return playerList(1) + " it's your turn!"
    }
    playerList(currentPlayerIndex) + " it's your turn!"
  }

  def move(dir: Char, row:Int, col:Int): String = {
    undoManager.doStep(new MoveCommand(dir, matchField, row, col, currentPlayerIndex, this))
    currentPlayerIndex=nextPlayer
    publish(new CellChanged)
    playerList(currentPlayerIndex) + " it's your turn!"
  }

  def matchFieldToString: String = matchField.toString

  def undo: String = {
    undoManager.undoStep
    publish(new CellChanged)
    "undo"
  }

  def redo: String = {
    undoManager.redoStep
    publish(new CellChanged)
    "redo"
  }

  def nextState: Unit = {
    state = state.nextState()
    publish(new CellChanged)
  }


  private def nextPlayer: Int = if (currentPlayerIndex == 0) 1 else 0

}
