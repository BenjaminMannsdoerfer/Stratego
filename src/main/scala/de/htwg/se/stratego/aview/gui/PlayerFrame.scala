package de.htwg.se.stratego.aview.gui

import de.htwg.se.stratego.controller.Controller

import scala.swing.event.ButtonClicked
import scala.swing.{Button, FlowPanel, Frame, Label, Swing, TextField}

class PlayerFrame(controller:Controller) extends Frame{
  listenTo(controller)
  title = "Stratego"

  object player1 extends TextField {columns = 5}
  object player2 extends TextField {columns = 5}

  object next extends Button("next")

  visible= true

  contents = new FlowPanel{
    contents += new Label("Player 1:")
    contents += player1
    contents += new Label("Player 2:")
    contents += player2
    contents += next
    border = Swing.EmptyBorder(15, 10 , 10, 10)
  }
  listenTo(next)
  reactions += {
    case ButtonClicked(`next`) =>
      listenTo(controller)
      controller.handle(player1.text+ " "+ player2.text)
      val mainFrame = new SwingGui(controller)
      visible = false
      dispose()
  }


}
