
/**
 * @author brlv
 *
 */
public class PercolationStats {
    /**
     * confidence interval.
     */
    private static final double CONFI = 1.96;
    /**
     * offset for random.
     */
    private static final double OFFSET = 0.5;
    /**
     * sample mean of percolation threshold.
     */
    private double sampleMean;

    /**
     * result for each run.
     */
    private double[] result;

    /**
     * times of experiments.
     */
    private int times;

    /**
     * size of the grid.
     */
    private int size;


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
        times = t;

        result = new double[t];
        for (int i = 0; i < t; i++) {
            result[i] = (double) run() / (size * size);
            sampleMean += result[i];
        }

        sampleMean = sampleMean / t;
    }

    /**
     * run once percolate.
     *
     * @return how many sites is opened when percolates
     */
    private int run() {
        Percolation perco = new Percolation(size);

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
    public double mean() {
        return sampleMean;
    }
    /**
     * return sample standard deviation of percolation threshold.
     *
     * @return sample standard deviation
     */
    public double stddev() {
        double stddev = 0.0;
        for (int i = 0; i < times; i++) {
            stddev += (result[i] - sampleMean) * (result[i] - sampleMean);
        }
        return Math.sqrt(stddev / (times - 1));
    }
    /**
     * test client.
     *
     * @param args command line arguments.
     */
    public static void main(final String[] args) {
        if (args.length != 2) {
            throw new java.lang.IllegalArgumentException();
        }
        int size = Integer.parseInt(args[0]);
        int times = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(size, times);

        Out out = new Out();
        double mean = stats.mean();
        double stddev = stats.stddev();
        double upper = mean - PercolationStats.CONFI * stddev / times;
        double lower = mean + PercolationStats.CONFI * stddev / times;
        out.printf("mean                    = %f\n", mean);
        out.printf("stddev                  = %f\n", stddev);
        out.printf("95%% confidence interval = %f, %f", upper, lower);
    }
}
