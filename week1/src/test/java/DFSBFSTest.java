import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DFSBFSTest {
    private Graph graph;

    @Before
    public void setup(){
        graph = new Graph(13);
        graph.addEdge(0, 5);
        graph.addEdge(3, 4);
        graph.addEdge(0, 1);
        graph.addEdge(9, 12);
        graph.addEdge(6, 4);
        graph.addEdge(5, 4);
        graph.addEdge(0, 2);
        graph.addEdge(11, 12);
        graph.addEdge(9, 10);
        graph.addEdge(0, 6);
        graph.addEdge(7, 8);
        graph.addEdge(9, 11);
        graph.addEdge(5, 3);
    }

    @Test
    public void testDFS() {
        DepthFirstPaths paths = new DepthFirstPaths(graph, 0);

        Assert.assertTrue(paths.hasPathTo(5));
        Assert.assertTrue(paths.hasPathTo(1));
        Assert.assertTrue(paths.hasPathTo(2));
        Assert.assertTrue(paths.hasPathTo(6));
        Assert.assertTrue(paths.hasPathTo(4));
        Assert.assertFalse(paths.hasPathTo(12));

        StdOut.println("---- DFS");
        for (int v = 0; v < graph.V(); v++) {
            if (paths.hasPathTo(v)) {
                StdOut.println("Path to " + v + ":");

                for (Integer vertex : paths.pathTo(v)) {
                    StdOut.print(vertex + " ");
                }
                StdOut.println();
            }
        }
    }

    @Test
    public void testBFS() {
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, 0);

        Assert.assertTrue(paths.hasPathTo(5));
        Assert.assertTrue(paths.hasPathTo(1));
        Assert.assertTrue(paths.hasPathTo(2));
        Assert.assertTrue(paths.hasPathTo(6));
        Assert.assertTrue(paths.hasPathTo(4));
        Assert.assertFalse(paths.hasPathTo(12));

        StdOut.println("---- BFS");
        for (int v = 0; v < graph.V(); v++) {
            if (paths.hasPathTo(v)) {
                StdOut.println("Path to " + v + ":");

                for (Integer vertex : paths.pathTo(v)) {
                    StdOut.print(vertex + " ");
                }
                StdOut.println();
            }
        }
    }
}
