package de.htwg.se.stratego.model
import org.scalatest.{Matchers, WordSpec}

class CharacterListSpec extends WordSpec with Matchers {
  "A CharacterList" when { "new with size 4" should {
    val characterlist = CharacterList(4)
    "have a list"  in {
      characterlist.characterList should be(Seq(Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Captain),
        Character(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Captain),
        Character(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (4)
    }

  }}
  "A CharacterList" when { "new with size 5" should {
    val characterlist = CharacterList(5)
    "have a list"  in {
      characterlist.characterList should be(Seq(Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (5)
    }


  }}
  "A CharacterList" when { "new with size 6" should {
    val characterlist = CharacterList(6)
    "have a list"  in {
      characterlist.characterList should be(Seq(Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (6)
    }


  }}
  "A CharacterList" when { "new with size 7" should {
    val characterlist = CharacterList(7)
    "have a list"  in {
      characterlist.characterList should be(Seq(Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (7)
    }

  }}
  "A CharacterList" when { "new with size 8" should {
    val characterlist = CharacterList(8)
    "have a list"  in {
      characterlist.characterList should be(Seq(Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (8)
    }

  }}
  "A CharacterList" when { "new with size 9" should {
    val characterlist = CharacterList(9)
    "have a list"  in {
      characterlist.characterList should be(Seq(Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (9)
    }

  }}
  "A CharacterList" when { "new with size 10" should {
    val characterlist = CharacterList(10)
    "have a list"  in {
      characterlist.characterList should be(Seq(Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Major),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Bomb),
        Character(Figure.Marshal),
        Character(Figure.General),
        Character(Figure.Colonel),
        Character(Figure.Colonel),
        Character(Figure.Major),
        Character(Figure.Major),
        Character(Figure.Major),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Captain),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Lieutenant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Sergeant),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Miner),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Scout),
        Character(Figure.Spy),
        Character(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (10)
    }

  }}
}
