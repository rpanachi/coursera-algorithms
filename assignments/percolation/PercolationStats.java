import java.util.Random;

public class PercolationStats {

  private int N;
  private int T;
  private Percolation percolation;

  // perform T independent computational experiments on an N-by-N grid
  public PercolationStats(int N, int T) {
    if (N < 0 || T < 0) throw new IllegalArgumentException();

    this.N = N;
    this.T = T;
    this.percolation = new Percolation(N);

    int count = 0;
    Random generator = new Random(System.currentTimeMillis());
    while(!percolation.percolates()) {
      int p = generator.nextInt(N);
      int q = generator.nextInt(N);

      if (this.percolation.isFull(p, q)) {
        this.percolation.open(p, q);
        count++;
      }
    }

    System.out.println("Percolate after " + count + " iterations");
    this.percolation.print();

  }

  // sample mean of percolation threshold
  public double mean() {
    return 0.0;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return 0.0;
  }

  // returns lower bound of the 95% confidence interval
  public double confidenceLo() {
    return 0.0;
  }

  // returns upper bound of the 95% confidence interval
  public double confidenceHi() {
    return 0.0;
  }

  // test client, described below
  public static void main(String[] args) {
    int N = new Integer(args[0]);
    int T = new Integer(args[1]);

    PercolationStats ps = new PercolationStats(N, T);
  }
}
