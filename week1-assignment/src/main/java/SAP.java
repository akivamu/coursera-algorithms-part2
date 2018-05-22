import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class SAP {
    private final Digraph digraph;
    private final Result result = new Result();

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();

        this.digraph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        result.calculate(v, w);
        return result.shortestLength;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        result.calculate(v, w);
        return result.shortestCommonAncestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        result.calculate(v, w);
        return result.shortestLength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        result.calculate(v, w);
        return result.shortestCommonAncestor;
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

    private class Result {
        int shortestLength = Integer.MAX_VALUE;
        int shortestCommonAncestor = -1;

        public void calculate(int v, int w) {
            if (v < 0 || v >= digraph.V() || w < 0 || w >= digraph.V()) throw new IllegalArgumentException();

            calculate(Arrays.asList(v), Arrays.asList(w));
        }

        public void calculate(Iterable<Integer> v, Iterable<Integer> w) {
            shortestLength = Integer.MAX_VALUE;
            shortestCommonAncestor = -1;

            validateVertices(v);
            validateVertices(w);

            BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
            BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

            for (int vertex = 0; vertex < digraph.V(); vertex++) {
                int length = bfsV.distTo(vertex) + bfsW.distTo(vertex);
                if (length >= 0 && length < shortestLength) {
                    shortestLength = length;
                    shortestCommonAncestor = vertex;
                }
            }

            if (shortestCommonAncestor == -1) shortestLength = -1;
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
