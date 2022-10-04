package campuspath.pathfind.astar.set;

import campuspath.pathfind.astar.OpenSet;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;

/**
 * @author Brady
 */
public class BinaryHeapOpenSet<T extends BinaryHeapNode<T>> implements OpenSet<T> {

    /**
     * 0-indexed backing array for the heap
     */
    private T[] elements;

    /**
     * The current size of the heap
     */
    private int size;

    public BinaryHeapOpenSet(IntFunction<T[]> generator) {
        this(generator, 256);
    }

    public BinaryHeapOpenSet(IntFunction<T[]> generator, int initialCapacity) {
        this.elements = generator.apply(initialCapacity);
    }

    @Override
    public void select(T node) {
        if (!node.isOpen()) {
            // Double the size of the underlying array to fit new capacity requirement
            if (this.size >= this.elements.length - 1) {
                this.elements = Arrays.copyOf(this.elements, this.elements.length << 1);
            }

            // Place the node in the last position and fix the heap invariant
            node.setOpen(true);
            this.setElement(this.size, node);
            this.size++;
        }
        this.heapifyUp(node);
    }

    @Override
    public T popCheapest() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        var cheapest = this.elements[0];
        cheapest.setOpen(false);
        cheapest.setIndex(-1);

        var last = this.elements[this.size - 1];
        this.setElement(0, last);
        this.size--;

        this.heapifyDown(last);

        return cheapest;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void heapifyUp(T node) {
        int index = node.getIndex();
        var estimate = node.getEstimate();
        while (index != 0) {
            int parentIndex = (index - 1) / 2;
            var parent = this.elements[parentIndex];

            if (parent.getEstimate() <= estimate) {
                break;
            }

            // Swap and update index
            this.elements[index] = parent;
            parent.setIndex(index);

            index = parentIndex;
        }

        // Update node with its new index
        this.elements[index] = node;
        node.setIndex(index);
    }

    private void heapifyDown(T node) {
        int index = node.getIndex();
        var estimate = node.getEstimate();
        int leftIndex;
        while ((leftIndex = 2 * index + 1) < this.size) {
            var rightIndex = leftIndex + 1;

            var smaller = this.elements[leftIndex];
            var smallerIndex = leftIndex;
            var smallerEstimate = smaller.getEstimate();

            // If there is a right child
            if (rightIndex < this.size) {
                var right = this.elements[rightIndex];
                var rightEstimate = right.getEstimate();
                if (rightEstimate < smallerEstimate) {
                    smaller = right;
                    smallerIndex = rightIndex;
                    smallerEstimate = rightEstimate;
                }
            }

            // The node is in the correct spot, break
            if (estimate <= smallerEstimate) {
                break;
            }

            // Move the smaller node into the current index
            this.elements[index] = smaller;
            smaller.setIndex(index);

            index = smallerIndex;
        }

        // Update node with its new index
        this.elements[index] = node;
        node.setIndex(index);
    }

    private void setElement(int index, T node) {
        this.elements[index] = node;
        node.setIndex(index);
    }
}
