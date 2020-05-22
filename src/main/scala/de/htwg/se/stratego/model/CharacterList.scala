package de.htwg.se.stratego.model

case class CharacterList(size: Int){
  var characterList = List[Character]()

  size match {
    case 10 =>
      characterList ++= List(Character(Figure.Bomb),
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
        Character(Figure.Flag))

    case 9 => characterList ++= List(Character(Figure.Bomb),
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
      Character(Figure.Flag))

    case 8 => characterList ++= List(Character(Figure.Bomb),
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
      Character(Figure.Flag))

    case 7 => characterList ++= List(Character(Figure.Bomb),
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
      Character(Figure.Flag))

    case 6 => characterList ++= List(Character(Figure.Bomb),
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
      Character(Figure.Flag))

    case 5 => characterList ++= List(Character(Figure.General),
      Character(Figure.Colonel),
      Character(Figure.Major),
      Character(Figure.Captain),
      Character(Figure.Flag))

    case 4 => characterList ++= List(Character(Figure.General),
      Character(Figure.Colonel),
      Character(Figure.Captain),
      Character(Figure.Flag))

  }

  def getCharacterList(): List[Character] = {
    return characterList
  }

}

