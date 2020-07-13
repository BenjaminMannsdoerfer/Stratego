package de.htwg.se.stratego.aview.gui

import java.awt.{Color, Font}
import scala.swing.event.{ButtonClicked, Key, KeyPressed, MouseClicked}
import scala.swing.{BorderPanel, BoxPanel, Button, Color, Dimension, FlowPanel, Font, Label, Orientation, Swing}
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameFinished, GameStatus, NewGame, PlayerSwitch}

class FieldPanel (row:Int, col: Int, controller: ControllerInterface) extends FlowPanel {

  var fieldText = " "
  var isClicked = false
  val r = row
  val c = col

  background = new Color(37,138,73)

  def fieldText(row:Int, col:Int): String ={
    if(controller.getField.field(row,col).isSet){
      if(controller.getField.field(row,col).character.get.figure.name.equals("F")){
        fieldText="\uD83C\uDFF3"
      }else if(controller.getField.field(row,col).character.get.figure.name.equals("B")){
        fieldText="\uD83D\uDCA3"
      }else if(controller.getField.field(row,col).character.get.figure.name.equals("M")){
        fieldText="\uD83D\uDC82"
      }else{
        fieldText= controller.getField.field(row, col).character.get.figure.name
      }
      fieldText
    }
    else " "
  }


  val figureText = new Button{
    text = fieldText(row,col)
    font = font.deriveFont(1, 16)
    foreground = Color.WHITE
    preferredSize = new Dimension(51, 51)

    if(controller.getField.field(row,col).isSet) {
      if (controller.getField.field(row, col).colour.get.value == 0) {
        background = new Color(37,39,138)
      } else if (controller.getField.field(row, col).colour.get.value == 1) {
        background = new Color(138,41,37)
      }
      }else{
        background = new Color(37,138,73)
    }

    listenTo(keys)
    reactions += {
      case KeyPressed(_, Key.B, _, _) =>
        controller.handle("s" + r + c + "B")
      case KeyPressed(_, Key.M, _, _) =>
        controller.handle("s" + r + c + "M")
      case KeyPressed(_, Key.Key9, _, _) =>
        controller.handle("s" + r + c + "9")
      case KeyPressed(_, Key.Key8, _, _) =>
        controller.handle("s" + r + c + "8")
      case KeyPressed(_, Key.Key7, _, _) =>
        controller.handle("s" + r + c + "7")
      case KeyPressed(_, Key.Key6, _, _) =>
        controller.handle("s" + r + c + "6")
      case KeyPressed(_, Key.Key5, _, _) =>
        controller.handle("s" + r + c + "5")
      case KeyPressed(_, Key.Key4, _, _) =>
        controller.handle("s" + r + c + "4")
      case KeyPressed(_, Key.Key3, _, _) =>
        controller.handle("s" + r + c + "3")
      case KeyPressed(_, Key.Key2, _, _) =>
        controller.handle("s" + r + c + "2")
      case KeyPressed(_, Key.Key1, _, _) =>
        controller.handle("s" + r + c + "1")
      case KeyPressed(_, Key.F, _, _) =>
        controller.handle("s" + r + c + "F")
    }
  }

  val field = new FlowPanel(){
    contents += figureText
    background = new Color(37,138,73)

    listenTo(figureText)
    reactions += {
      case ButtonClicked(`figureText`) =>
        isClicked = true
    }
  }

  contents += field

  def redraw ={
    contents.clear()

    if(controller.getField.field(row,col).isSet){
      if(controller.getField.field(row, col).colour.get.value == controller.currentPlayerIndex){
        figureText.text=fieldText(row,col)
      } else{
        figureText.text=""
        figureText.icon = null
      }
    }else{
      figureText.text=""
    }

    if(controller.getField.field(row,col).isSet) {
      if (controller.getField.field(row, col).colour.get.value == 0) {
        figureText.background = new Color(37,39,138)
      } else if (controller.getField.field(row, col).colour.get.value == 1) {
        figureText.background = new Color(138,41,37)
      }
    }else{
      figureText.background = new Color(138,124,65)
    }
    contents += field
    repaint
  }
}
