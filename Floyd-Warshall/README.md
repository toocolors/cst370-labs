# Floyd-Warshall
The Floyd-Warshall Algorithm, sometimes called Floyd's Algorithm, calculates the shortest paths between all nodes in the graph, including indirect paths.

This program gets graph information from the user or a file, then uses the Floyd-Warshall Algorithm to calculate all shortest-paths, then prints the result.

## How to Run
Upon startup the program will get board data from System.in. 
Let's say that the user enters the following:
```
4
0 -1 3 -1
2 0 -1 -1
-1 7 0 1
6 -1 -1 0
```
The first number is the number of vertices in the graph, and the rest represent whether there is an edge from one node to another (not -1) and its weight if so.

We can also use a command like **java Main < input00.txt** to start the program. In this case, the program will automatically use the text file.