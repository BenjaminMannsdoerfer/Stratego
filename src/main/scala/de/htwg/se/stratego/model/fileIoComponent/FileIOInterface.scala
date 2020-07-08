package de.htwg.se.stratego.model.fileIoComponent

import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface

trait FileIOInterface {
  def load: (MatchFieldInterface, Int)
  def save(matchField: MatchFieldInterface, currentPlayerIndex: Int): Unit
}
