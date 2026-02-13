#Heaps
This program was written while studying Heap data structures.
It creates an array that represents a Max Heap, and allows the user to manipulate it through different commands.

The user can use the following commands:
- deleteMax: Deletes the first node in the heap.
- display: Prints the heap.
- displayMax: Prints the maximum value of the heap.
- insert X: Attempts to insert integer X into the heap. Integers must be positive and cannot be repeats.
- help: Prints command information.
- info: Prints program information
- quit: Exits the program.

A Max Heap array follows these conventions:
- The left child of a node is at index 2i.
- The right child of a node is at index 2i + 1.
- The parent of a node is at index i/2.