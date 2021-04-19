package Lab2;

import java.util.*;
import Lab2.Bid;

// A priority queue.
public class PriorityQueue<E> {
    private ArrayList<E> heap = new ArrayList<E>();
    private Comparator<E> comparator;

    public PriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // Returns the size of the priority queue.
    public int size() {
        return heap.size();
    }

    // Adds an item to the priority queue.
    public void add(E x) {

    	if (x == null)
    		throw new IllegalArgumentException();

    	heap.add(x);
    	if (size() > 0) siftUp(size() - 1);
        System.out.println("added: " + x.toString() + " to " + comparator.getClass().getSimpleName() + " priority queue" );
        // System.out.println(heap);
//        throw new UnsupportedOperationException();
    }

    // Returns the smallest item in the priority queue.
    // Throws NoSuchElementException if empty.
    public E minimum() {
        if (size() == 0)
            throw new NoSuchElementException();

        return heap.get(0);
    }

    // Removes the smallest item in the priority queue.
    // Throws NoSuchElementException if empty.
    public E deleteMinimum() {
        if (size() == 0)
            throw new NoSuchElementException();

		E elem = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        if (heap.size() > 0) siftDown(0);
        return elem;
    }

    private void swap(int i, int j) {
    	E value1 = heap.get(i);
    	E value2 = heap.get(j);

    	heap.set(i, value2);
    	heap.set(j, value1);
	}

	public boolean change(E x1, E x2) {
    	if (delete(x1)) {
			add(x2);
			return true;
		}
    	return false;
	}

	private boolean delete(E x) {
		if (x == null)
			return false;
		if (!heap.contains(x))
			return false;

		return deleteAt(heap.indexOf(x)) != null;
	}


    private E deleteAt(int i) {

    	E itemToRemove = heap.get(i);
    	int indexOfLastElem = size() - 1;

    	if (itemToRemove.equals(heap.get(indexOfLastElem)))
    		return heap.remove(indexOfLastElem);

    	swap(heap.indexOf(itemToRemove), indexOfLastElem);
		heap.remove(indexOfLastElem);
		//sift down the element, that were at the last index to start with.
		siftDown(i);
    	return itemToRemove;
	}


    // Sifts a node up.
    // siftUp(index) fixes the invariant if the element at 'index' may
    // be less than its parent, but all other elements are correct.
    private void siftUp(int index) { // keep track of the comparison operator, could be the other way around
		E value = heap.get(index);

    	while (index > 0) {
    		E parentValue = heap.get(parent(index));
        	if (comparator.compare(value, parentValue) > 0 ) {
        		heap.set(index, parentValue);
        		index = parent(index);
			}
        	else break;
		}
    	heap.set(index, value);
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "heap=" + heap +
                '}';
    }

    public String sequentialOrder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++)
            sb.append(heap.get(i) + " ");
        return sb.toString();
    }

    // Sifts a node down.
    // siftDown(index) fixes the invariant if the element at 'index' may
    // be greater than its children, but all other elements are correct.
    private void siftDown(int index) {
        E value = heap.get(index);

        // Stop when the node is a leaf.
        while (leftChild(index) < heap.size()) {
            int left = leftChild(index);
            int right = rightChild(index);

            // Work out whether the left or right child is smaller.
            // Start out by assuming the left child is smaller...
            int child = left;
            E childValue = heap.get(left);

            // ...but then check in case the right child is smaller.
            // (We do it like this because maybe there's no right child.)
            if (right < heap.size()) {
                E rightValue = heap.get(right);
                if (comparator.compare(childValue, rightValue) < 0) {
                    child = right;
                    childValue = rightValue;
                }
            }

            // If the child is smaller than the parent,
            // carry on downwards.
            if (comparator.compare(value, childValue) < 0) {
                heap.set(index, childValue);
                index = child;
            } else break;
        }

        heap.set(index, value);
    }


    // Helper functions for calculating the children and parent of an index.
    private final int leftChild(int index) {
        return 2 * index + 1;
    }

    private final int rightChild(int index) {
        return 2 * index + 2;
    }

    private final int parent(int index) {
        return (index - 1) / 2;
    }
}
