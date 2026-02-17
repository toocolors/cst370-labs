# Linear Probing
This program allows the user to manipulate a Hash Table.
A Hash Table is an array that uses hashing to assign indexes to elements.
This program uses a simple modulo operation to assign indexes.
If two elements would have the same index, the table uses Linear Probing to assign the index of the second element.
Linear Probing, or Open Addressing, is where the program searches for the first available index starting at the hash result.

Commands:
- displayStatus X: Prints the value of index X.
- displayTable: Prints the values of each table index.
- getLoad: Prints the current Load Factor.
- getMaxLoad: Prints the current Maximum Load Factor.
- insert X: Inserts integer X into the hash table.
- newMaxLoad X: Sets the Maximum Load Factor to X.
- search X: Search for the location of element X in the hash table.
- tableSize: Prints the current table size.
- info: Print program information.
- help: Print command information.
- quit: Exit the program.
