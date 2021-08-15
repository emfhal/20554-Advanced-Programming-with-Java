public class ConcurrentMatrixMultiplyingTask implements Runnable {

    //Here we will monitor our program by n x p process and mult the matrix

    private final int[][] matrixA;
    private final int[][] matrixB;
    private final int[][] matrixProduct;
    //
    private final ConcurrencyContext context;

    public ConcurrentMatrixMultiplyingTask(final ConcurrencyContext context, final int[][] A, final int[][] B,
	    final int[][] C) {
	if (context == null) {
	    throw new IllegalArgumentException("Context can not be null");
	}
	this.context = context;
	this.matrixA = A;
	this.matrixB = B;
	this.matrixProduct = C;
    }

    @Override
    public void run() {
	while (true) {
	    int row;
	    synchronized (context) {
		if (context.isFullyProcessed()) { // incase the next row is equal to the last row break
		    break;
		}
		row = context.nextRowNum();
	    }

	    System.out.println(Thread.currentThread().getName() + " is working on:");

	    for (int j = 0; j < matrixB[0].length; j++) {
		for (int k = 0; k < matrixA[0].length; k++) {
		    matrixProduct[row][j] += matrixA[row][k] * matrixB[k][j]; // calculte the mult vector row col of
									      // matrix a and b to the mult matrix
		}
		System.out.println(row + " x " + j + " index (rowA x colB) = " + matrixProduct[row][j]);
	    }
	}
    }

    public static class ConcurrencyContext {

	private final int rowCount;
	private int nextRow = 0;

	public ConcurrencyContext(final int rowCount) {
	    this.rowCount = rowCount;
	}

	//return the next row of matrix
	public synchronized int nextRowNum() {
	    if (isFullyProcessed()) {
		throw new IllegalStateException("Already fully processed");
	    }
	    return nextRow++;
	}

	public synchronized boolean isFullyProcessed() {
	    return nextRow == rowCount;
	}
    }
}
