package de.htwg.se.stratego.controller

import java.beans.EventSetDescriptor

import scala.swing.event.Event

class FieldChanged extends Event
class PlayerChanged extends Event
case class MatchFieldSizeChanged(newSize: Int) extends Event

