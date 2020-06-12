import de.htwg.se.stratego.model.{CharacterList, Game, MatchField, Player}

def up(a: Int, b: Int): Unit ={
  println("up")
  println(a)
  println(b)
}
def down(a: Int, b: Int): Unit ={
  println("down")
  println(a)
  println(b)
}

val input = "d 2 4"
input.toList.filter(c=> c != ' ') match{
  case 'u' :: row :: col :: Nil => up(row.toString.toInt, col.toString.toInt)
  case 'd' :: row :: col :: Nil => down(row.toString.toInt, col.toString.toInt)
  case _ =>
}


val matchField = new MatchField(4, 4, false)
val characList = CharacterList(4)
val playerBlue = Player("PlayerBlue", characList.getCharacterList())
val playerRed = Player("PlayerRed", characList.getCharacterList())
val game = Game(playerBlue, playerRed, 4, matchField)

game.moveDown(matchField, 0, 0)


/*
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
      case 4 => n = size - 1
      case 5 => n = size - 1
      case 6 => n = size * 2 - 1
      case 7 => n = size * 2 - 1
      case 8 => n = size * 3 - 1
      case 9 => n = size * 3 - 1
      case 10 => n = size * 4 - 1
    }
    for (idx <- 0 to n) {
      matchField = aChar(matchField, idx, rowA(idx), colA(idx))
      matchField = bChar(matchField, idx, rowB(idx), colB(idx))
    }
    matchField
  }
   */




