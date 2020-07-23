package de.htwg.se.stratego.aview.gui

import java.awt.{Color, Font}

import scala.swing._
import scala.swing.event._
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameStatus, MachtfieldInitialized, NewGame}
import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import javax.imageio.ImageIO
import javax.swing.{BorderFactory, WindowConstants}
import javax.swing.border.LineBorder

class SetFrame(controller:ControllerInterface) extends Frame {

  listenTo(controller)

  title = "Stratego"
  peer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  resizable= false
  //peer.setLocationRelativeTo(null)
  visible=true


  val matchFieldSize = controller.getSize
  var fields = Array.ofDim[FieldPanel](matchFieldSize, matchFieldSize)
  var gameStatus: GameStatus = IDLE
  def statusString:String = GameStatus.getMessage(gameStatus)
  val iconImg = ImageIO.read(getClass.getResource("iconS.png"))

  iconImage = iconImg


  val defaultFont = new Font("Calibri", Font.BOLD, 30)
  val defaultColor = new Color(143,138,126)
  val defaultBorder = new LineBorder(java.awt.Color.WHITE,1)


  def matchfieldPanel = new GridPanel(matchFieldSize,matchFieldSize){
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

  val initializeButton = new Button{
    text = "set characters automatically"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
  }

  listenTo(initializeButton)
  reactions += {
    case ButtonClicked(`initializeButton`) =>
      controller.handle("i")
  }

  val status = new TextField(controller.statusString, 20)

  def statusPanel = new BorderPanel {
    add(status, BorderPanel.Position.Center)
  }

  val buttonPanel = new BorderPanel {
    add(initializeButton, BorderPanel.Position.Center)
    border = BorderFactory.createEmptyBorder(10,0,10,0)
  }

  val mainPanel = new BorderPanel{

    add(matchfieldPanel, BorderPanel.Position.North)
    add(buttonPanel, BorderPanel.Position.Center)
    add(statusPanel, BorderPanel.Position.South)

    border = BorderFactory.createEmptyBorder(20,20,20,20)
  }

  mainPanel.requestFocus()
  contents = mainPanel

  visible = true
  redraw

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New Game") {
        controller.createEmptyMatchfield(matchFieldSize)
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
  }

  def redraw: Unit = {
    for {
      row <- 0 until matchFieldSize
      column <- 0 until matchFieldSize
    } fields(row)(column).redraw
    status.text = controller.statusString
    repaint
  }

  reactions += {
    case event: FieldChanged     => redraw
    case event: MachtfieldInitialized =>
      visible = false
      deafTo(controller)
      close()
      new GameFrame(controller)
    case event: NewGame =>
      deafTo(controller)
      close()
      new PlayerFrame(controller)
  }
}
