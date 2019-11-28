/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final boolean solvable;
    private SearchNode finalNode;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode prev;
        private final int moves;
        private final int priority;

        SearchNode(Board b, SearchNode p, int m) {
            this.board = b;
            this.prev = p;
            this.moves = m;
            this.priority = m + b.manhattan();
        }
        Board getBoard() {
            return this.board;
        }
        SearchNode getPrev() {
            return this.prev;
        }
        int getMoves() {
            return this.moves;
        }
        int priority() {
            return this.priority;
        }

        public int compareTo(SearchNode that) {
            return priority() - that.priority();
        }
    }
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        MinPQ<SearchNode> minPQ = new MinPQ<>();
        minPQ.insert(new SearchNode(initial, null, 0));

        while (true) {
            SearchNode del = minPQ.delMin();
            Board delBoard = del.getBoard();


            if (delBoard.isGoal()) {
                finalNode = del;
                solvable = true;
                break;
            }

            if (delBoard.manhattan() == 2 && delBoard.twin().isGoal()) {
                finalNode = null;
                solvable = false;
                break;
            }

            for (Board b : delBoard.neighbors()) {
                if (del.getPrev() == null || !b.equals(del.getPrev().getBoard())) {
                    minPQ.insert(new SearchNode(b, del, del.getMoves() + 1));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return this.solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (solvable) return finalNode.getMoves();
        else return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> tracks = new Stack<>();
        SearchNode checkNode = finalNode;

        while (checkNode != null) {
            tracks.push(checkNode.getBoard());
            checkNode = checkNode.getPrev();
        }
        return tracks;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
