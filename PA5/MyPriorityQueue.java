/*
 * Name: TODO
 * PID:  TODO
 */

/**
 * Priority Queue Implementation using dHeap.
 *
 * @author TODO
 * @since TODO
 *
 * @param <T> the type of elements held in this collection
 */

 public class MyPriorityQueue<T extends Comparable<? super T>> {

    private dHeap<T> pQueue;

    /**
     * Constructor that creates a new priority queue
     *
     * @param initialSize the given size
     */
    public MyPriorityQueue(int initialSize) {
        // TODO
    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot be
     * null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer(T element) throws NullPointerException {
        // TODO
        return false;
    }

    /**
     * Retrieve and remove the head of this Priority Queue (smallest element), or null if the
     * queue is empty.
     *
     * @return The head of the queue (smallest element), or null if queue is empty.
     */
    public T poll() {
        // TODO
        return null;
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        // TODO
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if
     * this queue is empty.
     *
     * @return the head of the queue, null if the queue is empty
     */
    public T peek() {
        // TODO
        return null;
    }

    /**
     * Return true is the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        // TODO
        return false;
    }

}