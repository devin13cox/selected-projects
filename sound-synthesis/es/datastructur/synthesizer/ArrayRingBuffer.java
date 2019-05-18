package es.datastructur.synthesizer;
import java.lang.reflect.Type;
import java.util.Iterator;



public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;


    @Override
    public int capacity() {
        return rb.length;
    }
    @Override
    public int fillCount() {
        return fillCount;
    }



    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last += 1;
        if (last == rb.length) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T one = rb[first];
        first += 1;
        fillCount -= 1;
        if (first == rb.length) {
            first = 0;
        }
        if (first == last) {
            if (rb.length != last + 1) {
                last += 1;
            }
            last = 0;
        }
        return one;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T one = rb[first];
        return one;
    }

    public Iterator<T> iterator() {
        return new BoundedIterator();
    }

    private class BoundedIterator implements Iterator<T> {
        private int pos;
        BoundedIterator() {
            pos = first;
        }

        @Override
        public boolean hasNext() {
            return pos != last;
        }
        public T next() {
            T returnItem = rb[pos];
            if (pos + 1 == rb.length) {
                pos = 0;
            } else {
                pos += 1;
            }
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        ArrayRingBuffer other = (ArrayRingBuffer) (o);
        if (this.fillCount() !=  other.fillCount()) {
            return false;
        }
        while (this.iterator().hasNext() && other.iterator().hasNext()) {
            if (this.iterator().next() != other.iterator().next()) {
                return false;
            }
        }
        return true;
    }

}

