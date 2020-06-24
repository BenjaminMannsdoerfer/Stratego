package de.htwg.se.stratego.aview.gui

import de.htwg.se.stratego.controller.Controller

import scala.swing.event.MouseClicked
import scala.swing.{BoxPanel, Color, Dimension, FlowPanel, Font, Label, Orientation, Swing}

class FieldPanel (row:Int, col: Int, controller: Controller) extends FlowPanel{

  def getFigure(): String ={
    if(controller.matchField.fields.field(row,col).isSet){
      controller.matchField.fields.field(row, col).character.get.figure.name
    }
    else " "

  }

  val figureText = new Label {
    text= getFigure()
    font = new Font("Verdana", 1, 36)
  }

  val field = new BoxPanel(Orientation.Vertical){
    contents += figureText
    preferredSize = new Dimension(51, 51)
    border = Swing.BeveledBorder(Swing.Raised)

    reactions += {
      case MouseClicked(src, pt, mod, clicks, pops) => {
        repaint
      }
    }
  }

  def redraw ={
    contents.clear()
    figureText.text = getFigure()
    contents += field
    repaint
  }

}
