
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class BoardTest extends TestCase {
    public static Test suite() {
        TestSuite suite = new TestSuite(BoardTest.class);
        return suite;
    }
    
    public void testCreation() {
        int[][] blocks = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board board = new Board(blocks);
        
        assertEquals(board.dimension(), 3);
    }
    
    public void testHamming() {
        int[][] blocks3 = new int[][] {
            {8, 1, 3}, 
            {4, 0, 2}, 
            {7, 6, 5}
        };
        Board board3 = new Board(blocks3);
        
        assertEquals(board3.hamming(), 5);
        
        int[][] blocks4 = new int[][] {
            {1, 3, 2, 4}, 
            {5, 6, 7, 8}, 
            {9, 10, 11, 12}, 
            {13, 14, 15, 0}
        };
        
        Board board4 = new Board(blocks4);
        assertEquals(board4.hamming(), 2);
    }
    
    public void testManhattan() {
        int[][] blocks3 = new int[][] {
            {8, 1, 3}, 
            {4, 0, 2}, 
            {7, 6, 5}
        };
        Board board3 = new Board(blocks3);
        assertEquals(board3.manhattan(), 10);
        
        int[][] blocks4 = new int[][] {
            {1, 3, 2, 4}, 
            {5, 6, 7, 8}, 
            {9, 10, 11, 12}, 
            {13, 14, 15, 0}
        };
        Board board4 = new Board(blocks4);
        assertEquals(board4.manhattan(), 2);
        
        int[][] blockPuzzle04 = new int [][] {
            {0,  1,  3},
            {4,  2,  5},
            {7,  8,  6}
        };
        Board boardPuzzle04 = new Board(blockPuzzle04);
        assertEquals(boardPuzzle04.manhattan(), 4);
    }
    
    public void testIsGoal() {
        int[][] blocksIs = new int[][] {
            {1, 2, 3}, 
            {4, 5, 6}, 
            {7, 8, 0}
        };
        Board boardIs = new Board(blocksIs);
        assertTrue(boardIs.isGoal());
        int[][] blocksNot = new int[][] {
            {1, 3, 2}, 
            {4, 5, 6}, 
            {7, 8, 0}
        };
        Board boardNot = new Board(blocksNot);
        assertFalse(boardNot.isGoal());
    }
    
    public void testTwin() {
       // just print out
        int[][] blocks = new int[][] {
            {1, 3, 2}, 
            {4, 5, 6}, 
            {7, 8, 0}
        };
        Board board = new Board(blocks);
        Board twinBoard = board.twin();
        
        assertFalse(board.equals(twinBoard));
//        StdOut.println(board);
//        StdOut.println(twinBoard);
    }
    
    public void testNeighbors() {
//        int[][] blocks = new int[][] {
//            {1, 0}, 
//            {2, 3}
//        };
//        Board board = new Board(blocks);
//        
//        for (Board b: board.neighbors()) {
//            StdOut.print(b);
//        }
//        
//        int[][] blocks3 = new int[][] {
//            {1, 3, 2}, 
//            {4, 5, 6}, 
//            {7, 8, 0}
//        };
//        Board board3 = new Board(blocks3);
//        for (Board b: board3.neighbors()) {
//            StdOut.print(b);
//        }
    }
    
    public void testDuplicateSearchNode() {
        int[][] blocks = new int[][] {
            {1, 3, 2}, 
            {4, 5, 6}, 
            {7, 8, 0}
        };
        Board board01 = new Board(blocks);   
        Board board02 = new Board(blocks);  
        assertTrue(board01.equals(board02));
        
        int[][] blocks03 = new int[][] {
            {1, 2, 3}, 
            {4, 5, 6}, 
            {7, 8, 0}
        };
        Board board03 = new Board(blocks03);
        assertFalse(board03.equals(board01));
    }
    
    private Solver solve(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        return solver;
    }
    
    public void testSolver31() {
        Solver solver = solve("test\\8puzzle\\puzzle31.txt");
        assertEquals(solver.moves(), 31);
    }
    
    public void testSolver17() {
        Solver solver = solve("test\\8puzzle\\puzzle17.txt");
        assertEquals(solver.moves(), 17);
    }
    
    public void testSolverUnsolvable() {
        Solver solver = solve("test\\8puzzle\\puzzle3x3-unsolvable.txt");
        assertFalse(solver.isSolvable());
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
