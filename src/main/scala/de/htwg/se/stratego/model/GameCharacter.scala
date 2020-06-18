package de.htwg.se.stratego.model

case class GameCharacter(figure: Figure.FigureVal, show: Boolean = true) {
  override def toString: String = figure.toString
}
