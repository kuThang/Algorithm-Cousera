/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

public class test {
    public static void main(String[] args) {
        // Deque <Integer> deque = new Deque<Integer>();
        // deque.addFirst(1);
        // if(deque.iterator().hasNext()) {
        //     System.out.println("hasNext after addFirst(1)");
        // }
        // deque.addFirst(2);
        // if(deque.iterator().hasNext()) {
        //     System.out.println("hasNext after addFirst(2)");
        // }
        // deque.removeLast();
        // if(deque.iterator().hasNext()) {
        //     System.out.println("hasNext after removeLast(1)");
        // }
        // deque.removeLast();
        // if(deque.iterator().hasNext()) {
        //     System.out.println("hasNext after removeLast(2)");
        // }

        // -----------------------
        // RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        //
        // rq.size();
        // System.out.println(rq.size());
        // int rdn = StdRandom.uniform(1);
        // rq.enqueue(129);
        // System.out.println("enqueue");
        // rq.dequeue();
        // System.out.println("dequeue");

        //--------------------
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        // FOR TESTING IN INTELLIJ
        int k = Integer.parseInt(args[0]);
        In in = new In(args[2]);      // input file
        while (!in.isEmpty()) {
            queue.enqueue(in.readString());
        }
        int [] count = new int[4];
        for(int i = 0; i < 12000; i++) {
            String a = queue.sample();
            // System.out.println(queue.sample());
            if (a.equals("A"))  {
                count[0] += 1;
            } else if (a.equals("B"))  {
                count[1] += 1;
            } else if (a.equals("C"))  {
                count[2] += 1;
            } else if (a.equals("D"))  {
                count[3] += 1;
            }
        }

        for(int i = 0; i < count.length; i++) {
            String a = queue.sample();
            System.out.println("count i = " + i + " is " + count[i]);
        }

    }
}
