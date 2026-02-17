/*
 * Abstract: Allows the user to manipulate a Hash Table with Linear Probing.
 * Name: Noah deFer
 * Date: 
 */
import java.util.Scanner;
import java.util.regex.Pattern;
 
class Main 
{

    // Global variables
    static String[] commands;
    static double maxLoad = 0.5;
    static int[] table;
    static int tableSize = 5;

    public static void main(String[] args) {
        // Create hash table
        table = initializeArray(tableSize);
        
        // Create Scanner
        Scanner scanner = new Scanner(System.in);

        // Run Commands
        // Get first command
        System.out.print("Enter command: ");
        String command = scanner.nextLine();
        command = command.trim();
        // Loop until quit
        while(!command.equals("quit")) {
            // Run command
            runCommand(command);

            // Get next command
            System.out.print("Enter command: ");
            command = scanner.nextLine();
            command = command.trim();
        }

        // Close Scanner
        scanner.close();
    }

    /**
     * Calculates the load factor of the table.
     */
    private static double getLoad() {
        // Calculate n
        int n = 0;
        for(int i = 0; i < table.length; i++) {
            if(table[i] >= 0) {
                n++;
            }
        }

        // Calculate Load Factor
        return n / (table.length + 0.0);
    }

    /**
     * Displays command information.
     */
    private static void displayHelp() {
        System.out.println("displayStatus X: Print the value of index X.");
        System.out.println("getLoad: Prints the current Load Factor.");
        System.out.println("getMaxLoad: Prints the current Maximum Load Factor.");
        System.out.println("insert X: Inserts integer X into the hash table.");
        System.out.println("newMaxLoad X: Sets the Maximum Load Factor to X.");
        System.out.println("search X: Search for the location of element X in the hash table.");
        System.out.println("tableSize: Prints the current table size.");
        System.out.println("info: Print program information.");
        System.out.println("help: Print command information.");
        System.out.println("quit: Exit the program.");
    }

    /**
     * Displays program information
     */
    private static void displayInfo() {
        System.out.println("This program allows the user to manipulate a Hash Table.");
        System.out.println("A Hash Table is an array that\nuses hashing to assign indexes to elements.");
        System.out.println("This program uses a simple modulo operation to assign indexes.");
        System.out.println("If two elements would have the same index,\nthe table uses Linear Probing to assign the index of the second element.");
        System.out.println("Linear Probing, or Open Addressing, is where\nthe program searches for the first available index starting at the hash result.");
    }

    /**
     * Prints the value of an index, or empty if there is none.
     */
    private static void displayStatus(int index) {
        // Check if index is out of bounds
        if(index < 0 || table.length <= index) {
            System.out.println(index + " is out of bounds.");
            return;
        }

        // Check status of index
        if(table[index] >= 0) {
            System.out.println("Value at index " + index + ": " + table[index]);
        } else {
            System.out.println("Value at index " + index + " is empty.");
        }
    }

    /**
     * Inserts a number into table,
     *  then calculates the current load factor and resizes the table if needed.
     */
    private static void insert(int num) {
        // Insert num
        int i = num % table.length;
        while(true) {
            // Check if table[i] is empty
            if(table[i] < 0) {
                table[i] = num;
                break;
            }
            
            // Update i
            if(i >= table.length - 1) {
                i = 0;
            } else {
                i++;
            }
        }

        // Calculate load factor and increase table size if needed
        double load = getLoad();
        if(load > maxLoad) {
            resizeTable();
        }
    }

    /**
     * Creates an array, fills it with -1, and returns it.
     */
    private static int[] initializeArray(int size) {
        // Create Array
        int[] arr = new int[size];

        // Fill array with -1
        for(int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }

        // Return array
        return arr;
    }

    /**
     * Finds the next prime number after n
     */
    private static int nextPrime(int n) {
        n *= 2;
        boolean prime = false;
        while(!prime) {
            // Increment n
            n++;

            // Reset prime boolean
            prime = true;

            // Check if n has factors
            for(int i = 2; i < n; i++) {
                if(n % i == 0) {
                    prime = false;
                    break;
                }
            }
        }
        
        return n;
    }

    /**
     * Increases the table size and rehashes all values.
     */
    private static void resizeTable() {
        // Create copy of table
        int[] copy = table.clone();

        // Get new table size
        int newSize = nextPrime(table.length);

        // Reinitialize Table
        table = initializeArray(newSize);

        // Rehash table
        for(int i = 0; i < copy.length; i++) {
            if(copy[i] >= 0) {
                insert(copy[i]);
            }
        }
    }

    private static void runCommand(String command) {
        // Check if command is insert X
        if(Pattern.matches("^insert [0-9]+$", command)) {
            int num = Integer.parseInt(command.substring(7));
            // Check if X is within range
            if(num < 0) {
                System.out.println("Number should be an integer equal to or greater than 0.");
            } else {
                insert(num);
            }
            return;
        }

        // Check if command is search X
        if(Pattern.matches("^search [0-9]+$", command)) {
            int num = Integer.parseInt(command.substring(7));
            // Check if X is within range
            if(num < 0) {
                System.out.println("Number should be an integer equal to or greater than 0.");
            } else {
                search(num);
            }
            return;
        }

        // Check if command is displayStatus X
        if(Pattern.matches("^displayStatus [0-9]+$", command)) {
            displayStatus(Integer.parseInt(command.substring(14)));
            return;
        }

        // Check if command is setMaxLoad X.X
        if(Pattern.matches("^setMaxLoad [0-9]+.[0-9]+$", command)) {
            double newMaxLoad = Double.parseDouble(command.substring(12));
            // Check if newMaxLoad is within bounds
            if(newMaxLoad <= 0.0 || 1.0 <= newMaxLoad) {
                System.out.println("Load factor must be a double between 0.0 and 1.0.");
            } else {
                // Update maxLoad
                maxLoad = newMaxLoad;
                // Check if table needs to be resized
                if(getLoad() > maxLoad) {
                    resizeTable();
                }
            }
            return;
        }

        // Check if command is tableSize or unknown
        switch(command) {
            case "help":
                displayHelp();
                break;
            case "getLoad":
                System.out.println("Current Load Factor: " + getLoad());
                break;
            case "getMaxLoad":
                System.out.println("Maximum Load Factor: " + maxLoad);
                break;
            case "info":
                displayInfo();
                break;
            case "tableSize":
                System.out.println(table.length);
                break;
            default:
                System.out.println(command + " is unknwon");
                break;
        }
    }

    /**
     * Searches for num within the table, and prints the result.
     */
    private static void search(int num) {
        // Get hash of num
        int i = num % table.length;
        
        boolean found = false;
        do {
            // Check if table[i] is num
            if(table[i] == num) {
                System.out.println(num + " found at index " + i + ".");
                return;
            }
            
            // Update i
            if(i >= table.length - 1) {
                i = 0;
            } else {
                i++;
            }
        } while(i != num % table.length);

        // Print result (not found)
        System.out.println(num + "not found.");
    }
}

