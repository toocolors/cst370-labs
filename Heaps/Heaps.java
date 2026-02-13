/*
 * INSTRUCTION:
 *     This is a Java staring code for hw5_1.
 *     When you finish the development, download this file and and submit to Canvas
 *     according to the submission instructions.

 *     Please DO NOT change the name of the main class "Main"
 */

// Finish the head comment with Abstract, Name, and Date.
/*
 * Abstract: Takes a series of number and sorts them into a heap, then takes
 *  extra operations to manipulate the heap.
 * Name: Noah deFer
 * Date: 2/10/2026
 */
import java.util.Scanner;
import java.util.regex.Pattern;
 
class Main 
{

    // Global variables
    static int[] heap;
    static int heapSize = 0;

    public static void main(String[] args) {
        // Initialize heap array
        heap = new int[5];

        // Print Welcome + Help
        displayHelp();
        
        // Create Scanner Object
        Scanner scanner = new Scanner(System.in);

        // Run commands


        // Close Scanner Object
        scanner.close();
    }
    
    /**
     * Removes the first/largest element of the heap array and resorts it.
     */
    private static void deleteMax() {
        // Swap max element and last element, delete max element
        heap[1] = heap[heapSize];
        heap[heapSize] = 0;
        
        // Update heapSize
        heapSize--;

        // Sort heap
        sortHeap();
    }
    
    /** 
     * Prints the contents of the heap array.
    */
    private static void display() {
        for(int i = 1; i <= heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    /**
     * 
     */
    private static void displayHelp() {
        // Introduction
        System.out.println("This program lets the user display and manipulate\nan array representation of a max heap data structure.");
        System.out.println("A max heap is a complete binary tree where the parent node is greater than their child nodes.");
        System.out.println("An array representing a heap has the following characteristics:");
        System.out.println("The left child of a node is at index 2i.");
        System.out.println("The right child of a node is at index 2i + 1.");
        System.out.println("The parent of a node is at index i/2.");

        // Commands
        System.out.println("List of Commands:");
        System.out.println("deleteMax: Deletes the first node in the heap.");
        System.out.println("display: Prints the heap.");
        System.out.println("displayMax: Prints the maximum value of the heap.");
        System.out.println("insert X: Attempts to insert integer X into the heap. Integers must be positive and cannot be repeats.");
        System.out.println("help: Prints program and command information.");
        System.out.println("quit: Exits the program.");
    }

    /**
     * Prints the largest/first element of the heap array.
     */
    private static void displayMax() {
        System.out.println(heap[1]);
    }

    /**
     * Inserts a number into the heap array then sorts it.
     * Increases the size of the heap array if needed.
     */
    private static void insert(int num) {
        // Update heapSize
        heapSize++;

        // Check if heap array is large enough
        if(heapSize >= heap.length) {
            // Create larger array
            int[] newHeap = new int [heap.length * 2];
            
            // Copy heap into new array
            for(int i = 1; i < heap.length; i++) {
                newHeap[i] = heap[i];
            }
            
            // Set heap to new array
            heap = newHeap;
        }

        // Add num to end of heap array
        heap[heapSize] = num;

        // Sort heap array
        sortHeap();
    }

    /**
     * Returns whether the heap array is currently a heap.
     */
    private static boolean isHeap() {
        for(int i = 1; i <= heapSize; i++) {
            // Check if left child is smaller/larger than current node
            if(i * 2 <= heapSize && heap[i * 2] > heap[1]) {
                return false;
            }

            // Check if right child is smaller/larger than current node
            if(i * 2 + 1 <= heapSize && heap[i * 2 + 1] > heap[1]) {
                return false;
            }
            
        }
        return true;
    }

    /**
     * 
     */
    private static void runCommand(String command) {
        // Check if command is insert X
        if(Pattern.matches("^insert [0-9]+$", command)) {
            // Try to process number
            try {
                // Get number from command
                int num = Integer.parseInt(command.substring(7));
                
                // Insert number
                insert(num);
                return;
            } catch (NumberFormatException e) {}
        }

        // Check if command is deleteMax, display, displayMax, or other
        switch(command) {
            case "deleteMax":
                deleteMax();
                break;
            case "display":
                display();
                break;
            case "displayMax":
                displayMax();
                break;
            default:
                System.out.println(command + " is unknown.");
                break;
        }
    }

    /**
     * Sorts the heap array into a heap.
     */
    private static void sortHeap() {
        for(int i = heapSize / 2; i >= 1; i--) {
            int k = i;
            int v = heap[k];
            boolean isHeap = false;

            while(!isHeap && k * 2 <= heapSize) {
                int j = k * 2;
                if(j < heapSize) { // there are two children
                    if(heap[j] < heap[j + 1]) {
                        j++;
                    }
                }
                if (v >= heap[j]) {
                    isHeap = true;
                } else {
                    heap[k] = heap[j];
                    k = j;
                }
            }

            heap[k] = v;
        }
    }
}

