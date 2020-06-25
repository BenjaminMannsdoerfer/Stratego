package de.htwg.se.stratego.aview.gui

import de.htwg.se.stratego.controller.Controller

import scala.swing.FlowPanel.Alignment
import scala.swing.Swing.LineBorder
import scala.swing.event.{ButtonClicked, MouseClicked}
import scala.swing.{BoxPanel, Button, Color, Dimension, FlowPanel, Font, Label, Orientation, Swing}

class FieldPanel (row:Int, col: Int, controller: Controller) extends FlowPanel{

  var fieldText = " "

  def fieldText(row:Int, col:Int): String ={
    if(controller.matchField.fields.field(row,col).isSet){
      fieldText= controller.matchField.fields.field(row, col).character.get.figure.name
      fieldText
    }
    else " "

  }

  border = LineBorder(java.awt.Color.BLACK,2)

  val figureText = new Label{
    text = fieldText(row,col)
    font = new Font("Verdana", 1, 20)
  }

  val upButton = new Button{
    text = "\u2191"
  }
  val downButton = new Button{
    text = "\u2193"
  }
  val rightButton = new Button{
    text = "\u2192"
  }
  val leftButton = new Button{
    text = "\2190"
  }

  /*
  val figureText = new Label {
    text= fieldText
    font = new Font("Verdana", 1, 36)
  }

   */




  val field = new BoxPanel(Orientation.Vertical){
    contents += upButton

    contents += figureText
    contents += downButton
    //preferredSize = new Dimension(51, 51)
    //border = Swing.BeveledBorder(Swing.Raised)


    listenTo(upButton)
    reactions += {
      case ButtonClicked(`upButton`) =>
        controller.move('u', row, col)
        repaint
    }
    listenTo(downButton)
    reactions += {
      case ButtonClicked(`downButton`) =>
        controller.move('d', row, col)
        repaint
    }


  }


  contents += leftButton
  listenTo(leftButton)
  reactions += {
    case ButtonClicked(`leftButton`) =>
      controller.move('l', row, col)
      repaint
  }

  contents += field

  contents += rightButton
  listenTo(rightButton)
  reactions += {
    case ButtonClicked(`rightButton`) =>
      controller.move('r', row, col)
      repaint
  }


  def redraw ={
    contents.clear()
    figureText.text = fieldText(row,col)
    contents += field
    repaint
  }

}
