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
        printValue(scores, 10);

        Stopwatch stw = new Stopwatch();
        switch (type){
            case "SelectionSort":
                SelectionSort.sort(scores);
                break;
            case "ShellSort":
                ShellSort.sort(scores);
                break;
            case "InsertionSort":
                InsertionSort.sort(scores);
                break;
            case "MergeSort":
                MergeSort.sort(scores);
                break;
            case "MergeSortBottomUp":
                MergeSortBottomUp.sort(scores);
                break;
            case "QuickSort":
                QuickSort.sort(scores);
                break;
            case "HeapSort":
                HeapSort.sort(scores);
                break;
        }

        double times = stw.elapsedTime();
        System.out.println(type + " n = " + n + ", time in second \t\t" + times);
        printValue(scores, 10);
    }

    public static void main(String[] args) {
        // Sort Generic
        runSort("InsertionSort", 10000); // 10,000 = 0.454s
        runSort("SelectionSort", 10000); // 10,000 = 0.342s
        runSort("ShellSort", 1000000); // 1,000,000 = 4.023s
        runSort("MergeSort",         1000000); // 1,000,000 = 1.789s
        runSort("MergeSortBottomUp", 1000000); // 1,000,000 = 1.198s
        runSort("QuickSort", 1000000); // 1,000,000 = 0.62s
        runSort("HeapSort", 1000000); // 1,000,000 = 0.62s

        // // Sort with Comparator
        // int[] array = StdRandom.permutation(100000);
        // Student[] students = new Student[100000];
        // for (int i : array) {
        //     students[i] = new Student(array[i], "123");
        // }
        // MergeSortWithComparator.sort(students, Student.BY_SCORE);
        // int count = 0;
        // while(count < 10) {
        //     System.out.println(students[count].getScore());
        //     count ++;
        // }
    }
}
