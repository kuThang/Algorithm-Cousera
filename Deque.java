/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int length = 0;

    private class Node {
        Item item;
        Node next;
    }
    private class DequeInterator implements  Iterator<Item> {
        @Override
        public boolean hasNext() {
            return true;
        }
        @Override
        public Item next(){
            return null;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public Deque(){

    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return length;
    }

    public void addLast(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
        length += 1;
        Node newNode = new Node();
        newNode.item = item;
        last.next = newNode;
        last = newNode;
    }
    public void addFirst(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
        length += 1;
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item removeFirst(){
        length -= 1;
        Item toRmove = first.item;
        first = first.next;
        return toRmove;
    }

    public Item removeLast(){
        length -= 1;
        return null;
    }

    public Iterator<Item> iterator(){
        return new DequeInterator();
    }

    public static void main(String[] args) {

    }
}
