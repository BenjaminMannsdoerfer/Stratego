package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

case class GameCharacter(figure: Figure.FigureVal, show: Boolean = true) {
  override def toString: String = figure.toString
}
