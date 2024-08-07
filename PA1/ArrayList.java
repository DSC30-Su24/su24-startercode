public class ArrayList<E> {
    private E[] list;
    private int size;

    /**
     * Constructs an empty ArrayList with an initial capacity of 8.
     */
    public ArrayList() {
        elementData = new E[8];
        size = 0;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index the index at which the element should be inserted
     * @param element the element to be inserted
     */
    public void insert(int index, E element) {
        if (size == elementData.length) {
            resize();
        }
        
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to be added
     */
    public void add(E element) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size++] = element;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     */
    public void remove(int index) {
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null; 
        size--;
    }

    /**
     * Removes the element at the specified position in this list and returns it.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed
     */
    public E pop(int index) {
        E element = elementData[index];
        remove(index);
        return element;
    }

    /**
     * Doubles the size of the array to accommodate more elements.
     */
    private void resize() {
        int newCapacity = elementData.length * 2;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     */
    public E get(int index) {
        return elementData[index];
    }

    /**
     * Returns the string representation of this ArrayList
     *
     * @return a string where the ArrayList elements are enclosed in square brackets
     *         and comma-separated
     */
    public String toString() {
        if (size == 0){
            return "[]";
        }
        String result = "[" + elementData[0];
        for (int i = 1; i < size; i++) {
            result += ", " + elementData[i];
        }
        result += "]";
        return result;
    }
}

