/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {
    private class WeightedQuickUnion {
        int[] id;

        WeightedQuickUnion(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }

        int root(int x) {
            while(id[x] != x){
                id[x] = id[id[x]];
                x = id[x];
            }
            return x;
        }

        boolean connected(int p, int q){
            return root(p) == root(q);
        }

        void union(int p, int q){
            id[root(q)] = root(p);
        }
    }

    private int RANGE;
    private WeightedQuickUnion wqu;
    private WeightedQuickUnion wquForFull;
    private boolean [][] flag;
    private int countSites = 0;

    public Percolation(int n){
        if ( n <= 0 ) {
            throw new IllegalArgumentException();
        }
        this.wqu = new WeightedQuickUnion(n * n + 2);
        this.wquForFull = new WeightedQuickUnion(n * n + 1);
        this.flag = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.flag[i][j] = false;
            }
        }
        this.RANGE = n;
    }

    private boolean validCoordinate(int row, int col) {
        return row >= 1 && row <= this.RANGE && col >= 1 && col <= this.RANGE;
    }

    private int toArrayIndex(int row, int col) {
        return (row - 1) * this.RANGE + col;
    }

    private void union(int row1, int col1, int row2, int col2){
        if(!validCoordinate(row2, col2) || !isOpen(row2, col2)) {
            return;
        }
        int x = this.toArrayIndex(row1, col1);
        int y = this.toArrayIndex(row2, col2);
        this.wqu.union(x, y);
        this.wquForFull.union(x, y);
        // union(toArrayIndex(row1, col1), toArrayIndex(row2, col2));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validCoordinate(row, col)){
            throw new IllegalArgumentException();
        }
        if(isOpen(row, col)) {
            return;
        }
        this.flag[row -1][col -1] = true;
        int index = toArrayIndex(row, col);
        this.countSites += 1;
        if(row == 1) {
            this.wqu.union(index, 0);
            this.wquForFull.union(index, 0);
        }
        if(row == this.RANGE) {
            this.wqu.union(index, this.RANGE * this.RANGE + 1);
        }
        union(row, col, row - 1, col);
        union(row, col, row, col - 1);
        union(row, col, row + 1, col);
        union(row, col, row, col + 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validCoordinate(row, col)){
            throw new IllegalArgumentException();
        }
        // return this.id[toArrayIndex(row, col)] > -1;
        return this.flag[row-1][col-1];
    }


    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validCoordinate(row, col)){
            throw new IllegalArgumentException();
        }
        return isOpen(row, col) && this.wquForFull.connected(0, toArrayIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (this.RANGE > 0 && this.wqu.connected(0, this.RANGE * this.RANGE + 1));
    }

    public static void main(String[] args) {

    }
}
