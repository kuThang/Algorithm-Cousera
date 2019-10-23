/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>  {
    private Item[] _array;
    private int length = 0;
    private int N = 0;


    private class RandomizedQueueInterator implements Iterator<Item> {
        private int current = 0;
        private int[] index = StdRandom.permutation(N);
        private int startIdx = 0;

        @Override
        public boolean hasNext() {
            return !isEmpty() && startIdx != N;
        }

        @Override
        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            int rdn = index[startIdx];
            startIdx += 1;
            return _array[rdn];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        this._array = (Item[]) new Object[1];
        this.length = 1;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.N;
    }

    private void resize(int n) {
        Item[] newArray = (Item[])new Object[n];
        for (int i = 0; i < N; i++) {
            newArray[i] = this._array[i];
        }
        this._array = newArray;
        this.length = n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (this.N == this.length) {
            resize(this.length * 2);
        }
        this._array[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rdn = StdRandom.uniform(this.N);
        Item item = this._array[rdn];
        this._array[rdn] = this._array[N-1];
        this._array[N-1] = null;
        this.N --;
        if(this.N > 0 && this.N == (int)this.length/4) {
            resize((int)this.length/2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rdn = StdRandom.uniform(this.N);
        return this._array[rdn];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueInterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
