package de.htwg.se.stratego.model

case class Player(name: String, characterList: Seq[Character]) {
  override def toString: String = name
}
