import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Assert;
import org.junit.Test;

public class SAPTest {
    private Digraph readDigraph(String fileName) {
        In in = new In(getClass().getResource(fileName).getFile());
        return new Digraph(in);
    }

    private void testSAP(String digraphFileName, int v, int w, int eLength, int eAncestor) {
        Digraph digraph = readDigraph(digraphFileName);
        SAP sap = new SAP(digraph);

        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.println(v + "-" + w + ": length " + length);
        StdOut.println(v + "-" + w + ": ancestor " + ancestor);
        Assert.assertEquals(eLength, length);
        Assert.assertEquals(eAncestor, ancestor);
    }

    @Test
    public void test1() {
        testSAP("digraph1.txt", 3, 11, 4, 1);
        testSAP("digraph1.txt", 9, 12, 3, 5);
        testSAP("digraph1.txt", 7, 2, 4, 0);
        testSAP("digraph1.txt", 1, 6, -1, -1);
    }

}
