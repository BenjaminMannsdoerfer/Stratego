package de.htwg.se.stratego.controller.controllerComponent.controllerMockImpl

import de.htwg.se.stratego.controller.controllerComponent.ControllerInterface
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.MatchField

class Controller(var matchField: MatchFieldInterface) extends ControllerInterface{
  override def handle(input: String): String = ???

  override def welcome: String = ???

  override def setPlayers(input: String): String = ???

  override def createEmptyMatchfield(size: Int): String = ???

  override def initMatchfield: String = ???

  override def attack(rowA: Int, colA: Int, rowD: Int, colD: Int): String = ???

  override def set(row: Int, col: Int, charac: String): String = ???

  override def move(dir: Char, row: Int, col: Int): String = ???

  override def matchFieldToString: String = ???

  override def undo: String = ???

  override def redo: String = ???

  override def nextState: Unit = ???

  override def statusString: String = ???

  override def nextPlayer: Int = ???
}
