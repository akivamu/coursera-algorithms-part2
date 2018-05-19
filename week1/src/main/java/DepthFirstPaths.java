import java.util.ArrayList;
import java.util.List;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        List<Integer> path = new ArrayList<>();
        int currentVertex = v;

        do {
            path.add(currentVertex);
            currentVertex = edgeTo[currentVertex];
        } while (currentVertex != s);

        path.add(s);

        return path;
    }
}
