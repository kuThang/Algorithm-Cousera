/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class SelectionSort {
    public static <Key extends Comparable<Key>> void sort(Key[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            int min = i;
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min]))
                    min = j;
            exch(a, i, min);
        }
    }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        Integer[] arr = {3, 4, 1, 5, 10, 7, 2};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        System.out.println("After sorting  int : "+ Arrays.toString(arr));

        Double [] arr2 = { 3.4, 3.2, 3.3, 1.2, 1.9, 8.3, 7.3};
        selectionSort.sort(arr2);
        System.out.println("After sorting  double : "+ Arrays.toString(arr2));

    }

}
