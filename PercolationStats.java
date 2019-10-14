/* *****************************************************************************
 *  Name: Nguyen Quoc Thang
 *  Date: 2019/10/14
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] fractions;
    private double meanVal = -1.0;
    private double stddevVal = -1.0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            Stopwatch stw = new Stopwatch();
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if(!percolation.isOpen(row, col)){
                    percolation.open(row, col);
                }

            }
            this.fractions[i] = (double)percolation.numberOfOpenSites() / (n * n);

            double times = stw.elapsedTime();

            System.out.println("Time in second \t\t" + times);
            // stats.setVal(i, (double)percolation.numberOfOpenSites() / (n * n));
        }
    }

    private void setVal(int idx, double val) {
        this.fractions[idx] = val;
    }

    // sample mean of percolation threshold
    public double mean(){
        if(this.meanVal < 0) {
            this.meanVal = StdStats.mean(this.fractions);
        }
        return this.meanVal;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        if(this.stddevVal < 0){
            this.stddevVal = StdStats.stddev(this.fractions);
        }
        return this.stddevVal;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(this.fractions.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(this.fractions.length);
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        // System.out.println("Call mean " + stats.callMean);
        System.out.println("mean\t\t\t\t\t" + stats.mean());
        System.out.println("stddev\t\t\t\t\t" + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
        // System.out.println("Call mean " + stats.callMean);
    }

}