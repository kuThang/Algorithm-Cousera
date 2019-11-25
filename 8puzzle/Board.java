/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int n;
    private final int[][] board;
    private int emptyRow;
    private int emptyCol;

    private void swap(int[][]tiles, int x, int y, int dx, int dy) {
        int temp = tiles[x][y];
        tiles[x][y] = tiles[x + dx][y + dy];
        tiles[x + dx][y + dx] = temp;
    }

    private int[][] cloneBoard() {
        int[][] newBoard = new int[n][];
        for(int row = 0; row < n; row ++) {
            newBoard[row] = this.board[row].clone();
        }
        return newBoard;
    }

    private Board createNeighbor(int dx, int dy) {
        int[][] clone = cloneBoard();
        swap(clone, emptyRow, emptyCol, dx, dy);
        return new Board(clone);
    }

    private int[] calculatePosition(int val) {
        int[] position = new int[2];
        position[0] = (int)(val / n);
        position[1] = val - position[0] * n;
        return position;
    }
    public Board(int[][] tiles) {
        if(tiles == null) {
            throw new NullPointerException();
        }
        n = tiles.length;
        this.board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                this.board[i][j] = tiles[i][j];
                if(tiles[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }

    public String toString() {
        String returnString = String.valueOf(dimension()) + "\n";
        for (int i = 0; i < n; i++){
            returnString += board[i].toString() + "\n";
        }
        return returnString;
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int hammingDistance = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] != 0 && board[i][j] != i * n + j + 1) hammingDistance ++;
            }
        }
        return hammingDistance;
    }
    public int manhattan() {
        int manhattanDistance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] != 0 && board[i][j] != i * n + j + 1) {
                    int[] realPosition = calculatePosition(board[i][j] - 1);
                    manhattanDistance += Math.abs(i - realPosition[0]);
                    manhattanDistance += Math.abs(j - realPosition[1]);
                }
            }
        }
        return manhattanDistance;
    }
    public boolean isGoal() {
        return hamming() == 0;
    }
    public boolean equals(Object y) {
        return board.equals(y);
    }
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();

        if(emptyCol - 1 >= 0) {
            neighbors.add(createNeighbor(0, -1));
        }
        if(emptyCol + 1 <= n - 1) {
            neighbors.add(createNeighbor(0, 1));
        }
        if(emptyRow - 1 >= 0) {
            neighbors.add(createNeighbor(-1, 0));
        }
        if(emptyRow + 1 <= n - 1) {
            neighbors.add(createNeighbor(1, 0));
        }
        return neighbors;
    }
    public Board twin() {
        int[][] clone = cloneBoard();
        swap(clone, 0, 0, 0, 1);
        return new Board(clone);
    }
    public static void main(String[] args) {
        int n = 3;
        int[][] tiles = new int[n][n];
        tiles[0][0] =8;
        tiles[0][1] =1;
        tiles[0][2] =3;
        tiles[1][0] =4;
        tiles[1][1] =0;
        tiles[1][2] =2;
        tiles[2][0] =7;
        tiles[2][1] =6;
        tiles[2][2] =5;
        Board board = new Board(tiles);
        System.out.println("Hamming distance : " + board.hamming());
        System.out.println("Manhattan distance : " + board.manhattan());
    }
}
