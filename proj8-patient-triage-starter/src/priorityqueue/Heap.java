package priorityqueue;

import java.util.Comparator;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;

  /**
   * Constructor for the heap.
   * @param comparator comparator object to define a sorting order for the heap elements.
   * @param isMaxHeap Flag to set if the heap should be a max heap or a min heap.
   */
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
      //TODO: Implement this method.
      this.comparator = comparator;
      this.isMaxHeap = isMaxHeap;
      heap = (T[]) new Object[INIT_SIZE];
      this.numElements = 0;
  }
  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained. This method should run in
   * O(log(size)) time.
   * Note: When enqueue is called, an entry is placed at the next available index in 
   * the array and then this method is called on that index. 
   *
   * @param index the index to bubble up
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleUp(int index) {
      //TODO: Implement this method.
      if (index > getSize() || index < 0) {
        throw new IndexOutOfBoundsException();
      }
      while (index > 0) {
        int parentIndex = getPar(index);
        if (compareElements(heap[index],heap[parentIndex]) < 0)
           return;
        else {
           swap(index,parentIndex);
           index = parentIndex;
           bubbleUp(parentIndex);
        }
     }
  }
  
  private int getLeft(int parentIndex) {
    int leftC = 2*parentIndex + 1;
    return leftC;
  }
  private int getRight(int parentIndex) {
    int rightC = 2*parentIndex + 1;
    return rightC;
  }
  private int getPar(int childIndex) {
    int par = (childIndex-1)/2;
    return par;
  }
  private void swap(int i1, int i2) {
    T curr = heap[i1];
    heap[i1] = heap[i2];
    heap[i2] = curr;
  }
  private void expandCapacity() {
    T[] heap2 = (T[]) new Object[getSize()*2];
    for (int i =0; i < getSize(); i++) {
      heap2[i] = heap[i]; 
    }
    heap = heap2;
  }
  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time.
   * Note: When remove is called, if there are elements remaining in this
   *  the bottom most element of the heap is placed at
   * the 0th index and bubbleDown(0) is called.
   * 
   * @param index
   * @throws IndexOutOfBoundsException if invalid index
   */

  public void bubbleDown(int index) {
    //TODO: Implement this method.
    if (index > getSize() || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    int childIndex = getLeft(index);
    T value = heap[index];


    while (childIndex < getSize()) {
      // Find the max among the node and all the node's children
      T maxValue = value;
      int maxIndex = -1;
      for (int i = 0; i < 2 && i + childIndex < getSize(); i++) {
         if (compareElements(heap[i + childIndex], maxValue) > 0) {
            maxValue = heap[i + childIndex];
            maxIndex = i + childIndex;
         }
      }
      if (value == maxValue) {
         return;
      }
      else {
         swap(index,maxIndex);
         index = maxIndex;
         childIndex = getLeft(index);
      }
   }
}

  /**
   * Test for if the queue is empty.
   * @return true if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    boolean isEmpty = false;
      //TODO: Implement this method.
      if(heap[0] == null) {
        isEmpty = true;
        return isEmpty;
      }
      return isEmpty;
  }

  /**
   * Number of data elements in the queue.
   * @return the size
   */
  public int getSize(){
    int size = 0;
      //TODO: Implement this method.
    size = numElements;
    return size;
  }

  /**
   * Compare method to implement max/min heap behavior. It changes the value of a variable, compareSign, 
   * based on the state of the boolean variable isMaxHeap. It then calls the compare method from the 
   * comparator object and multiplies its output by compareSign.
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * negative int otherwise (if isMaxHeap),
   * return negative int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * positive int otherwise (if ! isMinHeap).
   */
  public int compareElements(T element1 , T element2) {
    int result = 0;
    int compareSign =  -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap 
   * without removing the element.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T peek() throws QueueUnderflowException {
     T data = null;
      //TODO: Implement this method.
      if (heap[0] == null) {
        throw new QueueUnderflowException();
      }
      data = heap[0];
      return data;
  }  

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority in the heap.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T dequeueElement() throws QueueUnderflowException{
    T data = null;
    //TODO: Implement this method.
    if (isEmpty()) {
      throw new QueueUnderflowException();
    }
    int temp = getSize() - 1;
    data = peek();
    heap[0] = heap[temp];
    heap[temp] = null; 
    numElements--;
    bubbleDown(0);
    return data;
  }

  /**
   * Enqueue the element.
   * @param the new element
   */
  public void enqueueElement(T newElement) {
      //TODO: Implement this method.
      if (isEmpty()) {
        heap[0] = newElement;
      }
      else {
        if (getSize() == heap.length) {
          expandCapacity();
        }
        heap[getSize()] = newElement;
        bubbleUp(getSize()); 
      }
  
      numElements++;    
  }

}