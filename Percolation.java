import java.util.Arrays;

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
        this.ROW = n;
        this.COL = n;
        this.openMap = new int[n][n];
        Arrays.fill(openMap, 0);
        this.id = new int[n][n];
        for (int i = 0; i < n + 1; i++){
            for (int j=0; j < n+1; j++) {
                this.id[i][j] = -1;
            }
        }
    }

    private int root(int row, int col){
        return this.id[row-1][col-1];
    }

    private void union() {

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)){
            openSiteCount += 1;
            if(row == 1) {
                this.id[row-1][col-1] = 1;
            } else {
                this.id[row - 1][col - 1] = (row - 1) * ROW +  (col - 1);
            }
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
            if(root(ROW, i+1) == 1){
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args){

    }

}
