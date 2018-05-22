public class Outcast {
    private final WordNet wordNet;

    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    public String outcast(String[] nouns) {
        int dMax = 0;
        String ret = null;

        for (String nouni : nouns) {
            int di = 0;
            for (String nounx : nouns) {
                di += wordNet.distance(nouni, nounx);
            }
            if (di > dMax) {
                dMax = di;
                ret = nouni;
            }
        }

        return ret;
    }
}
