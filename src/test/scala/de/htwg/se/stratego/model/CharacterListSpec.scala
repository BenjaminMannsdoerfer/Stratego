package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class CharacterListSpec extends WordSpec with Matchers {
  "A CharacterList" when { "new" should {
    val characterlist = CharacterList(4)
    "have a list"  in {
      characterlist.characterList should be(Seq(Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Captain),
        Character(Figure.Flag)))
    }

  }}
}
