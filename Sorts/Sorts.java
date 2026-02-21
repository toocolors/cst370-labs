/*
 * Abstract: Generates an array based on user input, sorts the array using multiple algorithms, then displays the time spent on each sort algorithm.
 * Name: Noah deFer
 * Date: 2/17/2026
 */
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Used to store function for the associated algorithm.
 */
@FunctionalInterface
interface AlgorithmFunction {
    void sort();
}

/**
 * Used to store algorithm information.
 */
class Algorithm {
    // Fields
    String name;
    boolean run;
    long runtime;
    AlgorithmFunction sort;

    // Constructor
    public Algorithm(String name, AlgorithmFunction sort) {
        this.name = name;
        run = false;
        runtime = -1;
        this.sort = sort;
    }
}
 
class Main 
{
    // ***************************************************************
    // GLOBAL VARIABLES
    // ***************************************************************
    // Integers
    static int inputSize;
    // Arrays
    static Algorithm[] algorithms = {
        new Algorithm("Insertion Sort", () -> insertionSort()),
        new Algorithm("Selection Sort", () -> selectionSort()),
        new Algorithm("Quick Sort", () -> quickSort(0, inputSize - 1)),
        new Algorithm("Quick Sort (Median of Three)", () -> medianOfThree(0, inputSize - 1))
    };
    static int[] unsortedArray;
    static int[] sortedArray;
    // Booleans
    static boolean doInsertion;
    static boolean doMedian;
    static boolean doSelection;
    static boolean doQuick;
    // Doubles
    static double insertionTime = -1.0;
    static double medianTime = -1.0;
    static double selectionTime = -1.0;
    static double quickTime = -1.0;

    // ***************************************************************
    // MAIN FUNCTION
    // ***************************************************************
    public static void main(String[] args) {
        // Create Scanner Object
        Scanner scanner = new Scanner(System.in);

        // Get Input Size
        System.out.println("Enter input size: ");
        inputSize = scanner.nextInt();

        // Get Input Type
        System.out.println("========== Select Option for Input Numbers ==========");
        System.out.println("1. Ascending Order");
        System.out.println("2. Descending Order");
        System.out.println("3. Random");
        System.out.println("Choose option:");
        int inputType = scanner.nextInt();

        // Get which algorithms to run
        getAlgoBooleans(scanner);

        // Close Scanner
        scanner.close();

        // Generate Array
        // 1 = Ascending Order
        // 2 = Descending Order
        // 3 = Random Order
        unsortedArray = new int[inputSize];
        switch(inputType) {
            case 1:
                generateAscending();
                break;
            case 2:
                generateDescending();
                break;
            case 3:
            default:
                generateRandom();
                break;
        }

        // Initialize start variable
        Instant start;

        // Run algorithms
        for(int i = 0; i < algorithms.length; i++) {
            if(algorithms[i].run) {
                resetSorted();
                start = Instant.now();
                algorithms[i].sort.sort();
                algorithms[i].runtime = Duration.between(start, Instant.now()).toNanos();
            }
        }
        
        // Print numbers generated and sort Results
        // Only print if input size <= 10
        if(inputSize <= 20) {
            printSorts();
        }

        // Print Times
        System.out.println("==================== Execution Result ====================");
        // ArrayList<Integer> ranks = printTimes();
        System.out.println("==========================================================");
        
        // Print Ranking
        System.out.println("\n========================= Ranking ========================");
        // printRanking(ranks);
        System.out.println("==========================================================");
    }

    // ***************************************************************
    // ALGORITHM FUNCTIONS
    // ***************************************************************

    /**
     * Sorts sortedArray using the Insertion Sort algorithm.
     */
    private static void insertionSort() {
        for(int i = 1; i < inputSize; i++) {
            int key = sortedArray[i];
            int j = i - 1;
            while(j >= 0 && sortedArray[j] > key) {
                sortedArray[j + 1] = sortedArray[j];
                j--;
            }
            sortedArray[j + 1] = key;
        }
    }

    /**
     * Sorts sortedArray using the Quick Sort (Median of Three) algorithm.
     */
    private static void medianOfThree(int start, int end) {
        //System.out.println("\nQuick Sort from " + start + " to " + end);
        //printSubArray(sortedArray, start, end);
        // Check if array partition is empty or only one element
        if(end - start <= 0) {
            return;
        }
        
        // Sort first, median, last
        int temp;
        int middleIndex = (start + end) / 2;
        if(sortedArray[start] > sortedArray[middleIndex]) {
            temp = sortedArray[start];
            sortedArray[start] = sortedArray[middleIndex];
            sortedArray[middleIndex] = temp;
        }
        if(sortedArray[middleIndex] > sortedArray[end]) {
            temp = sortedArray[middleIndex];
            sortedArray[middleIndex] = sortedArray[end];
            sortedArray[end] = temp;
        }
        if(sortedArray[start] > sortedArray[middleIndex]) {
            temp = sortedArray[start];
            sortedArray[start] = sortedArray[middleIndex];
            sortedArray[middleIndex] = temp;
        }

        // Swap median with second element
        temp = sortedArray[middleIndex];
        sortedArray[middleIndex] = sortedArray[start + 1];
        sortedArray[start + 1] = temp;
        
        // Set pivot (index of pivot)
        int pivot = start + 1;
        //System.out.println("Pivot: " + sortedArray[pivot]);

        // initialize i and j
        int i = start + 1;
        int j = end - 1;

        // Increment i until i reaches the end of partition or until i and j meet
        while(i <= end && i < j) {
            i++;
            //System.out.println("i -> [" + i + "] -> " + sortedArray[i]);
            // Check if element i is greater than or equal to the pivot.
            if(sortedArray[i] >= sortedArray[pivot]) {
                // Decrement j
                //System.out.println("i is greater than pivot");
                while(j >= start && j > i) {
                    //System.out.println("j -> [" + j + "] -> " + sortedArray[j]);
                    // Check if element j is less than or equal to the pivot.
                    if(sortedArray[j] <= sortedArray[pivot]) {
                        //System.out.println("j is smaller than pivot\nSwapping i and j");
                        // Swap element i and j
                        temp = sortedArray[j];
                        sortedArray[j] = sortedArray[i];
                        sortedArray[i] = temp;
                        break;
                    }
                    j--;
                }
            }
        }
        //System.out.println("i and j have met");

        // Decrement j until it reaches an element smaller than the pivot or it reaches the pivot 
        while(sortedArray[j] >= sortedArray[pivot] && j > pivot) {
            //System.out.println("j -> [" + j + "] -> " + sortedArray[j]);
            j--;
        }

        // Swap the pivot and element j
        //System.out.println("Swapping j and pivot");
        temp = sortedArray[pivot];
        sortedArray[pivot] = sortedArray[j];
        sortedArray[j] = temp;

        //printSubArray(sortedArray, start, end);

        // Divide array and call self
        medianOfThree(start, j - 1);
        medianOfThree(j + 1, end);
    }

    /**
     * Sorts the sortedArray using the Selection Sort algorithm.
     */
    private static void selectionSort() {
        for(int i = 0; i < sortedArray.length; i++) {
            // Get smallest element in array
            int minIndex = i;
            for(int j = i + 1; j < sortedArray.length; j++) {
                if(sortedArray[j] < sortedArray[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap elements
            int temp = unsortedArray[i];
            sortedArray[i] = sortedArray[minIndex];
            sortedArray[minIndex] = temp;
        }
    }

    /**
     * Sorts the sortedArray using the Quick Sort algorithm.
     */
    private static void quickSort(int start, int end) {
        //System.out.println("\nQuick Sort from " + start + " to " + end);
        //printSubArray(sortedArray, start, end);
        // Check if array partition is empty or only one element
        if(end - start <= 0) {
            return;
        }
        
        // Set pivot (index of pivot)
        int pivot = start;
        //System.out.println("Pivot: " + sortedArray[pivot]);

        // initialize i and j
        int i = start;
        int j = end;

        // Increment i until i reaches the end of partition or until i and j meet
        while(i <= end && i < j) {
            i++;
            //System.out.println("i -> [" + i + "] -> " + sortedArray[i]);
            // Check if element i is greater than or equal to the pivot.
            if(sortedArray[i] >= sortedArray[pivot]) {
                // Decrement j
                //System.out.println("i is greater than pivot");
                while(j >= start && j > i) {
                    //System.out.println("j -> [" + j + "] -> " + sortedArray[j]);
                    // Check if element j is less than or equal to the pivot.
                    if(sortedArray[j] <= sortedArray[pivot]) {
                        //System.out.println("j is smaller than pivot\nSwapping i and j");
                        // Swap element i and j
                        int temp = sortedArray[j];
                        sortedArray[j] = sortedArray[i];
                        sortedArray[i] = temp;
                        break;
                    }
                    j--;
                }
            }
        }
        //System.out.println("i and j have met");

        // Decrement j until it reaches an element smaller than the pivot or it reaches the pivot 
        while(sortedArray[j] >= sortedArray[pivot] && j > pivot) {
            //System.out.println("j -> [" + j + "] -> " + sortedArray[j]);
            j--;
        }

        // Swap the pivot and element j
        //System.out.println("Swapping j and pivot");
        int temp = sortedArray[pivot];
        sortedArray[pivot] = sortedArray[j];
        sortedArray[j] = temp;

        //printSubArray(sortedArray, start, end);

        // Divide array and call self
        quickSort(start, j - 1);
        quickSort(j + 1, end);
    }

    // ***************************************************************
    // HELPER FUNCTIONS
    // ***************************************************************

    /**
     * Fills each array with ascending numbers from 1-inputSize
     */
    private static void generateAscending() {
        for(int i = 0; i < inputSize; i++) {
            unsortedArray[i] = i + 1;
        }
    }

    /**
     * Fills each array with descending numbers from inputSize-1
     */
    private static void generateDescending() {
        int num = inputSize;
        for(int i = 0; i < inputSize; i++) {
            unsortedArray[i] = num;
            num--;
        }
    }

    /**
     * Fills each array with random numbers from 1-inputSize*10
     */
    private static void generateRandom() {
        Random r = new Random();
        for(int i = 0; i < inputSize; i++) {
            unsortedArray[i] = r.nextInt(inputSize*10 + 1);
        }
    }

    /**
     * Asks the user which algorithms they would like to run and updated the do___ booleans.
     */
    private static void getAlgoBooleans(Scanner scanner) {
        // Ask if all algorithms will be ran
        System.out.println("Run all sorting algorithms (y/n)?");
        if(scanner.next().trim().equals("y")) {
            // Set all algorithm booleans to true
            for(int i = 0; i < algorithms.length; i++) {
                algorithms[i].run = true;
            }
            return;
        }

        // For each algorithm, ask if it wil be run
        for(int i = 0; i < algorithms.length; i++) {
            System.out.println("Run " + algorithms[i].name + " (y/n)?");
            if(scanner.nextLine().trim().equals("y")) {
                algorithms[i].run = true;
            }
        }
    }

    /**
     * Prints the contents of an array (without brackets, with spaces in between elements)
     */
    private static void printArray(int[] array) {
        // Loop through the array
        for(int i = 0; i < array.length; i++) {
            // Print array element
            System.out.print(" " + array[i] + " ");
        }
    }

    /**
     * Prints the ranking between the sorting algorithms
     * The ranks ArrayList stores the ranks of each algorithm:
     * 1 = Quick Sort
     * 2 = Quick Sort (Median of Three)
     * 3 = Insertion Sort
     * 4 = Selection
     */
    private static void printRanking(ArrayList<Integer> ranks) {
        for(int i = 0; i < ranks.size(); i++) {
            // Print rank number
            System.out.print("(" + (i + 1) + ") ");

            // Print sort method and zero time variable
            switch(ranks.get(i)) {
                case 1:
                    System.out.println("Quick sort");
                    break;
                case 2:
                    System.out.println("Quick sort (Median of Three)");
                    break;
                case 3:
                    System.out.println("Insertion sort");
                    break;
                case 4:
                    System.out.println("Selection sort");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Prints the contents of each array
     */
    private static void printSorts() {
        // Unsorted Array
        System.out.print("Numbers generated: ");
        printArray(unsortedArray);
        System.out.println();

        // Sorted Array
        System.out.print("Sort results: ");
        printArray(sortedArray);
        System.out.println();
    }

    /**
     * Prints a subarray of arr from start to end
     * Used for debugging.
     */
    private static void printSubArray(int[] arr, int start, int end) {
        for(int i = start; i <= end; i++) {
            System.out.print(" " + arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * Prints the times of the different sorts, sorted by time.
     */
    private static ArrayList<Integer> printTimes() {
        // Create ArrayList
        ArrayList<Integer> ranks = new ArrayList<Integer>(3);
        
        // Check if all times have been processed or are 0
        while(quickTime != -1.0 || medianTime != -1.0 || insertionTime != -1.0 || selectionTime != -1.0) {
            // Get max
            double max = Math.max(quickTime, medianTime);
            max = Math.max(max, insertionTime);
            max = Math.max(max, selectionTime);

            // Print sort method and zero time variable
            if(max == quickTime) {
                quickTime = -1.0;
                System.out.print("Quick sort: ");
                ranks.add(0, 1);
            } else if(max == medianTime) {
                medianTime = -1.0;
                System.out.print("Quick sort (Median of Three): ");
                ranks.add(0, 2);
            } else if(max == insertionTime){
                insertionTime = -1.0;
                System.out.print("Insertion sort: ");
                ranks.add(0, 3);
            } else {
                selectionTime = -1.0;
                System.out.print("Selection sort: ");
                ranks.add(0, 4);
            }

            // Print time
            double millis = max / 1000000.0;
            System.out.println(millis + " milliseconds");
        }

        // Return Ranks
        return ranks;
    }

    /**
     * Resets the sorted array back to being unsorted.
     */
    private static void resetSorted() {
        sortedArray = unsortedArray.clone();
    }
}

