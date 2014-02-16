public class Percolation {

  private int N;
  private WeightedQuickUnionUF wquuf;
  private boolean[] sites;

  // create N-by-N grid, with all sites blocked
  public Percolation(int N) {
    this.N = N;
    this.wquuf = new WeightedQuickUnionUF(N * N + 2);
    this.sites = new boolean[N * N + 2];
    this.sites[0] = true;
    this.sites[N * N + 1] = true;
  }

  // open site (row i, column j) if it is not already
  public void open(int i, int j) {
    this.validateBounds(i, j);
    if (this.isSiteOpen(i, j)) return;

    int cell = index(i, j);
    this.sites[cell] = true;

    //if not top row
    if (i != 1 && isOpen(i-1, j)) {
      union(index(i-1, j), cell);
    } else if (i == 1) {
      //connect to virtual top cell
      union(cell, 0);
    }
    //if not bottom row
    if (i != N && isOpen(i+1, j)) {
      union(index(i+1, j), cell);
    } else if (i == N) {
      //connect to virtual bottom cell
      union(cell, N * N + 1);
    }
    //if not left border
    if (j != 1 && isOpen(i, j-1)) {
      union(index(i, j-1), cell);
    }
    //if not right border
    if (j != N && isOpen(i, j+1)) {
      union(index(i, j+1), cell);
    }
  }

  // is site (row i, column j) open?
  public boolean isOpen(int row, int column) {
    this.validateBounds(row, column);
    return this.isSiteOpen(row, column);
  }

  // is site (row i, column j) full?
  public boolean isFull(int row, int column) {
    this.validateBounds(row, column);
    return this.wquuf.connected(index(row, column), 0);
  }

  // does the system percolate?
  public boolean percolates() {
    return this.wquuf.connected(0, N * N + 1);
  }

  private void union(int i, int j) {
    if (!this.wquuf.connected(i, j)) {
      this.wquuf.union(i, j);
    }
  }

  private boolean isValidBounds(int i, int j) {
     return i > 0 && i <= N && j > 0 && j <= N;
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
    return ((row - 1) * N) + column;
  }

  private void puts(Object o) {
    System.out.println("" + o);
  }

}
