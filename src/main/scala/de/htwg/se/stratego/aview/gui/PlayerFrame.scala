package de.htwg.se.stratego.aview.gui

import java.awt.{Color, Dimension, Font}

import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, PlayerChanged}
import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller
import javax.imageio.ImageIO
import javax.swing.border.{Border, LineBorder}
import javax.swing.{BorderFactory, ImageIcon}

import scala.swing.event.{ButtonClicked, WindowClosing}
import scala.swing.{BorderPanel, BoxPanel, Button, Dimension, FlowPanel, Frame, GridPanel, Label, Orientation, RadioButton, Swing, TextField}

class PlayerFrame(controller:ControllerInterface) extends Frame{
  listenTo(controller)
  title = "Stratego"
  resizable= false

  val defaultColor = new Color(143,138,126)
  val defaultFont = new Font("Calibri", Font.BOLD, 30)

  val player1 = new TextField("", 20){
    foreground= defaultColor
    font = defaultFont
    border = BorderFactory.createEmptyBorder(0,10,0,0)
  }
  val player2 = new TextField("", 20){
    foreground= defaultColor
    font = defaultFont
    border = BorderFactory.createEmptyBorder(0,10,0,0)
  }
  //object player1 extends TextField {columns = 5}
  //object player2 extends TextField {columns = 5}

  //object next extends Button("next")

  visible= true

  var optionSet = true
  var gameStatus: GameStatus = IDLE


  val imgFlag = ImageIO.read(getClass.getResource("stratego.png"))
  val flag = new ImageIcon(imgFlag)



  def img = new Label{
    icon = flag
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
    //border = Swing.EmptyBorder(15, 10 , 10, 10)
  }

  val defaultBorder = new LineBorder(java.awt.Color.WHITE,10)

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

  size = new Dimension(800, 600)
}
