package de.htwg.se.stratego.aview.gui

import java.awt.{Color, Dimension, Font}
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, PlayerChanged}
import javax.imageio.ImageIO
import javax.swing.border.{Border, LineBorder}
import javax.swing.{BorderFactory, ImageIcon}
import scala.swing.event.{ButtonClicked, WindowClosing}
import scala.swing.{BorderPanel, BoxPanel, Button, Dimension, FlowPanel, Frame, GridPanel, Label, Orientation, RadioButton, Swing, TextField}

class PlayerFrame(controller:ControllerInterface) extends Frame{

  listenTo(controller)

  title = "Stratego"
  resizable= false
  visible= true


  val strategoImg = ImageIO.read(getClass.getResource("stratego.png"))
  val strategoI = new ImageIcon(strategoImg)
  val defaultColor = new Color(143,138,126)
  val lightG = new Color(192,192,192)
  val lightF = new Font("Calibri", 1, 25)
  val defaultFont = new Font("Calibri", Font.BOLD, 30)
  val defaultBorder = new LineBorder(java.awt.Color.WHITE,10)
  val iconImg = ImageIO.read(getClass.getResource("iconS.png"))

  iconImage = iconImg

  val player1 = new TextField("", 20){
    foreground= lightG
    font = lightF
    border = BorderFactory.createEmptyBorder(0,20,0,0)
  }

  val player2 = new TextField("", 20){
    foreground= lightG
    font = lightF
    border = BorderFactory.createEmptyBorder(0,20,0,0)
  }

  def img = new Label{
    icon = strategoI
  }

  def welcomeString = new Label{
    text = "Welcome to"
    foreground= defaultColor
    font = defaultFont
  }

  def welcomePanel = new FlowPanel() {
    contents += welcomeString
    contents += img
  }

  def setPanel = new GridPanel(2,2){
    contents += new Label {
      text = "Player 1:"
      foreground= defaultColor
      font = defaultFont
    }
    contents += player1
    vGap = 10
    contents += new Label {
      text = "Player 2:"
      foreground= defaultColor
      font = defaultFont
    }
    contents += player2
  }


  val next = new Button{
    text = "Play"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
  }

  def emptyPanel = new FlowPanel

  def buttonPanel = new GridPanel(1,2) {
    border = BorderFactory.createEmptyBorder(30,0,70,0)
    contents += emptyPanel
    contents += next
  }

  listenTo(next)

  reactions += {
    case ButtonClicked(`next`) =>
        listenTo(controller)
        controller.handle(player1.text+ " "+ player2.text)
  }
  reactions += {
    case event: PlayerChanged     =>
      visible = false
      deafTo(controller)
      close()
      new SetFrame(controller)
  }

  val mainPanel = new GridPanel(3,1) {
    contents += img
    vGap = 40
    contents += setPanel
    contents += buttonPanel
    border = BorderFactory.createEmptyBorder(0,0,0,80)
  }

  contents = mainPanel

}
