/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;

public class Solver {
    private MinPQ<Node> searchQueue;
    private int moveCount;
    private boolean solvable = false;
    private Node finalNode;

    private class Node implements Comparable<Node> {
        Board board;
        Node previous;
        int moves;

        Node(Board b, Node p, int m) {
            this.board = b;
            this.previous = p;
            this.moves = m;
        }
        Board getBoard() {
            return this.board;
        }
        Node getPrevious() {
            return this.previous;
        }
        int getMoves() {
            return this.moves;
        }
        int priority(){
            return this.moves + board.hamming();
        }

        public int compareTo(Node that) {
            return priority() - that.priority();
        }
    }
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        Node node = new Node(initial, null, 0);
        searchQueue = new MinPQ<>();
        searchQueue.insert(node);
        while(searchQueue.size() > 0) {
            // System.out.println("--");
            // System.out.println("queue size = " + searchQueue.size());
            Node del = searchQueue.delMin();
            Board delBoard = del.getBoard();

            // StdOut.println(delBoard);
            // StdOut.println("board move " + del.getMoves());

            if(delBoard.isGoal()) {
                // StdOut.println("Goal");
                if(delBoard.hamming() != 0) {
                    // StdOut.println("hamming is not zero");
                    finalNode = null;
                    solvable = false;
                } else {
                    // StdOut.println("hamming ZERO");
                    finalNode = del;
                    solvable = true;
                }
                break;
            }

            // int delMoves = del.getMoves();
            for(Board b : delBoard.neighbors()) {
                // StdOut.println("Neighbor");
                // StdOut.println(b);
                if (del.getPrevious() == null ||  (del.getPrevious() != null && !b.equals(del.getPrevious().getBoard()))) {
                    searchQueue.insert(new Node(b,  del, del.getMoves() + 1));
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
        if(isSolvable()) return this.finalNode.getMoves();
        else return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if(!isSolvable()) return null;

        List<Board> tracks = new LinkedList<>();

        while (finalNode != null) {
            ((LinkedList<Board>) tracks).addFirst(finalNode.getBoard());
            finalNode = finalNode.getPrevious();
        }
        return tracks;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();

        int[][] tiles = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

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
