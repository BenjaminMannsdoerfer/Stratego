package de.htwg.se.stratego.aview.gui

import java.awt
import java.awt.{Color, Font}
import java.awt.event.{KeyEvent, KeyListener}
import java.nio.Buffer

import scala.swing._
import scala.swing.event._
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameStatus, MachtfieldInitialized, NewGame}
import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller

class SetFrame(controller:ControllerInterface) extends Frame {


  listenTo(controller)

  title = "Stratego"
  resizable= false
  val matchFieldSize = controller.getSize

  var fields = Array.ofDim[FieldPanel](matchFieldSize, matchFieldSize)

  var gameStatus: GameStatus = IDLE

  def statusString:String = GameStatus.getMessage(gameStatus)

  def matchfieldPanel = new GridPanel(matchFieldSize,matchFieldSize){
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

  val initializeButton = new Button("automatically")

  listenTo(initializeButton)
  reactions += {
    case ButtonClicked(`initializeButton`) =>
      controller.handle("i")
      //controller.gameStatus=INIT
  }


  val status = new TextField(controller.statusString, 20)


  def statusPanel = new BorderPanel {
    add(status, BorderPanel.Position.Center)
  }

  val buttonPanel = new BorderPanel {
    add(initializeButton, BorderPanel.Position.Center)
  }


  val mainPanel = new BorderPanel{
    add(matchfieldPanel, BorderPanel.Position.North)
    add(buttonPanel, BorderPanel.Position.Center)
    add(statusPanel, BorderPanel.Position.South)
  }

  mainPanel.requestFocus()
  contents = mainPanel

  visible = true
  redraw

  menuBar = new MenuBar {
    preferredSize = new Dimension(100, 40)

    contents += new Menu("File") {
      font = new Font("Verdana", 1, 20)
      foreground = new Color(73,82,89)
      mnemonic = Key.F
      contents += new MenuItem(Action("New Game") {
        font = new Font("Verdana", 1, 10)
        foreground = new Color(73,82,89)
        controller.createEmptyMatchfield(matchFieldSize)
      })
      contents += new MenuItem(Action("Quit") {
        font = new Font("Verdana", 1, 10)
        foreground = new Color(73,82,89)
        System.exit(0)
      })
    }
    contents += new Menu("Edit"){
      font = new Font("Verdana", 1, 20)
      foreground = new Color(73,82,89)
      mnemonic = Key.E
      contents+= new MenuItem(Action("Undo") {
        font = new Font("Verdana", 1, 10)
        foreground = new Color(73,82,89)
        controller.undo
        redraw
      })
      contents += new MenuItem(Action("Redo") {
        font = new Font("Verdana", 1, 10)
        foreground = new Color(73,82,89)
        controller.redo
        redraw
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
  pack()
  //size = new Dimension(800, 600)
}
