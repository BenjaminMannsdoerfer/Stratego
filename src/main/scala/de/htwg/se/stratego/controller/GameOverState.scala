package de.htwg.se.stratego.controller

case class GameOverState(controller: Controller) extends ControllerState{
  override def handle(input: String): String = "GameOver"

  override def nextState(): ControllerState = SetState(controller)

}
