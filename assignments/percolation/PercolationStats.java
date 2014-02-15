import java.util.Random;

public class PercolationStats {

  private int N;
  private int T;
  private double[] thresholds;
  private double mean = 0.0;
  private double stddev = 0.0;
  private double confidenceLo = 0.0;
  private double confidenceHi = 0.0;

  // perform T independent computational experiments on an N-by-N grid
  public PercolationStats(int N, int T) {
    if (N < 0 || T < 0) throw new IllegalArgumentException();

    this.N = N;
    this.T = T;
    this.thresholds = new double[T];

    double N2 = (N * N);

    for (int t = 0; t < this.T; t++) {
      this.thresholds[t] = ((double) doMonteCarloSimulation()) / N2;
    }

    this.mean = StdStats.mean(thresholds);
    this.stddev = StdStats.stddev(thresholds);
    double u = ((1.96 * this.stddev) / Math.sqrt(T));
    this.confidenceLo = this.mean - u;
    this.confidenceHi = this.mean + u;
  }

  // sample mean of percolation threshold
  public double mean() {
    return this.mean;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return this.stddev;
  }

  // returns lower bound of the 95% confidence interval
  public double confidenceLo() {
    return this.confidenceLo;
  }

  // returns upper bound of the 95% confidence interval
  public double confidenceHi() {
    return this.confidenceHi;
  }

  private int doMonteCarloSimulation() {
    Percolation percolation = new Percolation(N);
    Random generator = new Random(System.currentTimeMillis());

    int openSites = 0;
    while (!percolation.percolates()) {
      int p = generator.nextInt(N);
      int q = generator.nextInt(N);
      if (percolation.isFull(p, q)) {
        percolation.open(p, q);
        openSites++;
      }
    }
    return openSites;
  }

  private void draw(Percolation percolation) {
    System.out.print("  ");
    for (int i = 0; i < N; i++) {
      System.out.print(Integer.toString(i) + " ");
    }
    System.out.print("\n");

    for (int i = 0; i < N; i++) {
      System.out.print(Integer.toString(i) + " ");
      for (int j = 0; j < N; j++) {
        if (percolation.isOpen(i, j)) {
          System.out.print(". ");
        } else {
          System.out.print("# ");
        }
      }
      System.out.print("\n");
    }
  }

  // test client, described below
  public static void main(String[] args) {
    int N = new Integer(args[0]);
    int T = new Integer(args[1]);

    PercolationStats ps = new PercolationStats(N, T);

    System.out.println("mean                    = " + ps.mean());
    System.out.println("stddev                  = " + ps.stddev());
    System.out.println("95% confidence interval = "
        + ps.confidenceLo() + ", " + ps.confidenceHi());
  }
}
