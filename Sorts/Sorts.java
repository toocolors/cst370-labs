/*
 * Abstract: Generates an array based on user input, sorts the array using multiple algorithms, then displays the time spent on each sort algorithm.
 * Name: Noah deFer
 * Date: 2/17/2026
 */
import java.time.Duration;
import java.time.Instant;
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
    long runtime = -1;
    AlgorithmFunction sort;

    // Constructor
    public Algorithm(String name, AlgorithmFunction sort) {
        this.name = name;
        this.sort = sort;
    }
}
 
class Main 
{
    // ***************************************************************
    // GLOBAL VARIABLES
    // ***************************************************************
    // Booleans
    static boolean printArrays;
    // Integers
    static int inputSize;
    // Arrays
    static Algorithm[] algorithms = {
        new Algorithm("Bubble Sort", () -> bubbleSort()),
        new Algorithm("Insertion Sort", () -> insertionSort()),
        new Algorithm("Selection Sort", () -> selectionSort()),
        new Algorithm("Quick Sort", () -> quickSort(0, inputSize - 1)),
        new Algorithm("Quick Sort (Median of Three)", () -> medianOfThree(0, inputSize - 1))
    };
    static int[] unsortedArray;
    static int[] sortedArray;

    // ***************************************************************
    // MAIN FUNCTION
    // ***************************************************************
    public static void main(String[] args) {
        // Create Scanner Object
        Scanner scanner = new Scanner(System.in);

        // Get Input Size
        System.out.println("Enter input size: ");
        inputSize = scanner.nextInt();

        // Update printArrays if inputSize <= 20
        if(inputSize <= 20) {
            System.out.println("Print unsorted array and sort results? (y/n)");
            if(scanner.next().trim().equals("y")) {
                printArrays = true;
            }
        }

        // Get input type and generate array
        // Get input type
        System.out.println("========== Select Option for Input Numbers ==========");
        System.out.println("1. Ascending Order");
        System.out.println("2. Descending Order");
        System.out.println("3. Random");
        System.out.println("Choose option:");
        // Generate Array
        // 1 = Ascending Order
        // 2 = Descending Order
        // 3 = Random Order
        unsortedArray = new int[inputSize];
        switch(scanner.nextInt()) {
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

        // Print unsorted array
        if(printArrays) {
            System.out.print("Input: ");
            printArray(unsortedArray);
        }

        // Get which algorithms to run
        runAlgorithms(scanner);

        // Close Scanner
        scanner.close();

        // Sort algorithms by runtime
        sortRuntime();

        // Print Times
        System.out.println("==================== Execution Result ====================");
        printTimes();
        System.out.println("==========================================================");
        
        // Print Ranking
        System.out.println("\n========================= Ranking ========================");
        printRanking();
        System.out.println("==========================================================");
    }

    // ***************************************************************
    // ALGORITHM FUNCTIONS
    // ***************************************************************

    /**
     * Sorts sortedArray using Bubble Sort
     */
    private static void bubbleSort() {
        boolean sorted;
        do {
            // Reset sorted
            sorted = true;

            // Loop through array
            for(int i = 1; i < inputSize - 1; i++) {
                if(sortedArray[i - 1] > sortedArray[i]) {
                    // Update sorted
                    sorted = false;

                    // Swap elements
                    int temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i - 1];
                    sortedArray[i - 1] = temp;
                }
            }
        } while(!sorted);
    }

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
     * Prints the contents of an array (without brackets, with spaces in between elements)
     */
    private static void printArray(int[] array) {
        // Loop through the array
        for(int i = 0; i < array.length; i++) {
            // Print array element
            System.out.print(array[i] + " ");
        }
        
        // Print newline
        System.out.println();
    }

    /**
     * Prints the ranking between the sorting algorithms 
     *  from shortest to longest runtime.
     */
    private static void printRanking() {
        for(int i = 0; i < algorithms.length; i++) {
            // Check if algorithm was ran
            if(algorithms[i].runtime == -1) {
                continue;
            }

            // Print rank number and algorithm name
            System.out.println("(" + (i + 1) + ") " + algorithms[i].name);
        }
    }

    /**
     * Prints the contents of each array.
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
     * Prints the times of the different algorithms,
     *  from longest to shortest runtime.
     */
    private static void printTimes() {
        for(int i = algorithms.length - 1; i >= 0; i--) {
            // Check if time is -1 (Then this and next algorithms where not ran)
            if(algorithms[i].runtime == -1) {
                break;
            }

            // Print sort method
            System.out.print(algorithms[i].name + ": ");

            // Print time
            System.out.println(algorithms[i].runtime / 1000000.0 + " milliseconds");
        }
    }

    /**
     * Resets the sorted array back to being unsorted.
     */
    private static void resetSorted() {
        sortedArray = unsortedArray.clone();
    }

    /**
     * Runs the passed in algorithm
     */
    private static void runAlgorithm(Algorithm algo) {
        // Try to run algorithm
        try {
            // Reset sorted array
            resetSorted();

            // Initalize instant and get current time
            Instant start;
            start = Instant.now();

            // Run algorithm
            algo.sort.sort();

            // Get end time
            algo.runtime = Duration.between(start, Instant.now()).toNanos();
        } catch(StackOverflowError e) {
            System.out.println("Could not run " + algo.name + ". Array size is too long.");
            return;
        }

        // Print sort result
        if(printArrays) {
            System.out.print(algo.name + ": ");
            printArray(sortedArray);
        }
    }

    /**
     * Asks the user which algorithms they would like to run and updated the do___ booleans.
     */
    private static void runAlgorithms(Scanner scanner) {
        // Ask if all algorithms will be ran
        System.out.println("Run all sorting algorithms (y/n)?");
        if(scanner.next().trim().equals("y")) {
            // Set all algorithm booleans to true
            for(int i = 0; i < algorithms.length; i++) {
                runAlgorithm(algorithms[i]);
            }
            return;
        }

        // For each algorithm, ask if it wil be run
        for(int i = 0; i < algorithms.length; i++) {
            System.out.println("Run " + algorithms[i].name + " (y/n)?");
            if(scanner.next().trim().equals("y")) {
                runAlgorithm(algorithms[i]);
            }
        }
    }

    /**
     * Sorts algorithms array based on runtime using insertion sort.
     */
    private static void sortRuntime() {
        for(int i = 1; i < algorithms.length; i++) {
            Algorithm key = algorithms[i];
            int j = i - 1;
            while(j >= 0 && algorithms[j].runtime > key.runtime) {
                algorithms[j + 1] = algorithms[j];
                j--;
            }
            algorithms[j + 1] = key;
        }
    }
}

