/*
 * Abstract: Generates a array based on user input, sorts the array using multiple algorithms, then displays the time spent on each sort algorithm.
 * Name: Noah deFer
 * Date: 2/17/2026
 */
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
 
class Main 
{
    // Global Variables
    static int inputSize;
    static boolean doInsertion;
    static boolean doMedian;
    // Arrays
    static int[] unsortedArray;
    static int[] quickArray;
    static int[] medianArray;
    static int[] insertionArray;

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

        // Get Insertion Sort Boolean
        System.out.println("Run insertion sort(y/n)? ");
        doInsertion = false;
        if(scanner.next().equals("y")) {
            doInsertion = true;
        }
        

        // Get Median of Three Boolean
        System.out.println("Run quick sort with Median of Three(y/n)? ");
        doMedian = false;
        if(scanner.next().equals("y")) {
            doMedian = true;
        }

        // Close Scanner
        scanner.close();

        // Generate Array
        // 1 = Ascending Order
        // 2 = Descending Order
        // 3 = Random Order
        unsortedArray = new int[inputSize];
        quickArray = new int[inputSize];
        medianArray = new int[inputSize];
        insertionArray = new int[inputSize];
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

        // Run Quick Sort
        double quickTime = -1.0;
        start = Instant.now();
        quickSort(0, inputSize - 1);
        quickTime = Duration.between(start, Instant.now()).toNanos();

        // Run Quick Sort (Median of Three)
        double medianTime = -1.0;
        if(doMedian) {
            start = Instant.now();
            medianOfThree(0, inputSize - 1);
            medianTime = Duration.between(start, Instant.now()).toNanos();
        }

        // Run Insertion Sort
        double insertionTime = -1.0;
        if(doInsertion) {
            start = Instant.now();
            insertionSort();
            insertionTime = Duration.between(start, Instant.now()).toNanos();
        }
        
        // Print numbers generated and sort Results
        // Only print if input size <= 10
        if(inputSize <= 20) {
            printSorts();
        }

        // Print Times
        System.out.println("==================== Execution Result ====================");
        ArrayList<Integer> ranks = printTimes(quickTime, medianTime, insertionTime);
        System.out.println("==========================================================");
        
        // Print Ranking
        System.out.println("\n========================= Ranking ========================");
        printRanking(quickTime, medianTime, insertionTime, ranks);
        System.out.println("==========================================================");
    }

    /**
     * Fills each array with ascending numbers from 1-inputSize
     */
    private static void generateAscending() {
        for(int i = 0; i < inputSize; i++) {
            unsortedArray[i] = i + 1;
            quickArray[i] = i + 1;
            medianArray[i] = i + 1;
            insertionArray[i] = i + 1;
        }
    }

    /**
     * Fills each array with descending numbers from inputSize-1
     */
    private static void generateDescending() {
        int num = inputSize;
        for(int i = 0; i < inputSize; i++) {
            unsortedArray[i] = num;
            quickArray[i] = num;
            medianArray[i] = num;
            insertionArray[i] = num;
            num--;
        }
    }

    /**
     * Fills each array with random numbers from 1-inputSize*10
     */
    private static void generateRandom() {
        Random r = new Random();
        for(int i = 0; i < inputSize; i++) {
            int rand = r.nextInt(inputSize*10 + 1);
            unsortedArray[i] = rand;
            quickArray[i] = rand;
            medianArray[i] = rand;
            insertionArray[i] = rand;
        }
    }

    /**
     * Sorts insertionArray using the Insertion Sort algorithm.
     */
    private static void insertionSort() {
        for(int i = 1; i < inputSize; i++) {
            int key = insertionArray[i];
            int j = i - 1;
            while(j >= 0 && insertionArray[j] > key) {
                insertionArray[j + 1] = insertionArray[j];
                j--;
            }
            insertionArray[j + 1] = key;
        }
    }

    /**
     * Sorts medianArray using the Quick Sort (Median of Three) algorithm.
     */
    private static void medianOfThree(int start, int end) {
        //System.out.println("\nQuick Sort from " + start + " to " + end);
        //printSubArray(medianArray, start, end);
        // Check if array partition is empty or only one element
        if(end - start <= 0) {
            return;
        }
        
        // Sort first, median, last
        int temp;
        int middleIndex = (start + end) / 2;
        if(medianArray[start] > medianArray[middleIndex]) {
            temp = medianArray[start];
            medianArray[start] = medianArray[middleIndex];
            medianArray[middleIndex] = temp;
        }
        if(medianArray[middleIndex] > medianArray[end]) {
            temp = medianArray[middleIndex];
            medianArray[middleIndex] = medianArray[end];
            medianArray[end] = temp;
        }
        if(medianArray[start] > medianArray[middleIndex]) {
            temp = medianArray[start];
            medianArray[start] = medianArray[middleIndex];
            medianArray[middleIndex] = temp;
        }

        // Swap median with second element
        temp = medianArray[middleIndex];
        medianArray[middleIndex] = medianArray[start + 1];
        medianArray[start + 1] = temp;
        
        // Set pivot (index of pivot)
        int pivot = start + 1;
        //System.out.println("Pivot: " + medianArray[pivot]);

        // initialize i and j
        int i = start + 1;
        int j = end - 1;

        // Increment i until i reaches the end of partition or until i and j meet
        while(i <= end && i < j) {
            i++;
            //System.out.println("i -> [" + i + "] -> " + medianArray[i]);
            // Check if element i is greater than or equal to the pivot.
            if(medianArray[i] >= medianArray[pivot]) {
                // Decrement j
                //System.out.println("i is greater than pivot");
                while(j >= start && j > i) {
                    //System.out.println("j -> [" + j + "] -> " + medianArray[j]);
                    // Check if element j is less than or equal to the pivot.
                    if(medianArray[j] <= medianArray[pivot]) {
                        //System.out.println("j is smaller than pivot\nSwapping i and j");
                        // Swap element i and j
                        temp = medianArray[j];
                        medianArray[j] = medianArray[i];
                        medianArray[i] = temp;
                        break;
                    }
                    j--;
                }
            }
        }
        //System.out.println("i and j have met");

        // Decrement j until it reaches an element smaller than the pivot or it reaches the pivot 
        while(medianArray[j] >= medianArray[pivot] && j > pivot) {
            //System.out.println("j -> [" + j + "] -> " + medianArray[j]);
            j--;
        }

        // Swap the pivot and element j
        //System.out.println("Swapping j and pivot");
        temp = medianArray[pivot];
        medianArray[pivot] = medianArray[j];
        medianArray[j] = temp;

        //printSubArray(medianArray, start, end);

        // Divide array and call self
        medianOfThree(start, j - 1);
        medianOfThree(j + 1, end);
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
     */
    private static void printRanking(
        double quickTime, 
        double medianTime, 
        double insertionTime, 
        ArrayList<Integer> ranks) {
        for(int i = 0; i < ranks.size(); i++) {
            // Print rank number
            System.out.print("(" + (i + 1) + ") ");

            // Print sort method and zero time variable
            if(ranks.get(i) == 1) {
                System.out.println("Quick sort");
            } else if(ranks.get(i) == 2) {
                medianTime = 0;
                System.out.println("Quick sort (Median of Three)");
            } else {
                insertionTime = 0;
                System.out.println("Insertion sort");
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

        // Quick Sort Array
        System.out.print("Quick sort result: ");
        printArray(quickArray);
        System.out.println();

        // Median of Three Array
        if(doMedian) {
            System.out.print("Quick sort (Median of Three) result: ");
            printArray(medianArray);
            System.out.println();
        }

        // Insertion Array
        if(doInsertion) {
            System.out.print("Insertion sort result: ");
            printArray(insertionArray);
            System.out.println();
        }
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
    private static ArrayList<Integer> printTimes(
        double quickTime, 
        double medianTime, 
        double insertionTime) {
        // Create ArrayList
        ArrayList<Integer> ranks = new ArrayList<Integer>(3);
        
        // Check if all times have been processed or are 0
        while(quickTime != -1.0 || medianTime != -1.0 || insertionTime != -1.0) {
            // Get max
            double max = Math.max(Math.max(quickTime, medianTime), Math.max(medianTime, insertionTime));

            // Print sort method and zero time variable
            if(max == quickTime) {
                quickTime = -1.0;
                System.out.print("Quick sort: ");
                ranks.add(0, 1);
            } else if(max == medianTime) {
                medianTime = -1.0;
                System.out.print("Quick sort (Median of Three): ");
                ranks.add(0, 2);
            } else {
                insertionTime = -1.0;
                System.out.print("Insertion sort: ");
                ranks.add(0, 3);
            }

            // Print time
            double millis = max / 1000000.0;
            System.out.println(millis + " milliseconds");

            
        }

        // Return Ranks
        return ranks;
    }

    /**
     * Sorts the quickArray using the Quick Sort algorithm.
     */
    private static void quickSort(int start, int end) {
        //System.out.println("\nQuick Sort from " + start + " to " + end);
        //printSubArray(quickArray, start, end);
        // Check if array partition is empty or only one element
        if(end - start <= 0) {
            return;
        }
        
        // Set pivot (index of pivot)
        int pivot = start;
        //System.out.println("Pivot: " + quickArray[pivot]);

        // initialize i and j
        int i = start;
        int j = end;

        // Increment i until i reaches the end of partition or until i and j meet
        while(i <= end && i < j) {
            i++;
            //System.out.println("i -> [" + i + "] -> " + quickArray[i]);
            // Check if element i is greater than or equal to the pivot.
            if(quickArray[i] >= quickArray[pivot]) {
                // Decrement j
                //System.out.println("i is greater than pivot");
                while(j >= start && j > i) {
                    //System.out.println("j -> [" + j + "] -> " + quickArray[j]);
                    // Check if element j is less than or equal to the pivot.
                    if(quickArray[j] <= quickArray[pivot]) {
                        //System.out.println("j is smaller than pivot\nSwapping i and j");
                        // Swap element i and j
                        int temp = quickArray[j];
                        quickArray[j] = quickArray[i];
                        quickArray[i] = temp;
                        break;
                    }
                    j--;
                }
            }
        }
        //System.out.println("i and j have met");

        // Decrement j until it reaches an element smaller than the pivot or it reaches the pivot 
        while(quickArray[j] >= quickArray[pivot] && j > pivot) {
            //System.out.println("j -> [" + j + "] -> " + quickArray[j]);
            j--;
        }

        // Swap the pivot and element j
        //System.out.println("Swapping j and pivot");
        int temp = quickArray[pivot];
        quickArray[pivot] = quickArray[j];
        quickArray[j] = temp;

        //printSubArray(quickArray, start, end);

        // Divide array and call self
        quickSort(start, j - 1);
        quickSort(j + 1, end);
    }
}

