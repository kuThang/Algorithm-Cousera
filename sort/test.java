/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class test {
    private static int[] createArray(int n){
        int[] array = StdRandom.permutation(n);
        return array;
    }

    private static void printValue(int[] a, int k) {
        int count = 0;
        while(count < k) {
            System.out.println(a[count]);
            count ++;
        }
    }
    private static void testInsertionSort(int n){
        int[] array = createArray(n);
        // printValue(array, 10);
        System.out.println("---");
        Stopwatch stw = new Stopwatch();
        InsertionSort.sort(array);
        double times = stw.elapsedTime();
        System.out.println("InsertionSort n = " + n + ", time in second \t\t" + times);
        // printValue(array, 10);
    }
    private static void testSelectionSort(int n){
        // int[] array = createArray(n);
        int[] array = StdRandom.permutation(n);
        Score[] scores = new Score[n];
        for (int i : array) {
            scores[i] = new Score(array[i]);
        }
        // printValue(array, 10);
        System.out.println("---");
        Stopwatch stw = new Stopwatch();
        SelectionSort.sort(scores);
        double times = stw.elapsedTime();
        System.out.println("SelectionSort n = " + n + ", time in second \t\t" + times);
        // printValue(array, 10);

        int count = 0;
        while(count < 10) {
            System.out.println(scores[count].getValue());
            count ++;
        }
    }
    private static void testShellSort(int n){
        int[] array = createArray(n);
        System.out.println("---");
        Stopwatch stw = new Stopwatch();
        ShellShort.sort(array);
        double times = stw.elapsedTime();
        System.out.println("ShellShort n = " + n + ", time in second \t\t" + times);
        printValue(array, 10);
    }
    public static void main(String[] args) {
        // testInsertionSort(10000);
        testSelectionSort(10000);
        // testShellSort(10000);
    }
}
