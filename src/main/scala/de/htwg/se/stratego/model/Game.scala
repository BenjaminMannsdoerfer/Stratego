package de.htwg.se.stratego.model
import de.htwg.se.stratego.model.{MatchField,Game, Player}
import scala.io.StdIn.{readLine, readInt}
import scala.util.Random.shuffle

case class Game(playerA: Player, playerB: Player, size: Int, var matchField: MatchField) {
  val aList = playerA.characterList
  val bList = playerB.characterList

  def create(): MatchField = {
    var rowA: List[Int] = List()
    var colA: List[Int] = List()
    var rowB: List[Int] = List()
    var colB: List[Int] = List()

    println(playerA.name + "!")
    for (i <- aList.reverse) {
      println("Where do you want to put " + i + "? ")
      println("Enter y value of field:")
      rowA ::= readLine().toInt
      println("Enter x value of field:")
      colA ::= readLine().toInt
      println()
    }

    println(playerB.name + "! You´re turn!")
    for (j <- bList.reverse) {
      println("Where do you want to put " + j + "? ")
      println("Enter y value of field:")
      rowB ::= readLine().toInt
      println("Enter x value of field:")
      colB ::= readLine().toInt
      println()
    }
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
      matchField = aChar(matchField, idx ,rowA(idx), colA(idx))
      matchField = bChar(matchField, idx ,rowB(idx), colB(idx))
    }
    matchField
  }


  def init(): MatchField ={

    //sets Characters of PlayerA
    var r = 0
    var c = 0
    for(carac <- shuffle(aList)){
      if(r.equals(size)){
        c += 1
        r = 0
      }
      matchField = matchField.addChar(c, r, carac)
      r += 1
    }

    //Sets Characters of PlayerB
    r=0
    c= size -1
    for(carac <- shuffle(bList)){
      if(r.equals(size)){
        c -= 1
        r = 0
      }
      matchField = matchField.addChar(c, r, carac)
      r += 1
    }

    matchField
  }

  def aChar(matchfield:MatchField, idx:Int, row:Int, col:Int): MatchField = matchfield.addChar(row,col,aList(idx))
  def bChar(matchfield:MatchField, idx:Int, row:Int, col:Int): MatchField = matchfield.addChar(row,col,bList(idx))

  def moveDown(matchField: MatchField, row:Int, col:Int): MatchField = {
    val charac = matchField.fields.field(row,col).character.get
    matchField.removeChar(row,col).addChar(row+1,col,charac)
  }
}
