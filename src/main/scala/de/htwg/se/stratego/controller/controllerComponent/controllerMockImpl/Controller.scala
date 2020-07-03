package de.htwg.se.stratego.controller.controllerComponent.controllerMockImpl

import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, GameStatus}
import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{Field, MatchField, Matrix}
import de.htwg.se.stratego.model.playerComponent.Player

class Controller(var matchField: MatchFieldInterface) extends ControllerInterface{
  override def handle(input: String): String = ""

  override def welcome: String = ""

  override def setPlayers(input: String): String = ""

  override def createEmptyMatchfield(size: Int): String = matchFieldToString

  override def initMatchfield: String = matchFieldToString

  override def attack(rowA: Int, colA: Int, rowD: Int, colD: Int): String = matchFieldToString

  override def set(row: Int, col: Int, charac: String): String = matchFieldToString

  override def move(dir: Char, row: Int, col: Int): String = matchFieldToString

  override def matchFieldToString: String = matchField.toString

  override def undo: String = matchFieldToString

  override def redo: String = matchFieldToString

  override def nextState: Unit = {}

  override def statusString: String = ""

  override def nextPlayer: Int = 0

  override def getSize: Int = 0

  override def gameStatus: GameStatus = IDLE

  override def playerList: List[Player] = List[Player]()

  override def currentPlayerIndex: Int = 0

  override def getField: Matrix[Field] = ???

  override def load: String = "load"

  override def save: String = "save"
}
