/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class InsertionSort {
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exchange(a, j, j - 1);
                } else break;
            }
        }
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] =temp;
    }

    private static <Key extends Comparable<Key>>  boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 4, 1, 5, 10, 7, 2};
        InsertionSort sort = new InsertionSort();
        sort.sort(arr);
        System.out.println("After sorting  int : "+ Arrays.toString(arr));

        Double [] arr2 = { 3.4, 3.2, 3.3, 1.2, 1.9, 8.3, 7.3};
        sort.sort(arr2);
        System.out.println("After sorting  double : "+ Arrays.toString(arr2));
    }
}