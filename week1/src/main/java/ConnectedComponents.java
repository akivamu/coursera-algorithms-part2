public class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int currentId;

    public ConnectedComponents(Graph G) {
        this.marked = new boolean[G.V()];
        this.id = new int[G.V()];
        this.currentId = 0;

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                // Process visiting all vertices belong to this component
                dfs(G, v);

                // Next component
                currentId++;
            }
        }

    }

    public int count() {
        return currentId;
    }

    public int id(int v) {
        return id[v];
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = currentId;
        for (int w : G.adj(v))
            if (!marked[w]) {
                dfs(G, w);
            }
    }

}
