package de.htwg.se.stratego.model


case class MatchField(fields: Matrix[Field]) {

  def this(rowSize: Int, colSize: Int, isSet: Boolean) = this(new Matrix[Field](rowSize, colSize, Field(isSet)))

  override def toString(): String = {
    var matchField = ""

    val col = fields.matrixSize
    val row = fields.matrixSize

    val frame = "+-----+" * row

    for {
      row <- 0 until row
      col <- 0 until col

    } {

      if (col == 0){
        matchField += "\n" + frame + "\n"
      }
      if (fields.field(row, col).isSet) {
        matchField += "|  X  |"
      } else {
        matchField += "|     |"
      }
    }
    matchField += "\n" + frame //End Frame

    return matchField
  }
}
