/**
 * The class is used to test the functions of ProcessScheduler. This class has
 * multiple test methods, each test focuses on a specific method, check whether
 * the result is exactly what we expect.
 */
public class ProcessSchedulerTests {

	/**
	 * checks the correctness of the enqueue operation implemented in the
	 * CustomProcessQueue class
	 * 
	 * @return true if pass the test, false otherwise
	 */
	public static boolean testEnqueueCustomProcessQueue() {
		CustomProcessQueue testQueue = new CustomProcessQueue();
		CustomProcess task1 = new CustomProcess(1);
		CustomProcess task2 = new CustomProcess(2);
		CustomProcess task3 = new CustomProcess(2);
		testQueue.enqueue(task3);
		testQueue.enqueue(task2);
		testQueue.enqueue(task1);

		if (testQueue.isEmpty()) // queue should not be empty now
			return false;
		if (testQueue.peek().getProcessId() != 1 || testQueue.peek().getBurstTime() != 1)
			return false;
		return true;
	}

	/**
	 * checks the correctness of the dequeue operation implemented in the
	 * CustomProcessQueue class
	 * 
	 * @return true if pass the test, false otherwise
	 */
	public static boolean testDequeueCustomProcessQueue() {
		CustomProcessQueue testQueue = new CustomProcessQueue();
		CustomProcess task1 = new CustomProcess(1);
		CustomProcess task2 = new CustomProcess(2);
		CustomProcess task3 = new CustomProcess(2);
		testQueue.enqueue(task1);
		testQueue.enqueue(task3);
		testQueue.enqueue(task2);
		testQueue.dequeue();
		testQueue.dequeue();

		if (testQueue.isEmpty()) // queue should not be empty now
			return false;
		if (testQueue.peek().getProcessId() != 6 || testQueue.peek().getBurstTime() != 2)
			return false;
		return true;
	}

	/**
	 * checks the correctness of the size operation implemented in the
	 * CustomProcessQueue class
	 * 
	 * @return true if pass the test, false otherwise
	 */
	public static boolean testSize() {
		CustomProcessQueue testQueue = new CustomProcessQueue();
		CustomProcess task1 = new CustomProcess(1);
		CustomProcess task2 = new CustomProcess(2);
		CustomProcess task3 = new CustomProcess(2);
		testQueue.enqueue(task3);
		testQueue.enqueue(task2);
		testQueue.enqueue(task1);

		if (testQueue.size() != 3) // size should be 3
			return false;
		return true;
	}

	/**
	 * checks the correctness of the isEmpty operation implemented in the
	 * CustomProcessQueue class
	 * 
	 * @return true if pass the test, false otherwise
	 */
	public static boolean testIsEmpty() {
		CustomProcessQueue testQueue = new CustomProcessQueue();
		CustomProcess task1 = new CustomProcess(1);
		CustomProcess task2 = new CustomProcess(2);
		testQueue.enqueue(task1);
		testQueue.enqueue(task2);
		testQueue.dequeue();
		testQueue.dequeue();

		if (!testQueue.isEmpty()) // queue should be empty
			return false;
		return true;
	}

	/**
	 * Testing main. Runs each test and prints which (if any) failed. If no problem
	 * occurs, print a single line showing "All tests passed!".
	 */
	public static void main(String[] args) {
		boolean passed = true;
		if (!testEnqueueCustomProcessQueue()) {
			System.out.println("testEnqueueCustomProcessQueue failed");
			passed = false;
		}
		if (!testDequeueCustomProcessQueue()) {
			System.out.println("testEnqueueCustomProcessQueue failed");
			passed = false;
		}
		if (!testSize()) {
			System.out.println("testSize failed");
			passed = false;
		}
		if (!testIsEmpty()) {
			System.out.println("testIsEmpty failed");
			passed = false;
		}
		// If no problem occurs, print a single line showing "All tests passed!".
		if (passed)
			System.out.println("All tests passed!");
	}
}
