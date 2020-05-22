package de.htwg.se.stratego.model

case class Player(name: String, characterList: List[Character]) {
  override def toString: String = name
}
