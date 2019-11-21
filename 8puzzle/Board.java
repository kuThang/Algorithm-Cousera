/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Board {
    private int _dimension;
    private int[][] board;
    public Board(int[][] tiles) {
        _dimension = tiles.length;
        board = tiles;
    }
    public String toString() {
        String returnString = String.valueOf(dimension()) + "\n";
        for (int i = 0; i < _dimension; i++){
            returnString += board[i].toString() + "\n";
        }
        return returnString;
    }
    public int dimension() {
        return _dimension;
    }
    public int hamming() {
        int hammingDistance = 0;
        for (int i = 0; i < _dimension; i++){
            for(int j = 0; j < _dimension; j++) {
                if(board[i][j] != i * _dimension + j) hammingDistance ++;
            }
        }
        return hammingDistance;
    }
    public int manhattan(){
        int manhattanDistance = 0;
        return 0;
    }
    public boolean isGoal() {
        return false;
    }
    public boolean equals(Object y) {
        return false;
    }
    public Iterable<Board> neighbors() {

    }
    public Board twin() {

    }
    public static void main(String[] args) {

    }
}
