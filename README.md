# cst370-labs
This repository is a collection of labs I created during my algorithms class.
Each lab has its own folder and README. The program descriptions are also detailed in this file.

# CoinCollection
In the Coin Collection problem, we have a board of x by y cells. Each cell can contain 0 or 1 coins. We also have a robot that starts at cell (1, 1), can only move right or down, and must reach the bottom-right cell with the largest amount of coins possible.

This program takes board data from either the user or a file, and calculates the optimal path for the robot to take.

# Floyd-Warshall
The Floyd-Warshall Algorithm, sometimes called Floyd's Algorithm, calculates the shortest paths between all nodes in the graph, including indirect paths.

This program gets graph information from the user or a file, then uses the Floyd-Warshall Algorithm to calculate all shortest-paths, then prints the result.

# Heaps
This program was written while studying Heap data structures.
It creates an array that represents a Max Heap, and allows the user to manipulate it through different commands.

A Max Heap array follows these conventions:
- The left child of a node is at index 2i.
- The right child of a node is at index 2i + 1.
- The parent of a node is at index i/2.

# Linear Probing
This program allows the user to manipulate a Hash Table.
A Hash Table is an array that uses hashing to assign indexes to elements.
This program uses a simple modulo operation to assign indexes.
If two elements would have the same index, the table uses Linear Probing to assign the index of the second element.
Linear Probing, or Open Addressing, is where the program searches for the first available index starting at the hash result.

# Sorts
Generates an array based on user input, sorts the array using multiple algorithms, then displays the time spent on each sort algorithm.

Currently supported sorting algorithms:
1. Bubble Sort
2. Insertion Sort
3. Radix Sort
4. Merge Sort
5. Selection Sort
6. Quick Sort
7. Quick Sort (Median of Three)