package rent.unionfind;

public class UnionFind {
    private int count;
    private final int[] parent;
    private final int[] size;

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }

    public void print() {
        System.out.println("count=" + count);
        for (int i = 0; i < parent.length; i++) {
            int x = i;
            System.out.print(i);
            while (x != parent[x]) {
                x = parent[x];
                System.out.print(" -> " + x);
            }
            System.out.println(", size=" + size[x]);
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        uf.union(1, 2);
        uf.print();
        uf.union(3, 4);
        uf.print();
        uf.union(1, 3);
        uf.print();
    }
}
