package de.htwg.se.stratego.aview.gui

import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller

import scala.swing.FlowPanel.Alignment
import scala.swing.Swing.LineBorder
import scala.swing.event.{ButtonClicked, MouseClicked}
import scala.swing.{BorderPanel, BoxPanel, Button, Color, Dimension, FlowPanel, Font, Label, Orientation, Swing}

class FieldPanel (row:Int, col: Int, controller: Controller) extends FlowPanel {

  var fieldText = " "
  var isClicked = false
  val r = row
  val c = col

  def fieldText(row:Int, col:Int): String ={
    if(controller.matchField.fields.field(row,col).isSet){
      fieldText= controller.matchField.fields.field(row, col).character.get.figure.name
      fieldText
    }
    else " "

  }
  border = LineBorder(java.awt.Color.BLACK,2)

  val figureText = new Button{
    text = fieldText(row,col)
    font = new Font("Verdana", 1, 20)
    preferredSize = new Dimension(51, 51)
  }

  val field = new FlowPanel(){
    contents += figureText

    listenTo(figureText)
    reactions += {
      case ButtonClicked(`figureText`) =>
        isClicked = true
    }
  }

  contents += field

  def redraw ={
    contents.clear()
    figureText.text = fieldText(row,col)
    contents += field
    repaint
  }

}
