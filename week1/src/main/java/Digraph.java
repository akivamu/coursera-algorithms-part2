import edu.princeton.cs.algs4.Bag;

public class Digraph {
    private final int V;
    private final Bag<Integer>[] adj;

    public Digraph(int vertexCount) {
        this.V = vertexCount;
        this.adj = (Bag<Integer>[]) new Bag[vertexCount];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public int E() {
        int count = 0;

        for (Bag<Integer> bag : adj) {
            count += bag.size();
        }

        return count;
    }

    public String toString() {
        String string = "";
        for (int v = 0; v < V; v++) {
            for (Integer w : adj(v)) {
                string += v + " - " + w + "\n";
            }
        }

        return string;
    }
}
