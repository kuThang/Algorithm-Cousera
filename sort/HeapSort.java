/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class HeapSort {

    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;
        for (int k = N/2; k >= 1; k--) sink(a, k, N);

        while (N > 1) {
            exchange(a, 1, N);
            sink(a, 1, --N);
        }

    }

    private static <Key extends Comparable<Key>> void sink ( Key[] a, int k, int N) {
        while (2*k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j+1)) j++;
            if (!less(a, k, j)) break;
            exchange(a, k, j);
            k = j;
        }
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j -1];
        a[j - 1] =temp;
    }

    private static <Key extends Comparable<Key>>  boolean less(Key[] a, int v, int w) {
        return a[v-1].compareTo(a[w-1]) < 0;
    }


    public static void main(String[] args) {
        Integer[] arr = {3, 4, 1, 5, 10, 7, 2};
        new HeapSort().sort(arr);
        System.out.println("After sorting  int : "+ Arrays.toString(arr));

        Double [] arr2 = { 3.4, 3.2, 3.3, 1.2, 1.9, 8.3, 7.3};
        new HeapSort().sort(arr2);
        System.out.println("After sorting  double : "+ Arrays.toString(arr2));
    }
}
