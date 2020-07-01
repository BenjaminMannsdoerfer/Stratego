package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.playerComponent.Player

case class Game(playerA: Player, playerB: Player, size: Int, var matchField: MatchFieldInterface) {
  var bList = playerA.characterList
  var rList = playerB.characterList

  def init(): MatchFieldInterface = {

    var row = 0
    var col = 0
    // shuffle(aList)
    for (charac <- bList) {
      if (row.equals(size)) {
        col += 1
        row = 0
      }
      matchField = matchField.addChar(col, row, charac,Colour.FigureCol(0))
      row += 1
    }

    row = 0
    col = size - 1
    // shuffle(bList)
    for (charac <- rList) {
      if (row.equals(size)) {
        col -= 1
        row = 0
      }
      matchField = matchField.addChar(col, row, charac,Colour.FigureCol(1))
      row += 1
    }
    matchField
  }

  def characValue(charac:String): Int = {
    if (charac.matches("[1-9]")) {
      return charac.toInt
    }
    charac match {
      case "B" => 11
      case "M" => 10
      case "F" => 0
    }
  }

  def isBlueField(row:Int): Boolean = {
    matchField.fields.matrixSize match {
      case 4 | 5 => if(row > 0) false else true
      case 6 | 7 => if(row > 1) false else true
      case 8 | 9 => if(row > 2) false else true
      case 10    => if(row > 3) false else true
    }
  }

  def isRedField(row:Int): Boolean = {
    matchField.fields.matrixSize match {
      case 4 | 5 => if(row < 3) false else true
      case 6 | 7 => if(row < 4) false else true
      case 8 | 9 => if(row < 5) false else true
      case 10    => if(row < 6) false else true
    }
  }

  def setBlue(row:Int, col:Int, charac: String): MatchFieldInterface = {
    if (isBlueChar(charac) && isBlueField(row) && !matchField.fields.field(row,col).isSet) {
      val idx = bList.indexOf(GameCharacter(Figure.FigureVal(charac,characValue(charac))))
      matchField = matchField.addChar(row,col,bList(idx),Colour.FigureCol(0))
      bList = bList.patch(idx, Nil, 1)
      return matchField
    }
    matchField
  }

  def isBlueChar(charac:String): Boolean = {
    bList.foreach(GameCharacter =>
      if(GameCharacter.toString().equals(charac)) {
        return true
      }
    )
    false
  }

  def set(player: Int, row:Int, col:Int, charac: String): MatchFieldInterface = {
    player match{
      case 0 => return setBlue(row, col, charac)
      case 1 => return setRed(row, col, charac)
    }
    matchField
  }

  def setRed(row:Int, col:Int, charac: String): MatchFieldInterface = {
    if (isRedChar(charac) && isRedField(row) && !matchField.fields.field(row,col).isSet) {
      val idx = rList.indexOf(GameCharacter(Figure.FigureVal(charac,characValue(charac))))
      matchField = matchField.addChar(row,col,rList(idx),Colour.FigureCol(1))
      rList = rList.patch(idx, Nil, 1)
      return matchField
    }
    matchField
  }

  def isRedChar(charac:String): Boolean = {
    rList.map(GameCharacter => if(GameCharacter.figure.name.equals(charac)) return true else false)
    false
  }

  def move(direction: Char, matchField: MatchFieldInterface, row: Int, col: Int, currentPlayerIndex: Int): MatchFieldInterface = {
    if (matchField.fields.field(row,col).isSet.equals(true) && matchField.fields.field(row,col).colour.get.value == currentPlayerIndex) {
      direction match {
        case 'u' => return moveUp(matchField, row, col)
        case 'd' => return moveDown(matchField, row, col)
        case 'r' => return moveRight(matchField, row, col)
        case 'l' => return moveLeft(matchField, row, col)
      }
    }
    matchField
  }

  def moveDown(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (row == size - 1) {
      println("The Figure can not set out of bounds!")
      return matchField
    }
    if (isFlagOrBomb(matchField, row,col)) {
      println("Flag and Bombs can't move!")
      return matchField
    }
    if (matchField.fields.field(row + 1, col).isSet.equals(false)) {
      matchField.removeChar(row, col).addChar(row + 1, col, matchField.fields.field(row, col).character.get,matchField.fields.field(row,col).colour.get)
    } else {
      val f = matchField.fields.field(row + 1, col)
      println(s"Field (${row + 1},$col) is set with Figure $f!")
      matchField
    }
  }

  def moveUp(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (row == 0) {
      println("The Figure can not set out of bounds!")
      return matchField
    }
    if (isFlagOrBomb(matchField, row,col)) {
      println("Flag and Bombs can't move!")
      return matchField
    }
    if (matchField.fields.field(row - 1, col).isSet.equals(false)) {
      matchField.removeChar(row, col).addChar(row - 1, col, matchField.fields.field(row, col).character.get, matchField.fields.field(row,col).colour.get)
    } else {
      val f = matchField.fields.field(row - 1, col)
      println(s"Field (${row - 1},$col) is set with Figure $f!")
      matchField
    }
  }

  def moveLeft(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (col == 0) {
      println("The Figure can not set out of bounds!")
      return matchField
    }
    if (isFlagOrBomb(matchField, row,col)) {
      println("Flag and Bombs can't move!")
      return matchField
    }
    if (matchField.fields.field(row, col - 1).isSet.equals(false)) {
      matchField.removeChar(row, col).addChar(row, col - 1, matchField.fields.field(row, col).character.get,matchField.fields.field(row,col).colour.get)
    } else {
      val f = matchField.fields.field(row, col - 1)
      println(s"Field ($row,${col - 1}) is set with Figure $f!")
      matchField
    }
  }

  def moveRight(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (col == size - 1) {
      println("The Figure can not set out of bounds!")
      return matchField
    }

    if (isFlagOrBomb(matchField,row,col)) {
      println("Flag and Bombs can't move!")
      return matchField
    }

    if (matchField.fields.field(row, col + 1).isSet.equals(false)) {
      matchField.removeChar(row, col).addChar(row, col + 1, matchField.fields.field(row, col).character.get,matchField.fields.field(row,col).colour.get)
    } else {
      val f = matchField.fields.field(row, col + 1)
      println(s"Field ($row,${col + 1}) is set with Figure $f!")
      matchField
    }
  }

  def figureHasValue(matchF: MatchFieldInterface, row: Int,col: Int): Int = {
    matchF.fields.field(row,col).character.get.figure.value
  }

  def isFlagOrBomb(matchField: MatchFieldInterface, row: Int,col: Int): Boolean = if(matchField.fields.field(row,col).character.get.figure.value == 0 ||
    matchField.fields.field(row,col).character.get.figure.value == 11) true else false

  object Context extends Game(playerA: Player, playerB: Player, size: Int, matchField: MatchFieldInterface) {
    override def figureHasValue(matchF: MatchFieldInterface, row: Int, col: Int): Int = super.figureHasValue(matchF, row, col)
    def attack(matchField: MatchFieldInterface, rowA: Int, colA: Int, rowD: Int, colD: Int, currentPlayerIndex: Int): MatchFieldInterface = {
      def strategy1:MatchFieldInterface = matchField
      def strategy3:MatchFieldInterface = matchField.removeChar(rowD, colD).addChar(rowD, colD, matchField.fields.field(rowA,colA).character.get,matchField.fields.field(rowA,colA).colour.get).removeChar(rowA,colA)
      def strategy5 = println("Flag has been found! Game finished!")
      def strategy6:MatchFieldInterface = matchField.removeChar(rowD, colD)
      def strategy7:MatchFieldInterface = matchField.removeChar(rowA, colA)
      def strategy8:MatchFieldInterface = matchField.removeChar(rowA, colA).removeChar(rowD, colD)

      val fieldIsSet = if(matchField.fields.field(rowA, colA).isSet.equals(false) || matchField.fields.field(rowD, colD).isSet.equals(false)) return strategy1
      val attackIsValid = if(matchField.fields.field(rowD,colD).colour.get.value == currentPlayerIndex && matchField.fields.field(rowA,colA).colour.get.value == currentPlayerIndex) return strategy1
      val enemyAttackIsValid = if(matchField.fields.field(rowD,colD).colour.get.value != currentPlayerIndex && matchField.fields.field(rowA,colA).colour.get.value != currentPlayerIndex) return strategy1
      val wrongPlayerAttackIsValid = if(matchField.fields.field(rowD,colD).colour.get.value == currentPlayerIndex && matchField.fields.field(rowA,colA).colour.get.value != currentPlayerIndex) return strategy1
      val attackToFarAway = if(((Math.abs(rowA-rowD)>1)||(Math.abs(colA-colD)>1))||((Math.abs(rowA-rowD)==1)&&(Math.abs(colA-colD)==1))) return strategy1
      val unableToAttack = if(figureHasValue(matchField, rowA,colA).equals(11)|figureHasValue(matchField,rowA,colA).equals(0)) return strategy1
      val isFlagOrBomb = if(matchField.fields.field(rowA,colA).character.get.figure.value == 0 || matchField.fields.field(rowA,colA).character.get.figure.value == 11) return strategy1
      val minerAttackTheBomb = if(figureHasValue(matchField, rowA, colA) == 3 && figureHasValue(matchField, rowD, colD) == 11) return strategy6
      val attackFlag = if(figureHasValue(matchField,rowD, colD)==0) strategy5
      val spyAttackMarshal = if((figureHasValue(matchField, rowA,colA) == 1) && (figureHasValue(matchField, rowD, colD) == 10)) return strategy3
      val defenceIsStronger = if (figureHasValue(matchField, rowA,colA) < figureHasValue(matchField,rowD, colD)) return strategy7
      val attackIsStronger = if(figureHasValue(matchField, rowA,colA) > figureHasValue(matchField,rowD, colD)) return strategy3
      val attackEqualsDefence = if(figureHasValue(matchField, rowA,colA) == figureHasValue(matchField,rowD, colD)) return strategy8
      matchField
    }
  }
}
