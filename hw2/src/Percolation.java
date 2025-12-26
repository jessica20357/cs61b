import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private final int n;
    private final boolean[][] open;
    private int openCount;

    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufFull;

    private final int top;
    private final int bottom;


    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0) {
            throw new IllegalArgumentException("N must be > 0");
        }

        this.n = N;
        this.open = new boolean[N][N];
        this.openCount = 0;

        int size = N * N;
        this.top = size;
        this.bottom = size + 1;

        this.uf = new WeightedQuickUnionUF(size + 2);
        this.ufFull = new WeightedQuickUnionUF(size + 1);

    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);

        if (open[row][col]) return;

        open[row][col] = true;
        openCount++;

        int index = index(row,col);

        if (row == 0) {
            uf.union(index, top);
            ufFull.union(index, top);
        }
        if (row == n - 1) {
            uf.union(index, bottom);
        }

        // 连接上下左右
        connectIfOpen(row - 1, col, index);
        connectIfOpen(row + 1, col, index);
        connectIfOpen(row, col - 1, index);
        connectIfOpen(row, col + 1, index);
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        return open[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        if (!isOpen(row, col)) return false;
        return ufFull.connected(index(row, col), top);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openCount;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return uf.connected(top, bottom);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    private void validate(int row, int col) {
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
            throw new IllegalArgumentException("row or col out of bounds");
        }
    }

    private int index(int r, int c) {
        return r * n + c;
    }

    public void connectIfOpen(int row, int col, int index) {
        if (row < 0 || row >= n || col < 0 || col >= n) return;
        if (open[row][col]) {
            int neighbor = index(row, col);
            uf.union(index, neighbor);
            ufFull.union(index, neighbor);
        }
    }
}
