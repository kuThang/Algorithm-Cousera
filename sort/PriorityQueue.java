/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

public class PriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    private void swim(int k) {
        while(k > 1 && less(k/2, k)) {
            exchange(k, k/2);
            k = k/2;
        }
    }

    private void exchange(int i, int j) {
        Key k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public PriorityQueue(int capacity) {
        pq = (Key []) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exchange(1, N --);
        sink(1);
        pq[N + 1] = null;
        return max;
    }

    private static Score[] createData(int n) {
        int[] array = StdRandom.permutation(n);
        Score[] scores = new Score[n];
        for (int i : array) {
            scores[i] = new Score(array[i]);
        }
        return scores;
    }

    private static void printValue(Score[] a, int k) {
        int count = 0;
        while(count < k) {
            System.out.println(a[count].getScore());
            count ++;
        }
    }

    public static void main(String[] args) {
        int n = 100;
        int[] array = StdRandom.permutation(100);
        Score[] scores = new Score[n];
        for (int i : array) {
            scores[i] = new Score(array[i]);
        }
        System.out.println("Before add to priority queue");
        int count = 0;
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
