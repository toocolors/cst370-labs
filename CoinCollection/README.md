# CoinCollection
In the Coin Collection problem, we have a board of x by y cells. Each cell can contain 0 or 1 coins. We also have a robot that starts at cell (1, 1), can only move right or down, and must reach the bottom-right cell with the largest amount of coins possible.

This program takes board data from either the user or a file, and calculates the optimal path for the robot to take.

## How to Run
Upon startup the program will get board data from System.in. 
Let's say that the user enters the following:
```
5 6
0 0 0 0 1 0
0 1 0 1 0 0
0 0 0 1 0 1
0 0 1 0 0 1
1 0 0 0 1 0
```
The first number is the number of rows on the board, the second is the number of columns, and the rest represents whether is a coin (1) or not (0) on each cell.

We can also use a command like **java Main < input00.txt** to start the program. In this case, the program will automatically use the text file.