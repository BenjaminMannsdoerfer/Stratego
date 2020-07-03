package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

case class Field(isSet:Boolean, character: Option[GameCharacter] = None, colour: Option[Colour.FigureCol] = None) {

  override def toString: String = character.fold(" ")("".+)

}
