/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Percolation {
    private int ROW = 0;
    private int COL = 0;
    private int[][] openMap = null;
    private int[][] id = null;
    private int openSiteCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if ( n <= 0 ) {
            throw new IllegalArgumentException();
        }
        this.ROW = n;
        this.COL = n;
        // this.openMap = new int[n][n];
        // Arrays.fill(openMap, 0);
        this.id = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j=0; j < n; j++) {
                this.id[i][j] = -1;
            }
        }
    }

    private int root(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        int index = (row-1) * COL + (col);
        // System.out.println("[root] index = " + index);
        while (this.id[row-1][col-1] != -1 && index != this.id[row-1][col-1])  {
            System.out.println("index " + index + ", row/col " + row + ", " + col + ", id " + this.id[row-1][col-1]);
            // row--;
            // col--;
            // index = (row-1) * COL + (col);
            int new_row = (int)(this.id[row-1][col-1] / COL);
            int new_col = this.id[row-1][col-1] % COL;
            row = new_row + 1;
            col = new_col;
            index = (row-1) * COL + (col);
            System.out.println("--NEW index " + index + ", row/col " + row + ", " + col + ", id " + this.id[row-1][col-1]);
        }
        if (this.id[row-1][col-1] > -1 )
            System.out.println("return root is " + this.id[row-1][col-1]);
        return this.id[row-1][col-1];
    }

    private void union(int row1, int col1, int row2, int col2) {
        if (root(row1, col1) < root(row2, col2)) {
            id[row2-1][col2-1] = id[row1-1][col1-1];
        } else {
            id[row1-1][col1-1] = id[row2-1][col2-1];
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        try {
            if (!isOpen(row, col)){
                openSiteCount += 1;
                int set_id = (row - 1) * COL +  (col) ;
                if(row == 1) {
                    // this.id[row-1][col-1] = 1;
                    this.id[row - 1][col - 1] = set_id;
                } else {
                    this.id[row - 1][col - 1] = set_id;
                }

                System.out.println("Open site " + row + ", " + col + " to id " + set_id + ", out = " +  this.id[row - 1][col - 1] );
                if(isOpen(row - 1, col)) {
                    System.out.println("Merge (" + row + "," + col + ") with (" + (row-1) + "," + col);
                    union(row, col, row - 1, col);
                }
                // if(isOpen(row, col -1)) {
                //     System.out.println("Merge (" + row + "," + col + ") with (" + (row) + "," + (col-1));
                //     union(row, col, row, col - 1);
                // }
                // if(isOpen(row + 1, col)) {
                //     System.out.println("Merge (" + row + "," + col + ") with (" + (row+1) + "," + col);
                //     union(row, col, row + 1, col);
                // }
                // if(isOpen(row, col + 1)) {
                //     System.out.println("Merge (" + row + "," + col + ") with (" + (row) + "," + (col+1));
                //     union(row, col, row, col + 1);
                // }
            }
        }
        catch (IllegalArgumentException e) {

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        return root(row, col) > -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        return root(row, col) == 1;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return this.openSiteCount;
    }

    // does the system percolate?
    public boolean percolates(){
        for (int i =0; i < COL; i++){
            if(root(ROW, i+1) <= COL){
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args){

    }

}
