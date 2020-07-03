package de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.stratego.StrategoModule
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameFinished, GameStatus, MachtfieldInitialized, NewGame, PlayerChanged, PlayerSwitch}
import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Field, Game, MatchField, Matrix}
import de.htwg.se.stratego.model.playerComponent.Player
import de.htwg.se.stratego.util.UndoManager

import scala.swing.Publisher


class Controller @Inject()(var matchField:MatchFieldInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new StrategoModule)

  val list = CharacterList(matchField.fields.matrixSize)
  var playerBlue = Player("PlayerBlue", list.getCharacterList())
  var playerRed = Player("PlayerRed", list.getCharacterList())
  val game = Game(playerBlue, playerRed, matchField.fields.matrixSize, matchField)
  var playerList = List[Player](playerBlue,playerRed)

  var gameStatus: GameStatus = IDLE

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
        playerRed = playerRed.copy(player2, list.getCharacterList())
        playerList = List[Player](playerBlue,playerRed)
        nextState
        publish(new PlayerChanged)

        "Hello " + player1 + " and " + player2 + "!\n" + "Set your figures automatically with (i) " +
          "or manually with (s row col figure)\n"

      case _ => "Please enter the names like (player1 player2)"
    }
  }

  def createEmptyMatchfield(size:Int): String = {
    matchField = new MatchField(size, size, false)
    gameStatus=NEW
    state = EnterPlayer(this)
    publish(new NewGame)
    "created new matchfield\nPlease enter the names like (player1 player2)"
  }

  def initMatchfield(): String = {
    matchField = game.init()
    gameStatus=INIT
    nextState
    publish(new MachtfieldInitialized)
    playerList(currentPlayerIndex) + " it's your turn!"
  }

  def attack(rowA: Int, colA: Int, rowD:Int, colD:Int): String ={
    if(matchField.fields.field(rowA, colA).isSet.equals(true) && matchField.fields.field(rowD, colD).isSet.equals(true)
      && matchField.fields.field(rowD,colD).character.get.figure.value==0){ //both fields are set and attacked figure is flag
      publish(new GameFinished)
      currentPlayerIndex=0
      nextState
      createEmptyMatchfield(matchField.fields.matrixSize)
      return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
        "Game finished! Play new Game with (n)!"
    }
    if (matchField.fields.field(rowA,colA).isSet && matchField.fields.field(rowA,colA).colour.get.value==currentPlayerIndex
      && matchField.fields.field(rowD,colD).isSet && matchField.fields.field(rowD,colD).colour.get.value!= currentPlayerIndex) {
      matchField = game.Context.attack(matchField, rowA, colA, rowD, colD,currentPlayerIndex)
      gameStatus = ATTACK
      currentPlayerIndex= nextPlayer
      publish(new PlayerSwitch)
      publish(new FieldChanged)
    }
    ""
  }

  def set(row:Int, col:Int, charac:String): String = {
    currentPlayerIndex match {
      case 0 =>
        undoManager.doStep(new SetCommand(currentPlayerIndex, row, col, charac, this))
        if(game.bList.size == 0){
          currentPlayerIndex=nextPlayer
          publish(new PlayerSwitch)
        }

      case 1 =>
        undoManager.doStep(new SetCommand(currentPlayerIndex, row, col, charac, this))
        if(game.rList.size == 0){
          currentPlayerIndex=nextPlayer
          nextState
          publish(new MachtfieldInitialized)
        }

    }
    publish(new FieldChanged)
    if(game.rList.size == 0){
      return "Move Figures with (m direction[u,d,r,l] row col) or attack with (a row col row col)\n" +
        playerList(currentPlayerIndex) + " it's your turn!"
    }
    if(game.bList.size == 0){
      return ""
    }
    ""
  }

  def move(dir: Char, row:Int, col:Int): String = {
    if (matchField.fields.field(row,col).isSet && matchField.fields.field(row,col).colour.get.value==currentPlayerIndex) {
        undoManager.doStep(new MoveCommand(dir, matchField, row, col, currentPlayerIndex, this))
        if (!matchField.fields.field(row,col).isSet) {
          currentPlayerIndex = nextPlayer
          publish(new FieldChanged)
          publish(new PlayerSwitch)
        }
      }
    ""
  }

  def matchFieldToString: String = matchField.toString

  def undo: String = {
    undoManager.undoStep
    gameStatus = UNDO
    publish(new FieldChanged)
    "undo"
  }

  def redo: String = {
    undoManager.redoStep
    gameStatus = REDO
    publish(new FieldChanged)
    "redo"
  }

  def nextState: Unit = {
    state = state.nextState()
    publish(new FieldChanged)
  }

  def statusString:String = GameStatus.getMessage(gameStatus)
  def nextPlayer: Int = if (currentPlayerIndex == 0) 1 else 0

  override def getSize: Int = matchField.fields.matrixSize

  override def getField: Matrix[Field] = matchField.fields
}
