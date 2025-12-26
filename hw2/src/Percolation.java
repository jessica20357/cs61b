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

        int r = row - 1;
        int c = col - 1;

        if (open[r][c]) return;

        open[r][c] = true;
        openCount++;

        int index = index(r,c);

        if (row == 1) {
            uf.union(index, top);
            ufFull.union(index, top);
        }
        if (row == n) {
            uf.union(index, bottom);
        }

        // 连接上下左右
        connectIfOpen(r - 1, c, r, c);
        connectIfOpen(r + 1, c, r, c );
        connectIfOpen(r, c - 1, r, c);
        connectIfOpen(r, c + 1, r, c);
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        return open[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        if (!isOpen(row, col)) return false;
        return ufFull.connected(index(row -1, col -1), top);
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
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row or col out of bounds");
        }
    }

    private int index(int r, int c) {
        return r * n + c;
    }

    public void connectIfOpen(int r1, int c1, int r2, int c2) {
        if (r2 < 0 || r2 >= n || c2 < 0 || c2 >= n) return;

    }
}
