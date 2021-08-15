import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrixMultiplying {

    /*Here we will open a dialogue between our app and the user and we will aski for the number of rows and columns 
    of the first matrix and the number of columns of the second matrix.
    Finally we will present the duplicate matrix to it and suggest it to save to the txt file*/

    public static void main(final String[] args) {
	int[][] matrixA;
	int[][] matrixB;

	int colA = 0; // m
	int rowA = 0; // n
	int colB = 0; // p
	int rowB = 0; // m

	try (Scanner userInput = new Scanner(System.in)) {
	    System.out.println("Please enter n x m size for matrix A");

	    do {
		System.out.print("1. Numbers of columns: ");
		colA = userInput.nextInt();
		System.out.println();
	    } while (!validNumber(colA));

	    rowB = colA; // m of Matrix A = m of Matrix B

	    do {
		System.out.print("2. Number of Rows: ");
		rowA = userInput.nextInt();
		System.out.println();
	    } while (!validNumber(rowA));

	    matrixA = new int[rowA][colA]; //creating empty first matrix

	    do {
		System.out.println("Please enter p number of columns for matrix B: ");
		colB = userInput.nextInt();
		System.out.println();
	    } while (!validNumber(colB));

	    matrixB = new int[rowB][colB]; //creating empty second matrix

	    fillMatrix(matrixA); //here we will fill values for matrix a number between 1-10
	    fillMatrix(matrixB); //here we will fill values for matrix b number between 1-10

	    System.out.println("Matrix A:");
	    printBackMatrix(matrixA);
	    System.out.println();
	    System.out.println("Matrix B:");
	    printBackMatrix(matrixB);
	    System.out.println();

	    final int[][] matrixProduct3 = multMatrixWithThreadsSync(matrixA, matrixB);

	    System.out.println();
	    System.out.println("The Multiplite Matrix:");
	    printBackMatrix(matrixProduct3);
	    System.out.println();

	    System.out.println("Would you like to save the Multiplite Matrix output? (y/n)");
	    final String userResponse = userInput.next();

	    if (userResponse.equalsIgnoreCase("y")) {
		final String fileName = "output.txt";
		try {
		    printMatrixToFile(matrixProduct3, fileName);
		} catch (final IOException e) {
		    e.printStackTrace();
		}
		System.out.println();
		System.out.println("The Matrix output is being written to " + fileName);
	    }
	    return;
	}
    }

    private static int[][] multMatrixWithThreadsSync(final int[][] matrixA, final int[][] matrixB) { //ThreadsSync for mult matrix

	final int[][] matrixProduct = new int[matrixA.length][matrixB[0].length];
	//
	final ConcurrentMatrixMultiplyingTask.ConcurrencyContext context = new ConcurrentMatrixMultiplyingTask.ConcurrencyContext(
		matrixProduct.length);
	//
	final Runnable task = new ConcurrentMatrixMultiplyingTask(context, matrixA, matrixB, matrixProduct);
	final Thread[] workers = new Thread[matrixB[0].length * matrixA.length]; //n x p number of process
	for (int i = 0; i < workers.length; i++) {
	    workers[i] = new Thread(task, "Process-" + i);
	}
	for (int i = 0; i < workers.length; i++) {
	    final Thread worker = workers[i];
	    worker.start();
	}
	for (int i = 0; i < workers.length; i++) {
	    final Thread worker = workers[i];
	    try {
		worker.join();
	    } catch (final InterruptedException ex) {
		Logger.getLogger(MatrixMultiplying.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return matrixProduct;
    }

    private static void printMatrixToFile(final int[][] matrix, final String fileName) throws IOException { 
	//incase the user would like to save the ouput to a txt file
	final PrintWriter userOutput = new PrintWriter(new FileWriter(fileName));
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[0].length; j++) {
		userOutput.print(matrix[i][j] + " ");
	    }
	    userOutput.println();
	}
	userOutput.close();
    }

    private static void printBackMatrix(final int[][] matrix) { //print the matrix values in order
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[0].length; j++) {
		System.out.print(matrix[i][j] + " ");
	    }
	    System.out.println(); //new line for new row
	}
    }

    // Enter random values to Matrix A and B (1 to 10)
    private static void fillMatrix(final int[][] matrix) {
	final Random rand = new Random();
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[0].length; j++) {
		matrix[i][j] = rand.nextInt(10) + 1; // set mat values from 1 to 10
	    }
	}
    }

    public static boolean validNumber(final int dim) { //checking the dim number are valid (more than zero)
	if (dim <= 0) {
	    System.err.println("Please enter a positive number");
	    return false;
	}
	return true;

    }
}