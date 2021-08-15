public class ConcurrentSumArrayTask implements Runnable {

    private static int[] arr;
    private final int[] arrProduct;
    //Here we will monitor our program by m process and sum 2 elements each time from the array
    private final ConcurrencyContext context;

    public ConcurrentSumArrayTask(final ConcurrencyContext context, final int[] A, final int B[]) {
	if (context == null) {
	    throw new IllegalArgumentException("Context can not be null");
	}
	this.context = context;
	ConcurrentSumArrayTask.arr = A;
	this.arrProduct = B;
    }

    @Override
    public void run() {
	while (true) {
	    int element;
	    synchronized (context) {
		if (context.isFullyProcessed()) {
		    break;
		}
		element = context.nextElementNum();
	    }
	    System.out.println(Thread.currentThread().getName() + " is working on element " + element);
	    final int j = element + 1;
	    arrProduct[element] += sumRemove(element, j); //calculte the sum of 2 elemnt (elemnt +j)
	    arr[0] += arrProduct[element]; // we add the same to the first elemnt in the orignal array
	}
    }

    public int sumRemove(final int left, final int right) { // we sum between 2 elemnt and remove them after (set 0)
	final int sum = arr[right] + arr[left];
	arr[right] = arr[left] = 0;
	return sum;
    }

    public static class ConcurrencyContext {

	private final int elementCount;
	private int nextElement = 0;

	public ConcurrencyContext(final int elementCount) {
	    this.elementCount = elementCount;
	}

	public synchronized int nextElementNum() { // return the next elemnt to sum
	    if (isFullyProcessed()) {
		throw new IllegalStateException("Already fully processed");
	    }

	    return nextElement++;
	}

	public synchronized boolean isFullyProcessed() {
	    return nextElement == elementCount - 1; // the last element is already include in j (elemnt+1) so the last one will be n-1
	}
    }
}
