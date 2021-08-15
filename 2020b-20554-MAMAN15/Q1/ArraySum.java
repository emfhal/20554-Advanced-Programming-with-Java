import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArraySum {
    
    /*Here we will open a dialogue between our app and the user and we will ask him to give us a number of elemnt in the array and
    number of process and create for him a array with random values. the next step is to take 2 elemnt from the array and sumrized and
    put 1 elemnt back that is the sum of the 2 elemnt that we took*/
    
    public static void main(final String[] args) {

	int[] arr;
	int n = 0; // n number of array
	int m = 0; // m number of process
	try (Scanner userInput = new Scanner(System.in)) {
	    System.out.println("Please enter the process numbers and size of array");

	    do {
		System.out.print("1. The array size: "); // n elemnts
		n = userInput.nextInt();
		System.out.println();
	    } while (!validNumber(n));

	    do {
		System.out.print("2. Number of process: "); //m process
		m = userInput.nextInt();
		System.out.println();
	    } while (!validNumber(m));
	}
	arr = new int[n];
	fillArr(arr); //fill the array with numbers between 1 to 100

	System.out.println("The array values:");
	printBackArr(arr);
	System.out.println();

	System.out.println();
	System.out.println();
	sumArrayWithThreadsSync(arr, m);

	System.out.println();
	System.out.println("The sum of the array is:"); // return the first elemnt in array that is the sum of all the orignal array
	System.out.println();
	System.out.println(arr[0]);
	System.out.println();
	return;
    }

    private static int[] sumArrayWithThreadsSync(final int[] arr, final int m) { 

	final int[] arrProduct = new int[arr.length];
	//
	final ConcurrentSumArrayTask.ConcurrencyContext context = new ConcurrentSumArrayTask.ConcurrencyContext(
		arrProduct.length);
	//
	final Runnable task = new ConcurrentSumArrayTask(context, arr, arrProduct);

	final Thread[] workers = new Thread[m]; // m process (from user input)
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
		Logger.getLogger(ArraySum.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return arrProduct;
    }

    private static void printBackArr(final int[] arr) {//print array by elemnt index order
	for (int i = 0; i < arr.length; i++) {
	    System.out.print(arr[i] + " ");
	}
	System.out.println();
    }

    // Enter random values to array (1 to 100)
    private static void fillArr(final int[] arr) {
	final Random rand = new Random();
	for (int i = 0; i < arr.length; i++) {
	    arr[i] = rand.nextInt(100) + 1; // set arr values from 1 to 100

	}
    }

    public static boolean validNumber(final int dim) { //check the nubmer of n and m is valid (positive number only)
	if (dim <= 0) {
	    System.err.println("Please enter a positive number");
	    return false;
	}
	return true;

    }
}