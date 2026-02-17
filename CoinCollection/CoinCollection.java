/*
 * Abstract: Caulcate the solution to the Coin-Collection problem using Dynamic Programming approach.
 *  Gets graph and coin data from user/file input.
 * Name: Noah deFer
 * Date: 2/17/2026
 */
import java.util.Scanner;
 
class Main 
{

    // Global Variables
    static int[][] board;
    static int[][] maxCoins;
    static int height;
    static int width;

    public static void main(String[] args) {
        // Create Scanner
        Scanner scanner = new Scanner(System.in);

        // Get Height
        height = scanner.nextInt();

        // Get Width
        width = scanner.nextInt();

        // Initialize and fill board
        board = new int[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        // Close Scanner
        scanner.close();

        // Calculate maximum coins per cell
        maxCoins = new int[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                // Get count from up
                int up = 0;
                if(i - 1 >= 0) {
                    up = maxCoins[i - 1][j];
                }

                // Get count from left
                int left = 0;
                if(j - 1 >= 0) {
                    left = maxCoins[i][j - 1];
                }

                // Calculate amount
                maxCoins[i][j] = Math.max(up, left) + board[i][j];
            }
        }

        // Calculate Route
        // Set starting position
        int x = width - 1;
        int y = height - 1;
        // Initialize route String
        String route = "(" + height + "," + width + ")";
        // Loop until route is done
        while(x > 0 || y > 0) {
            // Get values of up and left
            // up
            int up = 0;
            if(y - 1 >= 0) {
                up = maxCoins[y - 1][x];
            }
            // left
            int left = 0;
            if(x - 1 >= 0) {
                left = maxCoins[y][x - 1];
            }

            // Update x/y
            if(x == 0) {
                y = Math.max(y - 1, 0);
            } else if(y == 0) {
                x = Math.max(x - 1, 0);
            } else if(left >= up) {
                x = Math.max(x - 1, 0);
            } else {
                y = Math.max(y - 1, 0);
            }

            // Update route
            route = "(" + (y + 1) + "," + (x + 1) + ")->" + route;
        }

        // Print results
        System.out.println("Max coins:" + maxCoins[height - 1][width - 1]);
        System.out.println("Path: " + route);
    }
    
    /**
     * Prints the board.
     * Used for debugging.
     */
    private static void printBoard(int[][] arr) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

