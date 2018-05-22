import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordNet {
    private final List<String[]> synSets = new ArrayList<>();
    private final Digraph graph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || synsets.length() == 0 || hypernyms == null || hypernyms.length() == 0) {
            throw new IllegalArgumentException();
        }

        In in = new In(synsets);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            synSets.add(tokens[1].split(" "));
        }
        in.close();

        in = new In(hypernyms);
        graph = new Digraph(synSets.size());
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);

            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(id, Integer.parseInt(tokens[i]));
            }
        }
        in.close();
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        Set<String> ret = new HashSet<>();
        for (String[] nouns : synSets) {
            ret.addAll(Arrays.asList(nouns));
        }
        return ret;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        for (String[] nouns : synSets) {
            for (String noun : nouns) {
                if (noun.contentEquals(word)) return true;
            }
        }
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        // Find ids
        int v = -1;
        int w = -1;
        for (int i = 0; i < synSets.size(); i++) {
            for (String noun : synSets.get(i)) {
                if (v != -1 && w != -1) break;

                if (v == -1 && noun.contentEquals(nounA)) {
                    v = i;
                }

                if (w == -1 && noun.contentEquals(nounB)) {
                    w = i;
                }
            }
        }
        if (v == -1 || w == -1) throw new IllegalArgumentException();

        SAP sap = new SAP(graph);
        return sap.length(v, w);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        // Find ids
        int v = -1;
        int w = -1;
        for (int i = 0; i < synSets.size(); i++) {
            for (String noun : synSets.get(i)) {
                if (v != -1 && w != -1) break;

                if (v == -1 && noun.contentEquals(nounA)) {
                    v = i;
                }

                if (w == -1 && noun.contentEquals(nounB)) {
                    w = i;
                }
            }
        }
        if (v == -1 || w == -1) throw new IllegalArgumentException();

        SAP sap = new SAP(graph);
        int id = sap.ancestor(v, w);
        if (id == -1) return null;

        StringBuilder sb = new StringBuilder();
        for (String noun : synSets.get(id)) {
            sb.append(noun).append(" ");
        }
        return sb.toString().trim();
    }
}
