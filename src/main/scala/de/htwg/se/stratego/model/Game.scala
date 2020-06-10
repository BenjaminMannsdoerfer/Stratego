package de.htwg.se.stratego.model
import de.htwg.se.stratego.model.{MatchField,Game, Player}
import scala.io.StdIn.{readLine, readInt}
import scala.util.Random.shuffle

case class Game(playerA: Player, playerB: Player, size: Int, var matchField: MatchField) {
  val aList = playerA.characterList
  val bList = playerB.characterList

  def init(): MatchField = {

    //sets Characters of PlayerA
    var row = 0
    var col = 0
    // shuffle(aList)
    for (charac <- aList) {
      if (row.equals(size)) {
        col += 1
        row = 0
      }
      matchField = matchField.addChar(col, row, charac)
      row += 1
    }

    //Sets Characters of PlayerB
    row = 0
    col = size - 1
    // shuffle(bList)
    for (charac <- bList) {
      if (row.equals(size)) {
        col -= 1
        row = 0
      }
      matchField = matchField.addChar(col, row, charac)
      row += 1
    }
    matchField
  }

  def switchPlayer(): Unit = {
  }

  def aChar(matchfield: MatchField, idx: Int, row: Int, col: Int): MatchField = matchfield.addChar(row, col, aList(idx))

  def bChar(matchfield: MatchField, idx: Int, row: Int, col: Int): MatchField = matchfield.addChar(row, col, bList(idx))

  def moveDown(matchField: MatchField, row: Int, col: Int): MatchField = {
    matchField.removeChar(row, col).addChar(row + 1, col, matchField.fields.field(row, col).character.get)
  }

  def moveUp(matchField: MatchField, row: Int, col: Int): MatchField = {
    matchField.removeChar(row, col).addChar(row - 1, col, matchField.fields.field(row, col).character.get)
  }

  def moveLeft(matchField: MatchField, row: Int, col: Int): MatchField = {
    matchField.removeChar(row, col).addChar(row, col - 1, matchField.fields.field(row, col).character.get)
  }

  def moveRight(matchField: MatchField, row: Int, col: Int): MatchField = {
    if (col == size - 1) {
      println("The Figure can not set out of bounds!")
      return matchField
    }
    if (matchField.fields.field(row, col + 1).isSet.equals(false)) {
      matchField.removeChar(row, col).addChar(row, col + 1, matchField.fields.field(row, col).character.get)
    } else {
      val f = matchField.fields.field(row, col + 1)
      println(s"Field ($row,${col + 1}) is set with Figure $f!")
      matchField
    }
  }
  def figureHasValue(matchF: MatchField, row: Int,col: Int): Int ={
    matchF.fields.field(row,col).character.get.figure.value
  }

  def attack(matchField: MatchField, rowA: Int, colA: Int, rowD: Int, colD: Int): MatchField={
    println("Attack:" + matchField.fields.field(rowA,colA).character.get.figure.value)
    println("Defence:" + matchField.fields.field(rowD,colD).character.get.figure.value)
    println("rowDistance: "+ math.abs(rowA-rowD) + " colDistance: "+ math.abs(colA-colD))

    if(((Math.abs(rowA-rowD)>1)||(Math.abs(colA-colD)>1))||((Math.abs(rowA-rowD)==1)&&(Math.abs(colA-colD)==1))){ //field of attacked character is too far away
      return matchField
    }
    if(matchField.fields.field(rowA, colA).isSet.equals(true) && matchField.fields.field(rowD, colD).isSet.equals(true)){ //both fields are set
      if(figureHasValue(matchField, rowA,colA).equals(11)|figureHasValue(matchField,rowA,colA).equals(0)){ //flag and bomb are unable to attack
        return matchField
      }
      if((figureHasValue(matchField, rowA,colA) == 1) && (figureHasValue(matchField, rowD, colD) == 10)){ //attacker is spy and attacked figure is marshal
        return matchField.removeChar(rowD, colD).addChar(rowD, colD, matchField.fields.field(rowA,colA).character.get).removeChar(rowA,colA)
      }
      if(figureHasValue(matchField, rowA,colA) > figureHasValue(matchField,rowD, colD)) {
        if(figureHasValue(matchField,rowD, colD)==0){ //attacked figure is flag
          println("Flag has been found! Game finished!")
        }
        return matchField.removeChar(rowD, colD).addChar(rowD, colD, matchField.fields.field(rowA,colA).character.get).removeChar(rowA,colA)
      }
      if(figureHasValue(matchField, rowD, colD).equals(11)){ //attacked figure is a bomb
        if(figureHasValue(matchField, rowA, colD) == 3){ //attacker is miner => just remove bomb
          return matchField.removeChar(rowD, colD)
        }
        return matchField.removeChar(rowA, colA).removeChar(rowD, colD) //else remove both figures
      }
    }
    matchField
  }

  def outOfBounds(matchField: MatchField, row: Int, col: Int): String = {
    if (col == size - 1) {
      val s = "The Figure can not set out of bounds\n!" + matchField.toString
      s
    } else
      moveRight(matchField,row,col).toString
  }
}

