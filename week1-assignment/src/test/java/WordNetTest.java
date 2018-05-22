import org.junit.Assert;
import org.junit.Test;

public class WordNetTest {
    @Test
    public void test1() {
        WordNet wordNet = new WordNet(getClass().getResource("synsets.txt").getFile(), getClass().getResource("hypernyms.txt").getFile());

        Assert.assertEquals(11, wordNet.distance("paving", "direct_examination"));
        Assert.assertEquals(11, wordNet.distance("journal", "Galeopsis"));
    }
}
