
/**
 * @author brlv
 *
 */
public class PercolationStats {
    /**
     * Percolation interface.
     */
    private Percolation perco;

    /**
     * sample mean of percolation threshold.
     */
    private double sampleMean;

    /**
     * size of the grid.
     */
    private int size;
    /**
     * offset for random.
     */
    private static final double OFFSET = 0.5;
    /**
     * perform T independent computational experiments on an N-by-N grid.
     *
     * @param n size of grid
     * @param t times of experiments
     */
    public PercolationStats(final int n, final int t) {
        if (n < 1 || t < 1) {
            throw new java.lang.IllegalArgumentException();
        }
        size = n;
        perco = new Percolation(n);
        int[] times = new int[t];
        for (int i = 0; i < t; i++) {
            times[i] = run();
            sampleMean += times[i];
        }

        sampleMean = sampleMean / (t * size * size);
    }

    /**
     * run once percolate.
     *
     * @return how many sites is opened when percolates
     */
    private int run() {
        int numSites = 0;
        while (!perco.percolates()) {
            int p = getRandomIndex();
            int q = getRandomIndex();

            if (perco.isOpen(p, q)) {
                continue;
            }

            perco.open(p, q);
            numSites++;
        }

        return numSites;
    }

    /**
     * return a random index between 1 - n.
     *
     * @return random index
     */
    private int getRandomIndex() {
        return (int) Math.round((Math.random() * size + OFFSET));
    }

    /**
     * return sample mean of percolation threshold.
     *
     * @return mean of percolation threshold
     */
    public final double mean() {
        return sampleMean;
    }
    /**
     * return sample standard deviation of percolation threshold.
     *
     * @return sample standard deviation
     */
    public final double stddev() {
        return 0.0;
    }
    /**
     * test client.
     *
     * @param args command line arguments.
     */
    public static void main(final String[] args) {
        PercolationStats stats = new PercolationStats(200, 100);
        Out out = new Out();
        out.printf("mean %f", stats.mean());
    }
}
