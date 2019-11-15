/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class test {
    private static Score[] createData(int n) {
        int[] array = StdRandom.permutation(n);
        Score[] scores = new Score[n];
        for (int i : array) {
            scores[i] = new Score(array[i]);
        }
        return scores;
    }

    private static void printValue(Score[] a, int k) {
        int count = 0;
        while(count < k) {
            System.out.println(a[count].getScore());
            count ++;
        }
    }

    private static void runSort(String type, int n) {
        Score[] scores = createData(n);
        // printValue(scores, 10);

        Stopwatch stw = new Stopwatch();
        switch (type){
            case "SelectionSort":
                SelectionSort.sort(scores);
            case "ShellSort":
                ShellSort.sort(scores);
            case "InsertionSort":
                InsertionSort.sort(scores);
            case "MergeSort":
                MergeSort.sort(scores);
        }

        double times = stw.elapsedTime();
        System.out.println(type + " n = " + n + ", time in second \t\t" + times);
        // printValue(scores, 10);
    }

    public static void main(String[] args) {
        // runSort("InsertionSort", 100000);
        // runSort("SelectionSort", 100000);
        runSort("ShellSort", 100000);
        runSort("MergeSort", 100000);

        // Integer[] arr = {3, 4, 1, 5, 10, 7, 2};
        // // InsertionSort selectionSort = new InsertionSort();
        // ShellSort.sort(arr);
        // System.out.println("After sorting  int : "+ Arrays.toString(arr));
    }
}
