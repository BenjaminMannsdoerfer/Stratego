package de.htwg.se.stratego.aview

import de.htwg.se.stratego.model.{CharacterList, Game, MatchField, Player}

import scala.io.StdIn.{readLine}

class Tui {

  def legend():String = {
    val welcome = "**********  WELCOME TO STRATEGO  **********\n\n"
    val n = "n:   create a new machtfield of youre specified size \n"
    val g = "g:   start the Game\n"
    val q = "q:   quit the programm\n"
    val p = "p:   print the matchfield\n"
    val h = "h:   show the helpdesk\n"
    welcome + n + g + p + h + q
  }


  def processInputLine(input: String, matchfield:MatchField):MatchField = {
    input match {
      case "q" => println(matchfield)
        matchfield
      case "p" => println(matchfield)
        matchfield
      case "n" =>
        print("Enter the size of the GameBoard: ")
        val sizeOfBoard = readLine().toInt
        val board = matchfield.copy(new MatchField(sizeOfBoard, sizeOfBoard, false).fields)
        board
      case "g" => game(matchfield)
      case "h" => println(legend())
        matchfield
      case _ => println("wrong input!\nenter \"h\" for more informations!\n")
        matchfield
      }

  }

  def game(matchField: MatchField): MatchField = {
    var board = matchField
    val size = matchField.fields.matrixSize
    val list = CharacterList(size)

    println("Welcome to Stratego!")

    print("Name of first PLAYER: ")

    val playerA = Player(readLine(), list.getCharacterList())

    print("Name of second PLAYER: ")

    val playerB = Player(readLine(), list.getCharacterList())

    println(board)

    val game = Game(playerA, playerB, size, board)

    //while()

    //board = game.create()
    board = game.init()

    board = game.moveDown(board, 0,0)
     /*board = game.moveUp(board, 3,0)
     board = game.moveUp(board, 3,3)
     board = game.moveLeft(board,2 ,3)
     board = game.moveRight(board, 0, 3)*/

    print(board)
    matchField
  }

}
