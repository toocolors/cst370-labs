/*
 * Abstract: Gets graph information from user/file,
 *  then calculates its all-pairs shortest paths using Floyd's algorithm.
 * Name: Noah deFer
 * Date: 2/17/2026
 */
import java.util.Scanner;
 
class Main 
{
    // Global Variables
    static int[][] matrix;
    static int vertices;

    public static void main(String[] args) {
        // Create Scanner
        Scanner scanner = new Scanner(System.in);

        // Get number of vertices
        vertices = scanner.nextInt();

        // Initialize and Fill adjacency matrix
        matrix = new int[vertices][vertices];
        for(int i = 0; i < vertices; i++) {
            for(int j = 0; j < vertices; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Close Scanner
        scanner.close();

        // Run Foyd's Algorithm
        for(int k = 0; k < vertices; k++) {
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    if(matrix[i][k] != -1 && matrix[k][j] != -1) {
                        // Get intermediate distance
                        int distance = matrix[i][k] + matrix[k][j];
                        // Update current index if it is -1 or if the intermediate path is shorter
                        if(matrix[i][j] == -1) {
                            matrix[i][j] = distance;
                        } else {
                            matrix[i][j] = Math.min(matrix[i][j], distance);
                        }
                        
                    }
                }
            }
        }

        // Print results
        printMatrix();
    }

    /**
     * Prints the contents of the adjacency matrix.
     */
    private static void printMatrix() {
        for(int i = 0; i < vertices; i++) {
            for(int j = 0; j < vertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

