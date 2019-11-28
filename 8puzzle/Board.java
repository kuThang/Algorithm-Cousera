/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.LinkedList;
import java.util.List;

public class Board {
    private final int n;
    private final int[][] board;
    private int emptyRow;
    private int emptyCol;
    private final int hammingDistance;
    private final int manhattanDistance;

    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        n = tiles.length;
        this.board = new int[n][n];
        int hamming = 0;
        int manhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.board[i][j] = tiles[i][j];

                if (this.board[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                } else {
                    if (this.board[i][j] != i * n + j + 1) {
                        hamming++;
                        int[] realPosition = calculatePosition(board[i][j] - 1);
                        manhattan += Math.abs(i - realPosition[0]);
                        manhattan += Math.abs(j - realPosition[1]);
                    }
                }
            }
        }
        hammingDistance = hamming;
        manhattanDistance = manhattan;
    }

    private void swap(int[][]tiles, int x, int y, int dx, int dy) {
        int temp = tiles[x][y];
        tiles[x][y] = tiles[x + dx][y + dy];
        tiles[x + dx][y + dy] = temp;
    }

    private int[][] cloneBoard() {
        int[][] newBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = this.board[i][j];
            }
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
        position[0] = val / n;
        position[1] = val - position[0] * n;
        return position;
    }

    public String toString() {
        StringBuilder boardStr = new StringBuilder();
        boardStr.append(Integer.toString(this.n) + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boardStr.append(String.format("%3d ", board[i][j]));
                // boardStr = String.format("%s%3d ", boardStr,  board[i][j]);
            }
            boardStr.append("\n");
        }
        return new String(boardStr);

        // String boardStr = String.valueOf(dimension()) + "\n";
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         boardStr += String.format("%3d ",  board[i][j]);
        //     }
        //     boardStr += "\n";
        // }
        // return boardStr;
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        return this.hammingDistance;
    }
    public int manhattan() {
        return this.manhattanDistance;
    }
    public boolean isGoal() {
        return hamming() == 0;
        // if(this.emptyRow == n - 1 && this.emptyCol == n-1) return true;
        // else return false;
    }
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y == this) return true;
        if (this.getClass() != y.getClass()) return false;

        Board that = (Board) y;

        if (that.n != this.n) return false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (that.board[i][j] != this.board[i][j]) return false;
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();

        if (emptyCol - 1 >= 0) {
            neighbors.add(createNeighbor(0, -1));
        }
        if (emptyCol + 1 <= n - 1) {
            neighbors.add(createNeighbor(0, 1));
        }
        if (emptyRow - 1 >= 0) {
            neighbors.add(createNeighbor(-1, 0));
        }
        if (emptyRow + 1 <= n - 1) {
            neighbors.add(createNeighbor(1, 0));
        }
        return neighbors;
    }
    public Board twin() {
        int x, y;
        if (emptyRow == 0) {
            x = emptyRow + 1;
        } else {
            x = emptyRow - 1;
        }
        if (emptyCol == 0) {
            y = emptyCol + 1;
        } else {
            y = emptyCol - 1;
        }
        int[][] clone = cloneBoard();

        if (y == 0) {
            swap(clone, x, y, 0, 1);
            // StdOut.println("[x1, y1] = [" + x + ", " + y + "] , [x2, y2] = " + ( x+1) + ", " + y);
        } else {
            swap(clone, x, y, 0, -1);
            // StdOut.println("[x1, y1] = [" + x + ", " + y + "] , [x2, y2] = " + ( x-1) + ", " + y);
        }
        return new Board(clone);

        // int[][] cloned = cloneBoard();
        // if (emptyRow != 0) {
        //     StdOut.println("[x1, y1] = [0, 0], [x2, y2] = 0, 1");
        //     swap(cloned, 0, 0, 0, 1);
        // } else {
        //     StdOut.println("[x1, y1] = [1, 0], [x2, y2] = 1, 1");
        //     swap(cloned, 1, 0, 1, 1);
        // }
        // return new Board(cloned);
    }
    public static void main(String[] args) {
        // int n = 3;
        // int[][] tiles = new int[n][n];
        // tiles[0][0] =8;
        // tiles[0][1] =1;
        // tiles[0][2] =3;
        // tiles[1][0] =4;
        // tiles[1][1] =0;
        // tiles[1][2] =2;
        // tiles[2][0] =7;
        // tiles[2][1] =6;
        // tiles[2][2] =5;
        // Board board = new Board(tiles);
        // StdOut.println(board);
        // System.out.println("Hamming distance : " + board.hamming());
        // System.out.println("Manhattan distance : " + board.manhattan());

        // for(Board b : board.neighbors()) {
        //     StdOut.println("Neighbor");
        //     StdOut.println(b);
        //     System.out.println("Hamming distance : " + b.hamming());
        //     System.out.println("Manhattan distance : " + b.manhattan());
        // }
    }
}
