package de.htwg.se.stratego.model

import scala.util.Random

case class Game(playerRed: Player, playerBlue: Player, size: Int, var matchField: MatchField) {
  val blueList = playerBlue.characterList
  val redList = playerRed.characterList

  def create(size:Int, rowB:List[Int], colB:List[Int], rowR:List[Int], colR:List[Int]): MatchField = {
    var n = 0
    size match {
      case 4 => n = size-1
      case 5 => n = size-1
      case 6 => n = size*2-1
      case 7 => n = size*2-1
      case 8 => n = size*3-1
      case 9 => n = size*3-1
      case 10 => n = size*4-1
    }
    for (idx <- 0 to n) {
      matchField = blueChar(matchField, idx ,rowB(idx), colB(idx))
      matchField = redChar(matchField, idx ,rowR(idx), colR(idx))
    }
    matchField
  }

  // immer direkt den Character ansprehcen anstatt über die Liste und dann in einer Methode prüfen ob Anazahl Charaktere bereits vergeben ist
  def blueChar(matchfield:MatchField, idx:Int, row:Int, col:Int): MatchField = matchfield.addChar(row,col,blueList(idx))//{
    //val col = Random.nextInt(size)

    //val redList = playerRed.characterList
    // hier switch case
    /*size match {

    }*/
    //return matchfield.addChar(row,col,blueList(idx))
    //return matchfield.set(row,col,redList(idx))
  //}

  def redChar(matchfield:MatchField, idx:Int, row:Int, col:Int): MatchField = matchfield.addChar(row,col,redList(idx)) //{
    //val col = Random.nextInt(size)
    //val blueList = playerBlue.characterList

    //return matchfield.set(0,col,blueList(idx))
    //return matchfield.addChar(row,col,redList(idx))
  //}



}
