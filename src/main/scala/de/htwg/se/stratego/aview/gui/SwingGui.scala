package de.htwg.se.stratego.aview.gui

import scala.swing._
import scala.swing.event._
import de.htwg.se.stratego.controller._
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameFinished, GameStatus, NewGame, PlayerSwitch}
import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller
import javax.swing.JOptionPane

//class CellClicked(val row:Int, val column: Int) extends Event

class SwingGui(controller:ControllerInterface) extends Frame{

  listenTo(controller)

  title = "Stratego"
  val matchFieldSize = controller.getSize
  var optionAttack = false //if set to false -> move, else attack

  var fields = Array.ofDim[FieldPanel](matchFieldSize, matchFieldSize)

  //controller.initMatchfield()
  //controller.handle("i")
  var gameStatus: GameStatus = IDLE
  //controller.gameStatus=INIT

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

  val upButton = new Button{
    text = "\u2191"
  }
  val downButton = new Button{
    text = "\u2193"
  }
  val rightButton = new Button{
    text = "\u2192"
  }
  val leftButton = new Button{
    text = "\u2190"
  }

  def attackOrMove(direction: String, rowD:Int, colD:Int):Unit = {
    fields.foreach(r => for(c<- r){
      if(c.isClicked) {
        if(optionAttack){
          controller.handle("a"+(c.r).toString+(c.c).toString+(c.r+rowD).toString+(c.c+colD).toString)
          gameStatus=ATTACK
          c.isClicked=false
          repaint
        }else{
          controller.handle("m" + direction + c.r.toString+ c.c.toString)
          c.isClicked= false
          repaint
        }
      }
    })
  }

  def directionsPanel = new BorderPanel{

    add(upButton, BorderPanel.Position.North)
    add(downButton, BorderPanel.Position.South)
    add(rightButton, BorderPanel.Position.East)
    add(leftButton, BorderPanel.Position.West)


    listenTo(upButton)
    listenTo(downButton)
    listenTo(leftButton)
    listenTo(rightButton)
    reactions += {
      case ButtonClicked(`upButton`) =>
        attackOrMove("u", -1,0)
      case ButtonClicked(`downButton`) =>
        attackOrMove("d",1,0)
      case ButtonClicked(`leftButton`) =>
        attackOrMove("l",0,-1)
      case ButtonClicked(`rightButton`) =>
        attackOrMove("r", 0,1)
    }
  }


  val moveButton = new RadioButton{
    text = "move"
    selected = true
  }

  val attackButton = new RadioButton{
    text = "attack"
  }

  val radioButtons = List(moveButton, attackButton)
  val radioPanel = new BoxPanel(Orientation.Vertical) {
    contents ++= radioButtons
    listenTo(moveButton)
    reactions += {
      case ButtonClicked(`moveButton`) =>
        attackButton.selected = false
        optionAttack=false
    }
    listenTo(attackButton)
    reactions += {
      case ButtonClicked(`attackButton`) =>
        moveButton.selected = false
        optionAttack= true
    }

  }

  val status = new TextField(controller.statusString, 20)

  def optionPanel = new BorderPanel{
    add(radioPanel, BorderPanel.Position.Center)
  }

  def statusPanel = new BorderPanel {
    add(status, BorderPanel.Position.Center)
  }

  def controllPanel = new GridPanel(1,2){
    contents += directionsPanel
    contents += optionPanel
  }

  val mainPanel = new BorderPanel{
    add(matchfieldPanel, BorderPanel.Position.North)
    add(controllPanel, BorderPanel.Position.Center)
    add(statusPanel, BorderPanel.Position.South)
  }

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
    contents += new Menu("Edit"){
        mnemonic = Key.E
        contents+= new MenuItem(Action("Undo") {
          controller.undo
          redraw
        })
        contents += new MenuItem(Action("Redo") {
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
    case event: GameFinished     =>
      JOptionPane.showMessageDialog(null, controller.playerList(controller.currentPlayerIndex) + " you have won the game!")
      visible = false
      dispose()
      close()
      closeOperation()
    case event: NewGame          =>
      this.close()
      new PlayerFrame(controller)
    case event: PlayerSwitch => redraw
  }


  pack()
  //size = new Dimension(800, 600)

}
