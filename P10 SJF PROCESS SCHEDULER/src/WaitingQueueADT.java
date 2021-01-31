/**
 * This interface define the Abstract Data Type that represents the pattern for
 * our ready processes waiting list this is a generic within comparable
 * interface
 */
public interface WaitingQueueADT<T extends Comparable<T>> {

	/**
	 * inserts a newObject in the priority queue
	 * 
	 * @param newObject: newObject to be added to this queue.
	 */
	public void enqueue(T newObject);

	/**
	 * removes and returns the item with the highest priority
	 * 
	 * @return the item with the highest priority
	 */
	public T dequeue();

	/**
	 * returns without removing the item with the highest priority
	 * 
	 * @return the item with the highest priority
	 */
	public T peek();

	/**
	 * returns size of the waiting queue
	 * 
	 * @return size of the waiting queue
	 */
	public int size();

	/**
	 * checks if the waiting queue is empty
	 * 
	 * @return true if the queue is empty, false otherwise.
	 */
	public boolean isEmpty();
}
