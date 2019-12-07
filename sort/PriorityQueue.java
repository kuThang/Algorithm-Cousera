/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class PriorityQueue<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;

    public PriorityQueue (int capacity) {
        pq = (Key []) new Comparable[capacity + 1];
    }

    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }

    private void swim(int k) {
        while ( k > 1 && less(k/2, k)) {
            exchange(k/2, k);
            k = k/2;
        }

    }

    private void sink(int k) {
        while ( 2*k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    private boolean less(int v, int w) {
        return pq[v].compareTo(pq[w]) < 0;
    }

    private void exchange(int v, int w) {
        Key item = pq[v];
        pq[v] = pq[w];
        pq[w] = item;
    }

    public Key delMax() {
        Key maxKey = pq[1];
        exchange(1, N--);
        sink(1);
        return maxKey;
    }

    public static void main(String[] args) {
        int n = 100;
        int[] array = StdRandom.permutation(100);
        int count = 0;
        Score[] scores = new Score[n];
        for (int i : array) {
            scores[i] = new Score(array[i]);
        }
        System.out.println("Before add to priority queue");
        while(count < 10) {
            System.out.println(scores[count].getScore());
            count ++;
        }
        PriorityQueue<Score> queue = new PriorityQueue<>(scores.length);
        for (Score s : scores) {
            queue.insert(s);
        }

        System.out.println("After insert to priority queue");
        count = 0;
        while(count < 10) {
            System.out.println(queue.delMax().getScore());
            count ++;
        }
    }
}
