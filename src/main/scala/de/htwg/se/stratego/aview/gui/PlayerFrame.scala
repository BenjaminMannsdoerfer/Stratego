package de.htwg.se.stratego.aview.gui

import java.awt.Dimension

import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, PlayerChanged}
import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller
import javax.swing.BorderFactory

import scala.swing.event.{ButtonClicked, WindowClosing}
import scala.swing.{BorderPanel, BoxPanel, Button, Dimension, FlowPanel, Frame, GridPanel, Label, Orientation, RadioButton, Swing, TextField}

class PlayerFrame(controller:ControllerInterface) extends Frame{
  listenTo(controller)
  title = "Stratego"
  resizable= false


  val player1 = new TextField("", 20)
  val player2 = new TextField("", 20)
  //object player1 extends TextField {columns = 5}
  //object player2 extends TextField {columns = 5}

  val next = new Button("next")
  //object next extends Button("next")

  visible= true

  var optionSet = true
  var gameStatus: GameStatus = IDLE


  def welcomePanel = new GridPanel(1,1) {
    contents += new Label("Welcome to Stratego!")
  }

  def setPanel = new GridPanel(2,2){
    contents += new Label("Set your name Player 1:")
    contents += player1
    vGap = 10
    contents += new Label("Set your name Player 2:")
    contents += player2
    border = Swing.EmptyBorder(15, 10 , 10, 10)
  }

  def buttonPanel = new GridPanel(1,1) {
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

  val mainPanel = new BorderPanel {
    add(welcomePanel,BorderPanel.Position.North)
    add(setPanel,BorderPanel.Position.Center)
    add(buttonPanel,BorderPanel.Position.South)
  }

  contents = mainPanel

  size = new Dimension(800, 600)
}
