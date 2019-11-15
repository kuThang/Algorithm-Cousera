/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Comparator;

public class MergeSortWithComparator {
    private static void merge(Comparator comparator, Object[] a, Object[] aux, int low, int mid, int high) {
        for (int i = low; i <= high ; i++) {
            aux[i] = a[i];
        }
        int i = low;
        int j = mid + 1;
        for(int k = low; k <= high; k++) {
            if(i > mid)                  a[k] = aux[j++];
            else if(j > high)            a[k] = aux[i++];
            else if(less(comparator, aux[i],aux[j])) a[k] = aux[i++];
            else                         a[k] = aux[j++];
        }
    }

    public static <Key extends Comparable<Key>> void sort(Object[] a, Comparator comparator)  {
        Object[] aux = new Object[a.length];
        int N = a.length;
        int sz = 1;
        while(sz < N) {
            for(int low = 0; low < N - sz; low += 2*sz) {
                merge(comparator, a, aux, low, low + sz - 1, Math.min(low + 2*sz - 1, N-1));
            }
            sz = 2*sz;
        }
    }

    private static <Key extends Comparable<Key>> boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    public static void main(String[] args) {
        // Integer[] arr = {3, 4, 1, 5, 10, 7, 2};
        // MergeSortBottomUp.sort(arr, );
        // System.out.println("After sorting  int : "+ Arrays.toString(arr));
        //
        // Double [] arr2 = { 3.4, 3.2, 3.3, 1.2, 1.9, 8.3, 7.3};
        // MergeSortBottomUp.sort(arr2);
        // System.out.println("After sorting  double : "+ Arrays.toString(arr2));
    }
}
