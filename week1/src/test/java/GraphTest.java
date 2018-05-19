import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import org.junit.Test;


public class GraphTest {
    @Test
    public void testGraphRepresentationAdjacentList() {
        Graph graph = new Graph(13);
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

        StdOut.println("Edges: " + graph.E());
        StdOut.println(graph);

        Assert.assertEquals(13, graph.V());
        Assert.assertEquals(13, graph.E());
    }
}
