package de.htwg.se.stratego.model

import org.scalatest.{Matchers, WordSpec}

class MatrixSpec extends WordSpec with Matchers {
  "A Matrix" when { "new" should {
    val field = new Field(true)
    val matrix = new Matrix(4,4,field)
    "has a size"  in {
      matrix.matrixSize should be(4)
    }
    "can give us a field"  in {
      matrix.field(0,0) should be(field)
    }



  }}
}
