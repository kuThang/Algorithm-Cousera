/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class ShellShort {
    public static void sort(int[] a) {
        System.out.println("***************:");
        int step = 1;
        int N = a.length;

        while (step < N/3) step = step * 3 + 1;
        while (step >= 1) {
            System.out.println("step " + step);
            for (int i = step; i < N; i ++) {
                for (int j = i; j >= step; j-= step) {
                    if (less(a[j], a[j - step])) {
                        exchange(a, j, j - step);
                    } else break;

                }
            }
            step = step / 3;
        }
        System.out.println("***************:");
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
