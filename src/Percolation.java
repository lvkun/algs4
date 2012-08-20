
/**
 * @author brlv
 *
 */
public class Percolation {
    /**
     * Number of virtual sites.
     */
    private static int vsNum = 2;
    /**
     * size of grid.
     */
    private int size;

    /**
     * site open or blocked.
     */
    private boolean[] sites;

    /**
     * quick union interface.
     */
    private WeightedQuickUnionUF quickUnion;
    /**
     * quick union interface.
     */
    private WeightedQuickUnionUF quickUnionNoBottom;
    /**
     * virtual top site index.
     */
    private int vTop;

    /**
     * virtual bottom site index.
     */
    private int vBottom;

    /**
     * create N-by-N grid, with all sites blocked.
     *
     * @param n grid size
     */
    public Percolation(final int n) {
        if (n < 1) {
            throw new java.lang.IllegalArgumentException();
        }

        size = n;
        sites = new boolean[n * n + vsNum];
        // add virtual number to sites array
        quickUnion = new WeightedQuickUnionUF(n * n + vsNum);
        quickUnionNoBottom = new WeightedQuickUnionUF(n * n + 1);

        vTop = n * n;
        vBottom = n * n + 1;
        sites[vTop] = true;
        sites[vBottom] = true;
    }

    /**
     * return the index in Quick Union.
     * @param i row
     * @param j column
     * @return the index in Quick Union
     */
    private int getSiteIndex(final int i, final int j) {
        return (i - 1) * size + j - 1;
    }

    /**
     * is row & column a valid site index.
     *
     * @param i row
     * @param j column
     * @return is valid or not
     */
    private boolean isValidIndex(final int i, final int j) {
        if (i < 1 || j < 1 || i > size || j > size) {
            return false;
        }

        return true;
    }


    /**
     * union two site if they are open.
     *
     * @param p site index
     * @param q site index
     */
    private void union(final int p, final int q) {
        if (sites[p] && sites[q]) {
            quickUnion.union(p, q);
            if (p <= size * size && q <= size * size) {
                quickUnionNoBottom.union(p, q);
            }
        }
    }

    /**
     * open site (row i, column j) if it is not already.
     *
     * @param i row
     * @param j column
     */
    public void open(final int i, final int j) {
        if (!isValidIndex(i, j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        int index = getSiteIndex(i, j);
        // set the site to open
        sites[index] = true;

        // connect to sites around it
        if (i == 1) {
            union(index, vTop);
        } else {
            union(index, getSiteIndex(i - 1, j));
        }
        if (i == size) {
            union(index, vBottom);
        } else {
            union(index, getSiteIndex(i + 1, j));
        }
        if (j > 1) {
            union(index, getSiteIndex(i, j - 1));
        }
        if (j < size) {
            union(index, getSiteIndex(i, j + 1));
        }

    }

    /**
     *  is site (row i, column j) open?
     *
     * @param i row
     * @param j column
     * @return is open or not
     */
    public boolean isOpen(final int i, final int j) {
        if (!isValidIndex(i, j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return sites[getSiteIndex(i, j)];
    }
    /**
     * is site (row i, column j) full?
     *
     * @param i row
     * @param j column
     * @return is full or not
     */
    public boolean isFull(final int i, final int j) {
        if (!isValidIndex(i, j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return quickUnionNoBottom.connected(vTop, getSiteIndex(i, j));
    }
    /**
     * does the system percolate?
     *
     * @return percolate or not
     */
    public boolean percolates() {
        return quickUnion.connected(vTop, vBottom);
    }
}
