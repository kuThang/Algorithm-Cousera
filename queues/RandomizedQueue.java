/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int length = 0;

    private class Node {
        Item item;
        Node next;
        Node before;
    }

    private class RandomizedQueueInterator implements  Iterator<Item> {
        private Node current = first;
        private int[] index = StdRandom.permutation(length);
        private int startIdx = 0;

        @Override
        public boolean hasNext() {
            return !isEmpty() && startIdx != length;
        }

        @Override
        public Item next() {
            if(isEmpty() || startIdx >= length) {
                throw new NoSuchElementException();
            }
            int rdn = index[startIdx];
            startIdx += 1;
            Node node = current;
            while(rdn != 0) {
                node = node.next;
                rdn -=1;
            }
            return node.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.length == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.length;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.before = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        length += 1;
    }

    private Item dequeueFirst() {
        Item toRemove = first.item;
        first = first.next;
        return toRemove;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rdn = StdRandom.uniform(length);
        length -= 1;
        Item toRemove = null;

        if (isEmpty()) {
            toRemove = first.item;
            first = new Node();
            last = new Node();
            return toRemove;
        }
        if (rdn == 0) {
            return dequeueFirst();
        } else {
            Node iter  = first;
            Node before = null;
            while(rdn != 0) {
                before = iter;
                iter = iter.next;
                rdn --;
            }
            before.next = iter.next;
            toRemove = iter.item;
        }
        return toRemove;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rdn = StdRandom.uniform(length);
        Node iter  = first;
        while (rdn != 0) {
            iter = iter.next;
            rdn --;
        }
        return iter.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueInterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
