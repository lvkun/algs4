import java.util.Arrays;


public class Board {
    /**
     * array of blocks
     */
    private short[] blocks;
    
    /**
     * board dimension.
     */
    private int size;
    
    /**
     * row of 0 element
     */
    private int zeroRow;
    /**
     * column of 0 element
     */
    private int zeroCol;
    
    private int man = -1;
    private int ham = -1;
    
    private String out = "";
    
    /**
     * construct a board from an N-by-N array of blocks;
     * (where blocks[i][j] = block in row i, column j)
     * @param blocks
     */
    public Board(int[][] blocks) {
        size = blocks.length;
        
        this.blocks = new short[size * size];
        for (int i = 0; i < blocks.length; i++) {
            int[] row = blocks[i];
            for (int j = 0; j < row.length; j++) {
                this.blocks[size * i + j] = (short) blocks[i][j];
                
                // find out 0 element position
                if (blocks[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }
    }
    
    private Board(short[] blocks, int size, int zr, int zc) {
        this.blocks = Arrays.copyOf(blocks, blocks.length);
        this.size = size;
        this.zeroRow = zr;
        this.zeroCol = zc;
    }
    
    /**
     * @return board dimension N.
     */
    public int dimension() {
        return size;
    }
    
    /**
     * @return number of blocks out of place.
     */
    public int hamming() {
        
        if (ham != -1) {
            return ham;
        }
        ham = 0;
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] == 0) {
                continue;
            }
            if (blocks[i] != (i + 1)) {
                ham++;
            }
        }
        return ham;
    }
    
    /**
     * @return sum of Manhattan distances between blocks and goal.
     */
    public int manhattan() {
        
        if (man != -1) {
            return man;
        }
        
        man = 0;
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] == 0) {
                continue;
            }
            int row = Math.abs((i) % size - (blocks[i] - 1) % size);
            int col = Math.abs((i) / size - (blocks[i] - 1) / size);

            man += row + col;
        }
        return man;
    }
    
    /**
     * @return is this board the goal board?
     */
    public boolean isGoal() {
        return hamming() == 0;
    }
    
    /**
     * @return a board obtained by exchanging 
     * two adjacent blocks in the same row.
     */
    public Board twin() {
        boolean success = false;
        int[][] twinBlocks = new int[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                twinBlocks[i][j] = blocks[i * size + j];
            }
        }
        
        while (!success) {
            int row = StdRandom.uniform(size);
            int col = StdRandom.uniform(size - 1);
            
            if (size == 2) {
                col = 1;
            }
            
            if (col < 1 
                    || twinBlocks[row][col] == 0 
                    || twinBlocks[row][col - 1] == 0) {
                continue;
            }
            
            int temp = twinBlocks[row][col];
            twinBlocks[row][col] = twinBlocks[row][col - 1];
            twinBlocks[row][col - 1] = temp;
            
            success = true;
        }

        return new Board(twinBlocks);
    }
    
    /**
     * does this board equal y?
     */
    public boolean equals(Object y) {
        
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        
        Board that = (Board) y;
        
        if (that.size != this.size) {
            return false;
        }
        
        return Arrays.equals(blocks, that.blocks);
    }

    /**
     * @return all neighboring boards.
     */
    public Iterable<Board> neighbors() {
        Stack<Board> boards = new Stack<Board>();
        
        if (zeroRow != 0) {
            swap(this.blocks, zeroRow - 1, zeroCol, zeroRow, zeroCol);
            //print(initBlocks);
            boards.push(new Board(this.blocks, this.size, zeroRow - 1, zeroCol));
            // restore
            swap(this.blocks, zeroRow - 1, zeroCol, zeroRow, zeroCol);
        }

        if (zeroRow < size - 1) {
            swap(this.blocks, zeroRow + 1, zeroCol, zeroRow, zeroCol);
            //print(initBlocks);
            boards.push(new Board(this.blocks, this.size, zeroRow + 1, zeroCol));
            // restore
            swap(this.blocks, zeroRow + 1, zeroCol, zeroRow, zeroCol);
        }

        if (zeroCol != 0) {
            swap(this.blocks, zeroRow, zeroCol - 1, zeroRow, zeroCol);
            boards.push(new Board(this.blocks, this.size, zeroRow, zeroCol - 1));
            // restore
            swap(this.blocks, zeroRow, zeroCol - 1, zeroRow, zeroCol);
        }

        if (zeroCol < size - 1) {
            swap(this.blocks, zeroRow, zeroCol + 1, zeroRow, zeroCol);
            boards.push(new Board(this.blocks, this.size, zeroRow, zeroCol + 1));
            swap(this.blocks, zeroRow, zeroCol + 1, zeroRow, zeroCol);
        }

        return boards;
    }

    private void swap(short[] arr, int fromRow, int fromCol, 
             int toRow, int toCol) {
        short temp = arr[fromRow * size + fromCol];
        arr[fromRow * size + fromCol] = arr[toRow * size + toCol];
        arr[toRow * size + toCol] = temp;
    }
    
    /**
     * @return string representation of the board 
     * (in the output format specified below)
     */
    public String toString() {
        if (out.length() > 0) {
            return out;
        }
        
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", blocks[size * i + j]));
            }
            s.append("\n");
        }
        
        out = s.toString();
        return out;
    }
}
