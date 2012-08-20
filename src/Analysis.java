
public class Analysis {
    public static void main(final String[] args) {

        int N = 10;
        while (N < 500) {
            Stopwatch sw = new Stopwatch();
            Timing.trial(N, 770858);
            double time = sw.elapsedTime();
            System.out.println(String.format("%d %f", N, time));

            N += 100;
        }
    }
}