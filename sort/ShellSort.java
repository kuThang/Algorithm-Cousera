/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class ShellSort {
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int step = 1;
        int N = a.length;

        while (step < N/3) step = step * 3 + 1;
        while (step >= 1) {
            for (int i = step; i < N; i ++) {
                for (int j = i; j >= step; j-= step) {
                    if (less(a[j], a[j - step])) {
                        exchange(a, j, j - step);
                    } else break;

                }
            }
            step = step / 3;
        }
    }

    private static void exchange(Comparable[]a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] =temp;
    }

    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 4, 1, 5, 10, 7, 2};
        ShellSort sort = new ShellSort();
        sort.sort(arr);
        System.out.println("After sorting  int : "+ Arrays.toString(arr));

        Double [] arr2 = { 3.4, 3.2, 3.3, 1.2, 1.9, 8.3, 7.3};
        sort.sort(arr2);
        System.out.println("After sorting  double : "+ Arrays.toString(arr2));
    }
}
