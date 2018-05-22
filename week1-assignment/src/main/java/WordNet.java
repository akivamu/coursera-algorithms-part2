import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordNet {
    private final List<String[]> synSets = new ArrayList<>();
    private final Map<String, Set<Integer>> words = new HashMap<>();
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || synsets.length() == 0 || hypernyms == null || hypernyms.length() == 0) {
            throw new IllegalArgumentException();
        }

        // Read words
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);
            String[] nouns = tokens[1].split(" ");

            synSets.add(nouns);

            for (String noun : nouns) {
                Set<Integer> ids;
                if (words.containsKey(noun)) {
                    ids = words.get(noun);
                } else {
                    ids = new HashSet<>();
                    words.put(noun, ids);
                }
                ids.add(id);
            }
        }
        in.close();

        // Read hyperNyms
        in = new In(hypernyms);
        Digraph graph = new Digraph(synSets.size());
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            int id = Integer.parseInt(tokens[0]);

            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(id, Integer.parseInt(tokens[i]));
            }
        }
        in.close();

        Topological topological = new Topological(graph);
        if (!topological.hasOrder()) throw new IllegalArgumentException();

        int rootCount = 0;
        for (int i = 0; i < graph.V(); i++) {
            if (graph.outdegree(i) == 0) {
                rootCount++;
            }

            if (rootCount > 1) throw new IllegalArgumentException();
        }

        sap = new SAP(graph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return words.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return words.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        Set<Integer> id1 = findIds(nounA);
        Set<Integer> id2 = findIds(nounB);
        return sap.length(id1, id2);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        Set<Integer> id1 = findIds(nounA);
        Set<Integer> id2 = findIds(nounB);

        int id = sap.ancestor(id1, id2);
        if (id == -1) return null;

        StringBuilder sb = new StringBuilder();
        for (String noun : synSets.get(id)) {
            sb.append(noun).append(" ");
        }
        return sb.toString().trim();
    }

    private Set<Integer> findIds(String noun) {
        if (!words.containsKey(noun)) {
            throw new IllegalArgumentException();
        }

        Set<Integer> ret = words.get(noun);

        if (ret.isEmpty()) throw new IllegalArgumentException();

        return ret;
    }
}
