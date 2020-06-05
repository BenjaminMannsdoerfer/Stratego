package de.htwg.se.stratego.model

object Figure extends Enumeration {
  val Bomb = Value("B")
  val Marshal = Value("M")
  val General = Value("9")
  val Colonel = Value("8")
  val Major = Value("7")
  val Captain = Value("6")
  val Lieutenant = Value("5")
  val Sergeant = Value("4")
  val Miner = Value("3")
  val Scout = Value("2")
  val Spy = Value("1")
  val Flag = Value("F")
}
