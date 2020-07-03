package de.htwg.se.stratego.aview.gui

import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, PlayerChanged}
import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller

import scala.swing.event.{ButtonClicked, WindowClosing}
import scala.swing.{BorderPanel, BoxPanel, Button, FlowPanel, Frame, Label, Orientation, RadioButton, Swing, TextField}

class PlayerFrame(controller:ControllerInterface) extends Frame{
  listenTo(controller)
  title = "Stratego"

  object player1 extends TextField {columns = 5}
  object player2 extends TextField {columns = 5}

  object next extends Button("next")

  visible= true

  var optionSet = true
  var gameStatus: GameStatus = IDLE

  contents = new FlowPanel{
    contents += new Label("Set your name Player 1:")
    contents += player1
    contents += new Label("Set your name Player 2:")
    contents += player2
    contents += next
    border = Swing.EmptyBorder(15, 10 , 10, 10)
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
}
