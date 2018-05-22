import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private final Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();

        this.digraph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        validateVertices(v, w);

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int shortestLength = Integer.MAX_VALUE;
        int ret = -1;
        for (int vertex = 0; vertex < digraph.V(); vertex++) {
            int length = bfsV.distTo(vertex) + bfsW.distTo(vertex);
            if (length > 0 && length < shortestLength) {
                shortestLength = length;
                ret = vertex;
            }
        }

        if (ret == -1) return -1;
        else return shortestLength;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        validateVertices(v, w);

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int shortestLength = Integer.MAX_VALUE;
        int ret = -1;
        for (int vertex = 0; vertex < digraph.V(); vertex++) {
            int length = bfsV.distTo(vertex) + bfsW.distTo(vertex);
            if (length > 0 && length < shortestLength) {
                shortestLength = length;
                ret = vertex;
            }
        }
        return ret;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertices(v);
        validateVertices(w);

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int shortestLength = Integer.MAX_VALUE;
        int ret = -1;
        for (int vertex = 0; vertex < digraph.V(); vertex++) {
            int length = bfsV.distTo(vertex) + bfsW.distTo(vertex);
            if (length > 0 && length < shortestLength) {
                shortestLength = length;
                ret = vertex;
            }
        }

        if (ret == -1) return -1;
        else return shortestLength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertices(v);
        validateVertices(w);

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int shortestLength = Integer.MAX_VALUE;
        int ret = -1;
        for (int vertex = 0; vertex < digraph.V(); vertex++) {
            int length = bfsV.distTo(vertex) + bfsW.distTo(vertex);
            if (length > 0 && length < shortestLength) {
                shortestLength = length;
                ret = vertex;
            }
        }
        return ret;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }

    private void validateVertices(int... vertices) {
        for (int vertex : vertices) {
            if (vertex < 0 || vertex >= digraph.V()) throw new IllegalArgumentException();
        }
    }

    private void validateVertices(Iterable<Integer> vertices) {
        if (!vertices.iterator().hasNext()) throw new IllegalArgumentException();

        for (Integer v : vertices) {
            if (v == null) throw new IllegalArgumentException();
        }
    }
}
