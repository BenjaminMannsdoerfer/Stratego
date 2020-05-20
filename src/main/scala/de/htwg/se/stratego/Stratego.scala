package de.htwg.se.stratego

import de.htwg.se.stratego.model.{CharacterList, Matchfield, Player}

object Stratego {
  def main(args: Array[String]): Unit = {
    val list = new CharacterList(4)
    val player1 = new Player("Player1", list.getCharacterList())
    val player2 = new Player("Player2", list.getCharacterList())
    val matchfield = new Matchfield(4, player1, player2)
    println(matchfield)



    val vector :Vector[Vector[String]] = Vector(Vector("+---+"),Vector("|||"))



  }
}
