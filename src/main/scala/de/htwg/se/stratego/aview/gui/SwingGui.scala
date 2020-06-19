package de.htwg.se.stratego.aview.gui

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.stratego.controller._
import de.htwg.se.stratego.controller.Controller
import javax.imageio.ImageIO
import javax.swing.ImageIcon

class SwingGui(controller:Controller) extends Frame{
  listenTo(controller)

  title = "STRATEGO"
  val matchFieldSize = controller.matchField.fields.matrixSize


  val label =new Label{ icon= new ImageIcon("C:\\Users\\walte\\OneDrive\\Dokumente\\Sonstiges\\Stratego\\src\\main\\scala\\de\\htwg\\se\\stratego\\aview\\gui\\matchfield.JPG")}

  iconImage = toolkit.getImage("C:\\Users\\walte\\OneDrive\\Dokumente\\Sonstiges\\Stratego\\src\\main\\scala\\de\\htwg\\se\\stratego\\aview\\gui\\matchfield.JPG")

  val img = toolkit.getImage("matchfield.JPG")//ImageIO.read("matchfield.JPG")

  val panel = new Panel{
    override def paint(g:Graphics2D): Unit ={
      g.drawImage(img,100,100,null)
    }
    preferredSize = new Dimension(500,500)

  }

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
  }


  visible = true

}
