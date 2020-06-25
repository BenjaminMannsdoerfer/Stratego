package de.htwg.se.stratego.aview.gui

import java.awt.image.BufferedImage

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.stratego.controller._
import de.htwg.se.stratego.controller.Controller
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JOptionPane}

class CellClicked(val row:Int, val column: Int) extends Event

class SwingGui(controller:Controller) extends Frame{

  listenTo(controller)

  title = "Stratego"
  val matchFieldSize = controller.matchField.fields.matrixSize

  var fields = Array.ofDim[FieldPanel](matchFieldSize, matchFieldSize)

  controller.initMatchfield()

  def matchfieldPanel = new GridPanel(matchFieldSize,matchFieldSize){
    border = LineBorder(java.awt.Color.ORANGE, 2)
    background = java.awt.Color.GRAY

    for{
      row <- 0 until matchFieldSize
      col <- 0 until matchFieldSize
    }{
      val fieldPanel = new FieldPanel(row, col, controller)
      fields(row)(col) = fieldPanel
      contents += fieldPanel
      listenTo(fieldPanel)
    }
  }


  contents = matchfieldPanel
  /*
  contents = new BorderPanel{
    add(matchfieldPanel, BorderPanel.Position.Center)
  }

   */
  visible = true
  redraw

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New Game") {
        controller.createEmptyMatchfield(matchFieldSize)
        val playerFrame = new PlayerFrame(controller)
        visible = false
        dispose()
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
    contents += new Menu("Edit"){
        mnemonic = Key.E
        contents+= new MenuItem(Action("Undo") {
          controller.undo
          redraw
        })
        contents += new MenuItem(Action("Redo") {
          controller.redo
          redraw
        })
    }
  }

  def redraw = {
    for {
      row <- 0 until matchFieldSize
      column <- 0 until matchFieldSize
    } fields(row)(column).redraw

    repaint
  }

  reactions += {
    case event: CellChanged     => redraw
  }


  size = new Dimension(800, 600)

}
