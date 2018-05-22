import org.junit.Assert;
import org.junit.Test;

public class WordNetTest {
    @Test
    public void test1() {
        WordNet wordNet = new WordNet(getClass().getResource("synsets.txt").getFile(), getClass().getResource("hypernyms.txt").getFile());

        Assert.assertEquals(11, wordNet.distance("paving", "direct_examination"));
        Assert.assertEquals(11, wordNet.distance("journal", "Galeopsis"));
    }

    @Test
    public void test2() {
        try {
            WordNet wordNet = new WordNet(getClass().getResource("synsets3.txt").getFile(), getClass().getResource("hypernyms3InvalidTwoRoots.txt").getFile());
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            WordNet wordNet = new WordNet(getClass().getResource("synsets6.txt").getFile(), getClass().getResource("hypernyms6InvalidTwoRoots.txt").getFile());
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }

        try {
            WordNet wordNet = new WordNet(getClass().getResource("synsets6.txt").getFile(), getClass().getResource("hypernyms6InvalidCycle+Path.txt").getFile());
            Assert.fail();
        } catch (IllegalArgumentException ignored) {
        }
    }
}
