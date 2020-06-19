package de.htwg.se.stratego.model


case class Field(isSet:Boolean, character: Option[GameCharacter] = None, colour: Option[Colour.FigureCol] = None) {

  override def toString: String = character.fold(" ")("".+)

}
