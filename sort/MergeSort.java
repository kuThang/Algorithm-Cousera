/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class MergeSort {

    private static void merge( Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        for (int i = low; i <= high ; i++) {
            aux[i] = a[i];
        }
// low = 0, mid = 0, high = 1
        int i = low; // 0
        int j = mid + 1; // 1
        for(int k = low; k <= high; k++) {
            if(i > mid)                  a[k] = aux[j++];
            else if(j > high)            a[k] = aux[i++];
            else if(less(aux[i],aux[j])) a[k] = aux[i++];
            else                         a[k] = aux[j++];
        }
    }

    private static void sort( Comparable[] a, Comparable[] aux, int low, int high) {
        if(high <= low) return;
        int mid = low + (high - low) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, high);
        merge(a, aux, low, mid, high);
    }

    public static <Key extends Comparable<Key>> void sort(Key[] a)  {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0,  a.length - 1);
    }

    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 4, 1, 5, 10, 7, 2};
        MergeSort.sort(arr);
        System.out.println("After sorting  int : "+ Arrays.toString(arr));

        Double [] arr2 = { 3.4, 3.2, 3.3, 1.2, 1.9, 8.3, 7.3};
        MergeSort.sort(arr2);
        System.out.println("After sorting  double : "+ Arrays.toString(arr2));

    }
}
