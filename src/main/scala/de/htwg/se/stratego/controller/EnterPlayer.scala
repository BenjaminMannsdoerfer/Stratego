package de.htwg.se.stratego.controller

case class EnterPlayer(controller: Controller) extends ControllerState{
  override def handle(input: String): String = controller.setPlayers(input)
  override def nextState(): ControllerState = SetState(controller)
}
