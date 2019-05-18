package es.datastructur.synthesizer;



//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int) Math.round((SR / frequency)));
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //
        //       Make sure that your random numbers are different from each
        //       other.
        double[] r = new double[buffer.capacity()];
        double temp;
        for (int k = 0; k < r.length; k++) {
            temp = Math.random() - 0.5;
            for (int j = 0; j < k; j++) {
                if (temp == r[j]) {
                    temp = Math.random() - 0.5;
                    j = 0;
                }
            }
            r[k] = temp;
        }
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        int index = 0;
        while (!buffer.isFull()) {
            buffer.enqueue(r[index]);
            index += 1;
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double oldVal = buffer.dequeue();

        double newVal = ((oldVal + buffer.peek()) / 2) * DECAY;
        buffer.enqueue(newVal);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        if (!buffer.isEmpty()) {
            return buffer.peek();
        }
        return 0.0;
    }
}

