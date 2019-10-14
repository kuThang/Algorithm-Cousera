/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Percolation {
    private int ROW = 0;
    private int COL = 0;
    private int[][] id = null;
    private int openSiteCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if ( n <= 0 ) {
            throw new IllegalArgumentException();
        }
        this.ROW = n;
        this.COL = n;
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
        while (id[row-1][col-1] != calculateId(row-1, col-1)) {
            Integer [] coord = calculateCoor(id[row-1][col-1]);

            row = coord[0] + 1;
            col = coord[1] + 1;
        }
        return id[row-1][col-1];
    }

    private void union(int row1, int col1, int row2, int col2) {
        if(id[row2-1][col2-1] < 0){
            return;
        }
        if (root(row1, col1) < root(row2, col2)){
            System.out.println("root(row1, col1) = " + root(row1, col1) + " < root(row2, col2) = " + root(row2, col2));
            Integer [] coord = calculateCoor(id[row2-1][col2-1]);
            id[coord[0]][coord[1]] = root(row1, col1);
            System.out.println("---- root(row1, col1) = " + root(row1, col1) + ", root(row2, col2) = " + root(row2, col2));
        } else {
            System.out.println("root(row1, col1) = " + root(row1, col1) + " >= root(row2, col2) = " + root(row2, col2));
            Integer [] coord = calculateCoor(id[row1-1][col1-1]);
            id[coord[0]][coord[1]] = root(row2, col2);
            // id[row1-1][col1-1] = id[row2 -1][col2 -1];
            System.out.println("---- root(row1, col1) = " + root(row1, col1) + ", root(row2, col2) = " + root(row2, col2));
        }

        for (int i = 1; i <= ROW; i++){
            for (int j = 1; j <= COL; j++) {
                if(this.id[i-1][j-1] > -1 ){
                    System.out.println("** at ["+ i + "," + j+ "] value is " + this.id[i-1][j-1]);
                }
            }
        }
    }

    private int calculateId(int row, int col){
        int id = row * COL + col;
        // System.out.println("-- id, col, row " + id + ", " + row + ", " + col);
        return row * COL + col;
    }

    private Integer[] calculateCoor(int id) {
        Integer[] coord = new Integer[2];
        coord[0] = (int)(id / COL);
        coord[1] = id % COL;
        // System.out.println("- id, col, row " + id + ", " + coord[0] + ", " + coord[1]);
        return coord;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        // calculateCoor(2);
        // calculateCoor(50);
        // calculateCoor(49);
        //
        // calculateId(0, 2);
        // calculateId(5, 0);
        // calculateId(2, 9);
        if(!isOpen(row, col)){
            // id[row-1][col -1] = row - 1;
            id[row-1][col-1] = calculateId(row -1, col -1);
        }
        if(row > 1) {
            union(row, col, row - 1, col);
        }
        if(col > 1) {
            union(row, col, row, col - 1);
        }
        if(row < ROW) {
            union(row, col, row + 1, col);
        }
        if(col < COL) {
            union(row, col, row, col + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        return id[row-1][col-1] > -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        return id[row-1][col-1] > -1 && root(row, col) < COL ;
        // return id[row-1][col-1] < COL && id[row-1][col-1] > -1;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return this.openSiteCount;
    }

    // does the system percolate?
    public boolean percolates(){
        for(int j = 1; j <= COL; j++) {
            if(id[ROW-1][j-1] > -1 && root(ROW, j) < COL) {
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args){

    }

}
