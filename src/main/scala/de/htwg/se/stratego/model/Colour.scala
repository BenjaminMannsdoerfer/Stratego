package de.htwg.se.stratego.model

object Colour extends Enumeration {
  sealed case class FigureCol(value:Int) {
    override def toString: String = value.toString
  }

  val BLUE = FigureCol(0)
  val RED = FigureCol(1)


}
