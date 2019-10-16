/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        // FOR TESTING IN INTELLIJ
        // int k = Integer.parseInt(args[0]);
        // In in = new In(args[2]);      // input file
        // while (!in.isEmpty()) {
        //     queue.enqueue(in.readString());
        // }

        // FOR SUBMIT
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String aaaa = StdIn.readString();
            queue.enqueue(aaaa);
        }

        while (k > 0) {
            k --;
            System.out.println(queue.dequeue());
        }
        // System.out.println()
    }
}
