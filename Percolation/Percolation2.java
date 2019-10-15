/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Percolation2 {
    private int ROW = 0;
    private int COL = 0;
    private int[][] id = null;
    private int openSiteCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation2(int n){
        if ( n <= 0 ) {
            throw new IllegalArgumentException();
        }
        this.ROW = n;
        this.COL = n;
        this.id = new int[n+2][n];
        this.openSiteCount = 0;

        for (int i = 0; i < n; i++){
            for (int j=0; j < n; j++) {
                this.id[i][j] = -1;
            }
        }
    }

    private int getId(int row, int col) {
        return id[row-1][col-1];
    }

    private void setId(int row, int col, int id) {
        this.id[row-1][col-1] = id;
    }

    private int root(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        while (getId(row, col) != calculateId(row, col)) {
            // (row, col) cell pointed to other cell
            // getId(row, col) ~ id[i]
            // calculateId(row, col) ~ i
            // int nextId = getId(row, col);


            Integer [] coord = calculateCoor(getId(row, col));
            // setId(row, col, calculateId(coord[0], coord[1]));
            // System.out.println("Set id at ["+ row + "," +  col + "] value is " + calculateId(coord[0], coord[1]));
            // i = id[i]
            // Integer [] coord = calculateCoor(getId(row, col));
            row = coord[0];
            col = coord[1];
        }
        return getId(row, col);
    }

    private void union(int row1, int col1, int row2, int col2) {
        if(getId(row2, col2) < 0){
            return;
        }
        if (root(row1, col1) < root(row2, col2)){
            Integer [] coord = calculateCoor(root(row2, col2));
            setId(coord[0], coord[1], root(row1, col1));
        } else {
            Integer [] coord = calculateCoor(root(row1, col1));
            setId(coord[0], coord[1], root(row2, col2));
        }

        // for (int i = 1; i <= ROW; i++){
        //     for (int j = 1; j <= COL; j++) {
        //         if(this.id[i-1][j-1] > -1 ){
        //             System.out.println("** at ["+ i + "," + j+ "] value is " + this.id[i-1][j-1]);
        //         }
        //     }
        // }
    }

    private int calculateId(int row, int col){
        int id = (row - 1) * COL + col - 1;
        return id;
    }

    private Integer[] calculateCoor(int id) {
        Integer[] coord = new Integer[2];
        coord[0] = (id / COL) + 1;
        coord[1] = id % COL + 1;
        return coord;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        if(!isOpen(row, col)){
            setId(row, col, calculateId(row, col));

            openSiteCount += 1;
            // id[row-1][col-1] = calculateId(row, col);
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
        return getId(row, col) > -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (row < 1 || row > ROW || col < 1 || col > COL){
            throw new IllegalArgumentException();
        }
        return getId(row, col) > -1 && root(row, col) < COL ;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return this.openSiteCount;
    }

    // does the system percolate?
    public boolean percolates(){
        for(int j = 1; j <= COL; j++) {
            if(getId(ROW, j) > -1 && root(ROW, j) < COL) {
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args){

    }

}
