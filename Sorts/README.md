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

## How to use
The program will give several prompts to the user. The answers are expected to be an integer, y, or n.

## Sample Run 1
```
Enter input size: 
10
Print unsorted array and sort results? (y/n)
y
========== Select Option for Input Numbers ==========
1. Ascending Order
2. Descending Order
3. Random
Choose option:
2
Input: 10 9 8 7 6 5 4 3 2 1 
Run all sorting algorithms (y/n)?
y
Bubble Sort: 1 2 3 4 5 6 7 8 9 10 
Insertion Sort: 1 2 3 4 5 6 7 8 9 10 
Merge Sort: 1 2 3 4 5 6 7 8 9 10 
Radix Sort: 1 2 3 4 5 6 7 8 9 10 
Selection Sort: 1 2 3 4 5 6 7 8 9 10 
Quick Sort: 1 2 3 4 5 6 7 8 9 10 
Quick Sort (Median of Three): 1 2 3 4 5 6 7 8 9 10 
==================== Execution Result ====================
Merge Sort: 0.049 milliseconds
Bubble Sort: 0.023 milliseconds
Quick Sort: 0.019 milliseconds
Radix Sort: 0.019 milliseconds
Quick Sort (Median of Three): 0.018 milliseconds
Selection Sort: 0.018 milliseconds
Insertion Sort: 0.015 milliseconds
==========================================================

========================= Ranking ========================
(1) Insertion Sort
(2) Selection Sort
(3) Quick Sort (Median of Three)
(4) Radix Sort
(5) Quick Sort
(6) Bubble Sort
(7) Merge Sort
==========================================================
```

## Sample Run 2
```
Enter input size: 
99999
========== Select Option for Input Numbers ==========
1. Ascending Order
2. Descending Order
3. Random
Choose option:
3
Run all sorting algorithms (y/n)?
y
==================== Execution Result ====================
Bubble Sort: 17245.441 milliseconds
Selection Sort: 3251.28 milliseconds
Insertion Sort: 762.344 milliseconds
Quick Sort (Median of Three): 65.637 milliseconds
Merge Sort: 36.14 milliseconds
Quick Sort: 31.6 milliseconds
Radix Sort: 11.99 milliseconds
==========================================================

========================= Ranking ========================
(1) Radix Sort
(2) Quick Sort
(3) Merge Sort
(4) Quick Sort (Median of Three)
(5) Insertion Sort
(6) Selection Sort
(7) Bubble Sort
==========================================================
```