package de.htwg.se.stratego.model

object Figure extends Enumeration {
  /*
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

   */

  sealed case class FigureVal(name: String, value: Int){
    override def toString: String = name
  }
  val Bomb = FigureVal("B", 11)
  val Marshal = FigureVal("M", 10)
  val General = FigureVal("9",9)
  val Colonel = FigureVal("8", 8)
  val Major = FigureVal("7", 7)
  val Captain = FigureVal("6", 6)
  val Lieutenant = FigureVal("5", 5)
  val Sergeant = FigureVal("4", 4)
  val Miner = FigureVal("3", 3)
  val Scout = FigureVal("2", 2)
  val Spy = FigureVal("1", 1)
  val Flag = FigureVal("F", 0)
}
