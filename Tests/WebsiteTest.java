import Main.Website;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebsiteTest {
    List<Website> sites;

    @BeforeEach
    void setUp() {
        this.sites = new ArrayList<>();
        sites.add(new Website("www.ohyeah.com", "Amazing", Arrays.asList("word1", "word2", "word3")));
        sites.add(new Website("www.longurl.com/klsjdfldjsljfdskfjdksjjfkjsdjfkjsdjfkjsdkjfhskdsjkhfjkdshfkjdshfkjdhskfjhdskjfkdjshfkjsd", "Very long titleejehfsdhfihsdhfsdjhfjkdshfjkdshfkhdsjhfkjdshfjdshfjhsfkjhfjhdjkfhs", Arrays.asList("word2", "word3", "word4")));
        sites.add(new Website(" ", "/¤&*/%weirdsymbols", Arrays.asList("word15", "word26", " ", "word37", "word123", "word342")));
        sites.add(new Website("www.lotsofweirdsymbols%&/(%¤#¤?|}][{€.com", " ", Arrays.asList("WoRd1", "WoRd2", "WoRd3", "")));

    }

    @AfterEach
    void tearDown() {
        sites.clear();
    }

    @Test
    void getWords() {
        Website site1 = sites.get(0);
        Website site2 = sites.get(1);
        Website site3 = sites.get(2);
        Website site4 = sites.get(3);

        assertTrue(site1.getWords().size() == 3);
        assertTrue(site2.getWords().size() == 3);
        assertTrue(site3.getWords().size() == 6);
        assertTrue(site4.getWords().size() == 4);

    }

    @Test
    void getTitle() {
        String title1 = sites.get(0).getTitle();
        String title2 = sites.get(1).getTitle();
        String title3 = sites.get(2).getTitle();
        String title4 = sites.get(3).getTitle();

        assertEquals(title1, "Amazing");
        assertEquals(title2, "Very long titleejehfsdhfihsdhfsdjhfjkdshfjkdshfkhdsjhfkjdshfjdshfjhsfkjhfjhdjkfhs");
        assertEquals(title3, "/¤&*/%weirdsymbols");
        assertEquals(title4, " ");


    }

    @Test
    void getUrl() {
        String url1 = sites.get(0).getUrl();
        String url2 = sites.get(1).getUrl();
        String url3 = sites.get(2).getUrl();
        String url4 = sites.get(3).getUrl();

        assertEquals(url1, "www.ohyeah.com");
        assertEquals(url2, "www.longurl.com/klsjdfldjsljfdskfjdksjjfkjsdjfkjsdjfkjsdkjfhskdsjkhfjkdshfkjdshfkjdhskfjhdskjfkdjshfkjsd");
        assertEquals(url3, " ");
        assertEquals(url4, "www.lotsofweirdsymbols%&/(%¤#¤?|}][{€.com");
    }

    @Test
    void containsWord() {
        assertTrue(sites.get(0).containsWord("word1"));
        assertTrue(sites.get(0).containsWord("word2"));
        assertTrue(sites.get(0).containsWord("word3"));
        assertTrue(sites.get(1).containsWord("word2"));
        assertTrue(sites.get(1).containsWord("word3"));
        assertTrue(sites.get(1).containsWord("word4"));
        assertTrue(sites.get(2).containsWord("word15"));
        assertTrue(sites.get(2).containsWord("word26"));
        assertTrue(sites.get(2).containsWord(" "));
        assertTrue(sites.get(2).containsWord("word37"));
        assertTrue(sites.get(2).containsWord("word123"));
        assertTrue(sites.get(2).containsWord("word342"));
        assertTrue(sites.get(3).containsWord("WoRd1"));
        assertTrue(sites.get(3).containsWord("WoRd2"));
        assertTrue(sites.get(3).containsWord("WoRd3"));
        assertTrue(sites.get(3).containsWord(""));

        assertFalse(sites.get(0).containsWord("word4"));
        assertFalse(sites.get(0).containsWord("word5"));
        assertFalse(sites.get(0).containsWord("word6"));
        assertFalse(sites.get(1).containsWord("word1"));
        assertFalse(sites.get(1).containsWord("word5"));
        assertFalse(sites.get(1).containsWord("word6"));
        assertFalse(sites.get(2).containsWord("word1"));
        assertFalse(sites.get(2).containsWord("word2"));
        assertFalse(sites.get(2).containsWord("      "));
        assertFalse(sites.get(3).containsWord("word1"));
        assertFalse(sites.get(3).containsWord("word2"));
        assertFalse(sites.get(3).containsWord("word3"));
        assertFalse(sites.get(0).containsWord("    "));
    }
}
