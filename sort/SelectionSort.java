/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class SelectionSort {
    // public static void sort(int[] a) {
    //     int N = a.length;
    //     for (int i = 0; i < N; i++) {
    //         int min = i;
    //         for (int j = i + 1; j < N; j++) {
    //             if (less(a[j], a[min])) {
    //                 min = j;
    //             }
    //         }
    //         exchange(a, i, min);
    //     }
    // }
    //
    // private static void exchange(int[]a, int i, int j) {
    //     int temp = a[i];
    //     a[i] = a[j];
    //     a[j] =temp;
    // }
    //
    // private static boolean less(Integer v, Integer w) {
    //     return v.compareTo(w) < 0;
    // }
    //

    public static <Key extends Comparable<Key>> void sort(Key[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            int min = i;
            for (int j = i+1; j < N; j++)
                if (a[j].compareTo(a[j-1]) < 0)
                    min = j;
            exch(a, i, min);
        }
    }

    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        int[] x = StdRandom.permutation(100);
        Integer[] m = new Integer[2];
        sort(m);

        int[] array = StdRandom.permutation(10);
        Score[] scores = new Score[10];
        for (int i : array) {
            scores[i] = new Score(array[i]);
        }
        // printValue(array, 10);
        System.out.println("---");
        sort(scores);
        // show(a);
        // assert isSorted(a);
    }

}
