package de.htwg.se.stratego.aview.gui

import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.controller.controllerComponent.PlayerChanged
import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller

import scala.swing.event.ButtonClicked
import scala.swing.{BorderPanel, BoxPanel, Button, FlowPanel, Frame, Label, Orientation, RadioButton, Swing, TextField}

class PlayerFrame(controller:Controller) extends Frame{
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
        //controller.nextState
      /*if (optionSet) {
        controller.initMatchfield()
        controller.handle("i")
        controller.gameStatus=INIT
      } else {

      }*/

  }
  reactions += {
    case event: PlayerChanged     =>
      //controller.nextState
      //new SwingGui(controller)
      new SetFrame(controller)
      visible = false
      dispose()
  }



  /*val initButton = new RadioButton{
    text = "automatically"
    selected = true
  }

  val setButton = new RadioButton{
    text = "manually"
  }

  val radioButtons = List(initButton, setButton)
  val radioPanel = new BoxPanel(Orientation.Vertical) {
    contents ++= radioButtons
    listenTo(initButton)
    reactions += {
      case ButtonClicked(`initButton`) =>
        setButton.selected = false
        optionSet=true
    }
    listenTo(setButton)
    reactions += {
      case ButtonClicked(`setButton`) =>
        initButton.selected = false
        optionSet=false
    }
  }
  val playerPanel = new BorderPanel{
    add(setPanel, BorderPanel.Position.North)
    add(radioPanel, BorderPanel.Position.South)
  }

  contents = playerPanel*/
}
