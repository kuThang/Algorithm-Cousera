/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class SelectionSort {
    public static void sort(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }

    private static void exchange(int[]a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] =temp;
    }

    private static boolean less(Integer v, Integer w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {

    }
}
