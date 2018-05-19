import edu.princeton.cs.algs4.Bag;

/**
 * Implementation using Adjacent list
 */
public class Graph {
    private final int V;
    private final Bag<Integer>[] adj;

    public Graph(int vertexCount) {
        this.V = vertexCount;
        this.adj = (Bag<Integer>[]) new Bag[vertexCount];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
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

        return count / 2;
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
