/*
 * Name: TODO
 * PID:  TODO
 */

 import java.util.Arrays;
 import java.util.NoSuchElementException;
 
 /**
  * Title: dHeap Description: This program creates a Heap with d branching factor
  *
  * @author TODO
  * @since TODO
  *
  * @param <T> the type of elements held in this collection
  */
 
 public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {
 
     private T[] heap;   // backing array
     private int d;      // branching factor
     private int nelems; // number of elements
     private boolean isMaxHeap; // indicates whether heap is max or min
 
     /**
      * Initializes a binary max heap with capacity = 10
      */
     @SuppressWarnings("unchecked")
     public dHeap() {
         // TODO
     }
 
     /**
      * Initializes a binary max heap with a given initial capacity.
      *
      * @param heapSize The initial capacity of the heap.
      */
     @SuppressWarnings("unchecked")
     public dHeap(int heapSize) {
         // TODO
     }
 
     /**
      * Initializes a d-ary heap (with a given value for d), with a given initial
      * capacity.
      *
      * @param d         The number of child nodes each node in the heap should have.
      * @param heapSize  The initial capacity of the heap.
      * @param isMaxHeap indicates whether the heap should be max or min
      * @throws IllegalArgumentException if d is less than one.
      */
     @SuppressWarnings("unchecked")
     public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
         // TODO
     }
 
     @Override
     public int size() {
         // TODO
         return 0;
     }
 
     @Override
     public T remove() throws NoSuchElementException {
         // TODO
         return null;
     }
 
     @Override
     public void add(T item) throws NullPointerException {
         // TODO
     }
 
     @SuppressWarnings("unchecked")
     @Override
     public void clear() {
         // TODO
     }
 
     @Override
     public T element() throws NoSuchElementException {
         // TODO
         return null;
     }
 
     private int parent(int index) {
         // TODO
         return 0;
     }
 
     private void percolateUp(int index) {
         // TODO
     }
 
     private void percolateDown(int index) {
         // TODO
     }
 
     @SuppressWarnings("unchecked")
     private void resize() {
         // TODO
     }
 
 }