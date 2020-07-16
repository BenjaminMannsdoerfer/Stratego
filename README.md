![stratego](https://user-images.githubusercontent.com/64639703/87684761-e9c68980-c782-11ea-91d6-d04b58b7a369.png)


A little info about our project STRATEGO for our class Software Engineering at HTWG Konstanz.

## Build Status

Build status of continus integration with travis

[![Build Status](https://travis-ci.com/BenjaminMannsdoerfer/Stratego.svg?branch=master)](https://travis-ci.com/BenjaminMannsdoerfer/Stratego)


## Code Status

with coveralls.io

[![Coverage Status](https://coveralls.io/repos/github/BenjaminMannsdoerfer/Stratego/badge.svg?branch=master)](https://coveralls.io/github/BenjaminMannsdoerfer/Stratego?branch=master)


## Gameplay

Stratego is a strategy board game for two players. Each player has 40 pieces representing different characters.

We modified the game a little bit so you can walk with all characters (except flag and bomb) just one field up, down, to the right or to the left.
You also can attack with your figures the oppenents. To win the game you have to find and attack the flag of your enemy.

The figures are ranked from highest to lowest as follows:

1.  Bomb        (💣)
2.  Marshal     (💂)
3.  General     (9)
4.  Colonel     (8)
5.  Major       (7)
6.  Captain     (6)
7.  Lieutenant  (5)
8.  Sergeant    (4)
9.  Miner       (3)
10. Scout       (2)
11. Spy         (1)
12. Flag        (🏳️)

Important rules:

- Flag is the most important figure
- Miner and Bomb: only the miner can defuse the Bomb
- Every other figure loses against the Bomb
- Spy and Marshal: only Spy can beat the Marshal except the bomb
- Flag and Bomb can’t move  


## Graphical UI

You can see screenshots of the GUI below:

![game_test1](https://user-images.githubusercontent.com/64639703/87685319-8b4ddb00-c783-11ea-884c-0fb8eb3b158d.PNG)
![game_2](https://user-images.githubusercontent.com/64639703/87685947-3e1e3900-c784-11ea-9caa-65b27421e755.PNG)





