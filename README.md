# cst370-labs
This repository is a collection of labs I created during my algorithms class.
Each lab has its own folder and README. The program descriptions are also detailed in this file.

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
