import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class SAPTest {
    private Digraph readDigraph(String fileName) {
        In in = new In(getClass().getResource(fileName).getFile());
        return new Digraph(in);
    }

    private void testSAP(String digraphFileName, int v, int w, int eLength, int eAncestor) {
        testSAP(digraphFileName, Arrays.asList(v), Arrays.asList(w), eLength, eAncestor);
    }

    private void testSAP(String digraphFileName, Iterable<Integer> v, Iterable<Integer> w, int eLength, int eAncestor) {
        Digraph digraph = readDigraph(digraphFileName);
        SAP sap = new SAP(digraph);

        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertEquals(eLength, length);
        Assert.assertEquals(eAncestor, ancestor);
    }

    @Test
    public void test1() {
        testSAP("digraph1.txt", 3, 11, 4, 1);
        testSAP("digraph1.txt", 9, 12, 3, 5);
        testSAP("digraph1.txt", 7, 2, 4, 0);
        testSAP("digraph1.txt", 1, 6, -1, -1);
        testSAP("digraph1.txt", 3, 3, 0, 3);
    }

    @Test
    public void test2() {
        testSAP("digraph1.txt", Collections.EMPTY_LIST, Arrays.asList(11), -1, -1);

        try {
            testSAP("digraph1.txt", null, Arrays.asList(11), -1, -1);
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }
    }
}
