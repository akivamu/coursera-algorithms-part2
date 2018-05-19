import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public BreadthFirstPaths(Graph G, int s) {
        this.s = s;
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);

        while (!q.isEmpty()) {
            int vertex = q.remove();
            marked[vertex] = true;
            for (int adj : G.adj(vertex)) {
                if (!marked[adj]) {
                    q.add(adj);
                    edgeTo[adj] = vertex;
                }
            }
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
