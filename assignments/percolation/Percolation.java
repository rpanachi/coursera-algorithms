public class Percolation {

  private int N;
  private WeightedQuickUnionUF wquuf;
  private boolean[] sites;

  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {
    this.N = N;
    this.wquuf = new WeightedQuickUnionUF(N * N);
    this.sites = new boolean[N * N];
  }

  // open site (row i, column j) if it is not already
  public void open(int row, int column) {
    int i = row - 1;
    int j = column - 1;

    this.validateBounds(i, j);
    if (this.isSiteOpen(i, j)) return;

    this.sites[index(i, j)] = true;

    if (isValidBounds(i, j-1) && isSiteOpen(i, j-1)) {
      this.wquuf.union(index(i, j), index(i, j-1));
    }
    if (isValidBounds(i, j+1) && isSiteOpen(i, j+1)) {
      this.wquuf.union(index(i, j), index(i, j+1));
    }
    if (isValidBounds(i-1, j) && isSiteOpen(i-1, j)) {
      this.wquuf.union(index(i-1, j), index(i, j));
    }
    if (isValidBounds(i+1, j) && isSiteOpen(i+1, j)) {
      this.wquuf.union(index(i+1, j), index(i, j));
    }
  }

  // is site (row i, column j) open?
  public boolean isOpen(int row, int column) {
    int i = row - 1;
    int j = column - 1;

    this.validateBounds(i, j);
    return this.isSiteOpen(i, j);
  }

  // is site (row i, column j) full?
  public boolean isFull(int row, int column) {
    int i = row - 1;
    int j = column - 1;

    this.validateBounds(i, j);
    return !this.sites[index(i, j)];
  }

  // does the system percolate?
  public boolean percolates() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (this.wquuf.connected(index(0, i), index(N - 1, j))) return true;
      }
    }
    return false;
  }

  private boolean isValidBounds(int i, int j) {
     return i >= 0 && i < N && j >= 0 && j < N;
  }

  private void validateBounds(int i, int j) {
     if (!isValidBounds(i, j)) {
       throw new IndexOutOfBoundsException("(" + i + ", " + j + ")");
     }
  }

  private boolean isSiteOpen(int i, int j) {
    return this.sites[index(i, j)];
  }

  private int index(int row, int column) {
    return (row * N) + column;
  }

  private void puts(Object o) {
    System.out.println("" + o);
  }

}
