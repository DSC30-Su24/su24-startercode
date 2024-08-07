public class SinglyLinkedList<E> {
    private Node<E> head; // Reference to the first node in the list
    private int size;     // Number of elements in the list

    /**
     * Constructs an empty {@code SinglyLinkedList}.
     */
    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts subsequent elements to the right.
     *
     * @param index the index at which the element should be inserted
     * @param element the element to be inserted
     */
    public void insert(int index, E element) {
        if (index == 0) {
            head = new Node<>(element, head);
        } else {
            Node<E> current = head;
            int currentIndex = 0;
            while (currentIndex < index - 1) {
                current = current.next;
                currentIndex++;
            }
            current.next = newNode<>(element, current.next);
        }
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to be added
     */
    public void add(E element) {
        if (size == 0) {
            head = new Node<>(element, null);
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(element, null);
        }
        size++;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     */
    public void remove(int index) {
        if (index == 0) {
            head = head.next;
        } else {
            Node<E> current = head;
            int pos = 0;
            while (pos < index - 1) {
                current = current.next;
                pos++;
            }
            current.next = current.next.next;
        }
        size--;
    }

    /**
     * Removes the element at the specified position in this list and returns it.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed
     */
    public E pop(int index) {
        E element;
        if (index == 0) {
            element = head.element;
            head = head.next;
        } else {
            Node<E> current = head;
            int pos = 0;
            while (pos < index - 1) {
                current = current.next;
                pos++;
            }
            element = current.next.element;
            current.next = current.next.next;
        }
        size--;
        return element;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    public String toString() {
        if (size == 0){
            return "[]";
        }
        String result = "[" + head.data;
        Node<T> current = head;
        while (current.next != null) {
            result += ", " + current.data;
        }
        result += "]";
        return result;
    }
}

