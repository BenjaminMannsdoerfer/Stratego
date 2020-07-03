package de.htwg.se.stratego.model.matchFieldComponent

import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{Colour, Field, GameCharacter, Matrix}

trait MatchFieldInterface {
  def fields: Matrix[Field]
  def addChar(row:Int,col:Int,char:GameCharacter,colour:Colour.FigureCol): MatchFieldInterface
  def removeChar(row:Int,col:Int):MatchFieldInterface
  def legend:String
  def frame(row:Int):String
  def createNewMatchField:MatchFieldInterface
}