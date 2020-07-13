package de.htwg.se.stratego.aview.gui

import java.awt.{BorderLayout, Color, Font}

import scala.swing.{Color, _}
import scala.swing.event._
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameFinished, GameStatus, NewGame, PlayerSwitch}
import javax.swing.{BorderFactory, JOptionPane, SwingConstants}
import javax.swing.border.{Border, LineBorder}


class GameFrame(controller:ControllerInterface) extends Frame{

  listenTo(controller)

  title = "Stratego"
  resizable= false

  val matchFieldSize = controller.getSize
  var optionAttack = false //if set to false -> move, else attack
  var fields = Array.ofDim[FieldPanel](matchFieldSize, matchFieldSize)

  var gameStatus: GameStatus = IDLE

  def statusString:String = GameStatus.getMessage(gameStatus)

  def matchfieldPanel = new GridPanel(matchFieldSize,matchFieldSize){
    background = new Color(37,138,73)

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

  val defaultFont = new Font("Calibri", Font.BOLD, 30)
  val defaultColor = new Color(143,138,126)
  val defaultBorder = new LineBorder(java.awt.Color.WHITE,1)

  val upButton = new Button{
    text = "\u2191"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
    border = defaultBorder
  }
  val downButton = new Button{
    text = "\u2193"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
    border = defaultBorder
  }
  val rightButton = new Button{
    text = "\u2192"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
    border = defaultBorder
  }
  val leftButton = new Button{
    text = "\u2190"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
    border = defaultBorder
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

  def lrPanel = new GridPanel(1,2){
    contents += leftButton
    contents += rightButton
  }

  def directionsPanel = new GridPanel(3,1){
    background = new Color(113,126,138)
    contents += upButton
    contents += lrPanel
    contents += downButton

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


  val grColor = new Color(79,76,70)

  val moveButton = new RadioButton{
    text = "move"
    selected = true
    font = defaultFont
    foreground = grColor
    verticalAlignment=Alignment.Center
  }

  val attackButton = new RadioButton{
    text = "attack"
    font = defaultFont
    foreground = grColor
    verticalAlignment=Alignment.Center
  }

  val radioButtons = List(moveButton, attackButton)
  val radioPanel = new GridPanel(2,1) {
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
    xLayoutAlignment= 20
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
    border = BorderFactory.createEmptyBorder(20,20,20,20)
  }

  contents = mainPanel

  visible = true
  redraw

  menuBar = new MenuBar {
    //preferredSize = new Dimension(100, 40)
    foreground = new Color(73,82,89)

    contents += new Menu("File") {
      foreground = new Color(73,82,89)
      mnemonic = Key.F
      contents += new MenuItem(Action("New Game") {
        foreground = new Color(73,82,89)
        controller.createEmptyMatchfield(matchFieldSize)
      })
      contents += new MenuItem(Action("Quit") {
        foreground = new Color(73,82,89)
        System.exit(0)
      })
    }
    contents += new Menu("Edit"){
      foreground = new Color(73,82,89)
        mnemonic = Key.E
        contents+= new MenuItem(Action("Undo") {
          foreground = new Color(73,82,89)
          controller.undo
          redraw
        })
        contents += new MenuItem(Action("Redo") {
          foreground = new Color(73,82,89)
          controller.redo
          redraw
          foreground = new Color(73,82,89)
        })
    }
    contents += new Menu("Game"){
      foreground = new Color(73,82,89)
      mnemonic = Key.E
      contents+= new MenuItem(Action("Save") {
        foreground = new Color(73,82,89)
        controller.save
        redraw
      })
      contents += new MenuItem(Action("Load") {
        foreground = new Color(73,82,89)
        controller.load
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
      deafTo(controller)
      close()
      new PlayerFrame(controller)
    case event: NewGame          =>
      deafTo(controller)
      close()
      new PlayerFrame(controller)
    case event: PlayerSwitch => redraw
  }


  pack()
  //size = new Dimension(800, 600)

}
