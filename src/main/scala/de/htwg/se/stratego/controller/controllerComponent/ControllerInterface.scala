package de.htwg.se.stratego.controller.controllerComponent

import scala.swing.Publisher

trait ControllerInterface extends Publisher{
  def handle(input:String):String
  def welcome:String
  def setPlayers(input:String):String
  def createEmptyMatchfield(size:Int):String
  def initMatchfield:String
  def attack(rowA:Int, colA:Int, rowD:Int, colD:Int): String
  def set(row:Int, col:Int, charac:String):String
  def move(dir:Char, row:Int, col:Int):String
  def matchFieldToString:String
  def undo:String
  def redo:String
  def nextState:Unit
  def statusString:String
  def nextPlayer:Int

}

import scala.swing.event.Event

class FieldChanged extends Event
class PlayerChanged extends Event
class MachtfieldInitialized extends Event
case class MatchFieldSizeChanged(newSize: Int) extends Event


