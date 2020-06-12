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





