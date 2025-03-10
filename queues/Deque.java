/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int length = 0;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private class DequeInterator implements  Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Deque() { }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public int size() {
        return length;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        length += 1;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        length += 1;
    }

    public Item removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        length -= 1;
        Item toRemove;
        if(isEmpty()) {
            toRemove = last.item;
            last = null;
            first = null;
        } else {
            toRemove = first.item;
            first = first.next;
            first.previous = null;
        }
        return toRemove;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        length -= 1;
        Item toRemove;
        if (isEmpty()) {
            toRemove = last.item;
            last = null;
            first = null;
        } else {
            toRemove = last.item;
            last = last.previous;
            last.next = null;
        }
        return toRemove;
    }

    public Iterator<Item> iterator() {
        return new DequeInterator();
    }

    public static void main(String[] args) {

    }
}
