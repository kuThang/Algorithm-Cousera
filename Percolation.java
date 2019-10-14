/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {
    private int RANGE = -1;
    private Integer[] id;
    private Integer[] sz;
    private int countSites = 0;

    public Percolation(int n){
        if ( n <= 0 ) {
            throw new IllegalArgumentException();
        }
        this.id = new Integer[n*n + 2];
        this.sz = new Integer[n*n + 2];
        this.RANGE = n;
        for(int i = 0; i < n*n + 2; i++){
            if(i == 0 || i == n*n + 1) {
                this.id[i] = i;
                this.sz[i] = 0;
            } else {
                this.id[i] = -1;
                this.sz[i] = -1;
            }
        }
    }

    private boolean validCoordinate(int row, int col) {
        return row >= 1 && row <= this.RANGE && col >= 1 && col <= this.RANGE;
    }

    private int toArrayIndex(int row, int col) {
        return (row - 1) * this.RANGE + col;
    }

    private int root(int i){
        if(id[i] == -1) {
            throw new IllegalArgumentException();
        }
        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    private boolean connected(int x, int y){
        return root(x) == root(y);
    }

    private void union(int x, int y) {
        if (sz[x] < sz[y]) {
            id[root(x)] = id[root(y)];
        } else {
            id[root(y)] = id[root(x)];
        }
        int sum = sz[x] + sz[y];
        sz[x] = sum;
        sz[y] = sum;
    }

    private void union(int row1, int col1, int row2, int col2){
        if(!validCoordinate(row2, col2) || !isOpen(row2, col2)) {
            return;
        }
        union(toArrayIndex(row1, col1), toArrayIndex(row2, col2));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validCoordinate(row, col)){
            throw new IllegalArgumentException();
        }
        id[toArrayIndex(row, col)] = toArrayIndex(row, col);
        sz[toArrayIndex(row, col)] = 1;
        this.countSites += 1;
        if(row == 1) {
            union(0, toArrayIndex(row, col));
        }
        if(row == this.RANGE) {
            union(toArrayIndex(row, col), this.RANGE * this.RANGE + 1);
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
        return this.id[toArrayIndex(row, col)] > -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validCoordinate(row, col)){
            throw new IllegalArgumentException();
        }
        return isOpen(row, col) && connected(0, toArrayIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (this.RANGE > 0 && connected(0, this.RANGE * this.RANGE + 1));
    }

    public static void main(String[] args) {

    }
}
