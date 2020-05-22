package de.htwg.se.stratego.model


case class MatchField(fields: Matrix[Field]) {

  def this(rowSize: Int, colSize: Int, isSet: Boolean) = this(new Matrix[Field](rowSize, colSize, Field(isSet)))

  def frame(row:Int): String = {
    val plus = "+"
    val line = "-"
    val combine = (plus + line * 5) * row + plus
    return combine
  }

  override def toString:String = {
    val col = fields.matrixSize
    val row = fields.matrixSize
    val n = fields.matrixSize - 1
    val pipe = "|"
    val new_line = "\n"
    var matchField = ""

    matchField += frame(fields.matrixSize) + new_line
    for {
      row <- 0 until row
      col <- 0 until col

    } {
      if (fields.field(row, col).isSet) {
        matchField += "|  X  "
      } else {
        matchField += "|     "
      }
      if (col == n) {
        matchField += pipe + new_line
        matchField += frame(fields.matrixSize) + new_line
      }
    }
    return matchField
  }
}
