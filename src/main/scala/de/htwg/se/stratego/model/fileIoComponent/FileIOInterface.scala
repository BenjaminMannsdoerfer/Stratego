package de.htwg.se.stratego.model.fileIoComponent

import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface

trait FileIOInterface {
  def load: MatchFieldInterface
  def save(matchField: MatchFieldInterface): Unit
}
