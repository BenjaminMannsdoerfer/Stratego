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


  //controller.initMatchfield()

  def enterNamesDialog(){
    Dialog.showMessage(null,"WELCOME TO STRATEGO!", title = "Stratego")
    controller.setPlayers(JOptionPane.showInputDialog(null, "Spieler 1 Name:", JOptionPane.DEFAULT_OPTION) +
    " " + JOptionPane.showInputDialog(null,"Spieler 2 Name:",title="Stratego"))
    Dialog.showMessage(null, "Hello " + controller.playerList(0) + " and " + controller.playerList(1))
  }
  enterNamesDialog()

  val matchfieldPanel = new GridPanel(matchFieldSize,matchFieldSize){
    border = LineBorder(java.awt.Color.ORANGE, 2)
    background = java.awt.Color.GRAY
    for{
      row <- 0 until matchFieldSize
      col <- 0 until matchFieldSize
    }{
      contents += new FlowPanel(){
        border = LineBorder(java.awt.Color.BLACK,2)
        contents += new Label{
          text = if(controller.matchField.fields.field(row, col).isSet) controller.matchField.fields.field(row,col).character.get.figure.name else " "
          font = new Font("Verdana", 1, 36)
        }
      }
    }

  }
  /*
    contents+= new Label {
      icon = new ImageIcon("C:\\Users\\walte\\OneDrive\\Dokumente\\Sonstiges\\Stratego\\src\\main\\scala\\de\\htwg\\se\\stratego\\aview\\gui\\pikachu-figur.jpg")
    }
     */


    menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        controller.createEmptyMatchfield(matchFieldSize)
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
      contents += new Menu("Edit"){
        mnemonic = Key.E
        contents+= new MenuItem(Action("Undo") { controller.undo })
        contents += new MenuItem(Action("Redo") { controller.redo })
      }
  }

  matchfieldPanel.visible=true

  contents_=(matchfieldPanel)



  visible = true

}
