package de.htwg.se.stratego.model
// hier noch Health
case class Character(figure: Figure.Value, positionX : Int = 0, positionY : Int = 0, show: Boolean = true) {
  override def toString: String = figure.toString
}
