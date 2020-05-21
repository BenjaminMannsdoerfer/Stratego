package de.htwg.se.stratego.model


case class Field(isSet:Boolean) {

  override def toString: String = isSet.toString

}
