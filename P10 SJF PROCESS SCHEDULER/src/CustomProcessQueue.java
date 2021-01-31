/**
 * The CustomProcessQueue class represents an array-based min heap containing
 * processes. This class implement WaitingQueueADT interface of CustomProcesses.
 */
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess> {

	private static final int INITIAL_CAPACITY = 20; // the initial capacity of the heap
	private CustomProcess[] heap; // array-based min heap storing the data. This is an oversize array
	private int size; // number of CustomProcesses present in this CustomProcessQueue
	private int capacity; // the capacity of the heap, it will be expanded once run out

	/**
	 * This constructor creates a new instance of CustomProcessQueue with an empty
	 * array. Initial capacity of the array is given in the final field. Initial
	 * size is 0.
	 */
	public CustomProcessQueue() {
		heap = new CustomProcess[INITIAL_CAPACITY + 1]; // index start from 1, so array size + 1
		size = 0; // initial size is 0
		capacity = INITIAL_CAPACITY;
	}

	/**
	 * inserts a newObject in the priority queue
	 * 
	 * @param newObject: a new task
	 */
	@Override
	public void enqueue(CustomProcess newObject) {
		size++;
		if (size > capacity) { // expand the array if it is full by copying elements to a new array
			capacity *= 2;
			CustomProcess[] newheap = new CustomProcess[capacity + 1];
			for (int i = 1; i <= size - 1; i++)
				newheap[i] = heap[i];
			heap = newheap;
		}
		heap[size] = newObject; // add the newObject to the end of the heap
		minHeapPercolateUp(size); // after adding, we should percolate up this newObject
	}

	/**
	 * removes and returns the item with the highest priority
	 * 
	 * @return the item with the highest priority
	 */
	@Override
	public CustomProcess dequeue() {
		if (size == 0) // check empty first
			return null;
		CustomProcess result = heap[1]; // store the peak
		heap[1] = heap[size]; // replace the peak with the last element of the heap
		heap[size] = null; // set last element to null
		size--; // size decrement 1
		minHeapPercolateDown(1); // after replacing, we should percolate up this newObject
		return result;
	}

	/**
	 * returns without removing the item with the highest priority
	 * 
	 * @return the item with the highest priority
	 */
	@Override
	public CustomProcess peek() {
		if (size == 0) // check empty first
			return null;
		return heap[1];
	}

	/**
	 * returns size of the waiting queue
	 * 
	 * @return size of the waiting queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * checks if the waiting queue is empty
	 * 
	 * @return true if the queue is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * This method bubble up the element at the given index in the min heap
	 * 
	 * @param index: starting index of percolateUp process
	 */
	private void minHeapPercolateUp(int index) {
		while (index > 1) { // percolate up the last element, if it has higher priority than parent
			// swap it with its parent, then do it again until its parent has higher
			// priority
			int parentIndex = index / 2;
			if (heap[parentIndex].compareTo(heap[index]) > 0) {
				swap(parentIndex, index);
				index = parentIndex;
			} else {
				return;
			}
		}
	}

	/**
	 * self-defined swap method
	 * 
	 * @param index1: one index we want to swap
	 * @param index2: the other index we want to swap
	 */
	private void swap(int index1, int index2) {
		CustomProcess temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}

	/**
	 * This method bubble down the element at the given index in the min heap
	 * 
	 * @param index: starting index of percolateDown process
	 */
	private void minHeapPercolateDown(int index) {
		int childIndex = index * 2;
		// percolate down the first element, swap it with the child with the higest
		// priority
		while (childIndex <= size) {
			int minIndex = -1;
			CustomProcess higestPriority = heap[index];
			for (int i = 0; i < 2 && i + childIndex <= size; i++) { // check each child
				if (heap[i + childIndex].compareTo(higestPriority) < 0) {
					minIndex = i + childIndex;
					higestPriority = heap[minIndex];
				}
			}
			if (minIndex == -1) // if not swap is made, finish the percolating process
				return;
			else {
				swap(index, minIndex);
				index = minIndex;
				childIndex = 2 * index;
			}
		}
	}
}
