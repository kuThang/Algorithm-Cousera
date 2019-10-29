/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class InsertionSort {
    public static void sort(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exchange(a, j, j - 1);
                } else break;
            }
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
