package de.htwg.se.stratego.controller


abstract class ControllerState {

  def handle(input: String): String

  def nextState(): ControllerState

}