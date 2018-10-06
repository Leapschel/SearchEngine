import FuzzySearch.EditDistance;
import FuzzySearch.FuzzyHelper;
import Index.*;

import Main.Website;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @ author Leah Peschel
 * A class for testing the fuzzy search mechanism. The fuzzyLookup method and the getFuzzyWords method of the FuzzyHelper class and the
 * getEditDistance method of the EditDistance class are tested on the inverted index.
 */

public class FuzzySearchTest {

    private InvertedIndex invertedIndex;
    private FuzzyHelper fuzzyHelper;
    private EditDistance editDistance;

    @BeforeEach
    void setUp() {
        List<Website> sites = new ArrayList<Website>();
        sites.add(new Website("e1.com", "title1", Arrays.asList("funk", "hiphop", "jazz", "soul")));
        sites.add(new Website("e2.com", "title2", Arrays.asList("funke")));
        sites.add(new Website("e3.com", "title3", Arrays.asList("territories")));
        sites.add(new Website("e4.com", "title4", Arrays.asList("abc", "qrst")));


        invertedIndex = new InvertedIndexHashMap();
        invertedIndex.build(sites);
        fuzzyHelper = new FuzzyHelper(invertedIndex);
        editDistance = new EditDistance();


    }

    @AfterEach
    void tearDown() {
        invertedIndex = null;
        fuzzyHelper = null;
        editDistance = null;
    }



    @Test
    void testFuzzyLookup01() {

        assertEquals(1, fuzzyHelper.fuzzyLookup("abc").size());
        assertEquals("[abc]", fuzzyHelper.getFuzzyWords());
        assertEquals("[Main.Website{title='title4', url='e4.com', words=[abc, qrst]}]", fuzzyHelper.fuzzyLookup("abc").toString());

    }

    @Test
    void testFuzzyLookup02() {
        assertEquals(1, fuzzyHelper.fuzzyLookup("ab").size());
        assertEquals("[abc]", fuzzyHelper.getFuzzyWords());
        assertEquals("[Main.Website{title='title4', url='e4.com', words=[abc, qrst]}]", fuzzyHelper.fuzzyLookup("abc").toString());
    }


    @Test
    void testFuzzyLookup03() {
        assertEquals(0, fuzzyHelper.fuzzyLookup("a").size());
        assertEquals("[]", fuzzyHelper.getFuzzyWords());

    }


    @Test
    void testFuzzyLookup04() {
        assertEquals(1, fuzzyHelper.fuzzyLookup("abcd").size());
        assertEquals("[Main.Website{title='title4', url='e4.com', words=[abc, qrst]}]", fuzzyHelper.fuzzyLookup("abc").toString());

    }


    @Test
    void testFuzzyLookup05() {
        assertEquals(1, fuzzyHelper.fuzzyLookup("a bc").size());
        assertEquals("[Main.Website{title='title4', url='e4.com', words=[abc, qrst]}]", fuzzyHelper.fuzzyLookup("abc").toString());

    }

    @Test
    void testFuzzyLookup06() {
        assertEquals(2, fuzzyHelper.fuzzyLookup("funkr").size());
        assertEquals("[funk, funke]", fuzzyHelper.getFuzzyWords());
        assertEquals("[Main.Website{title='title1', url='e1.com', words=[funk, hiphop, jazz, soul]}, Main.Website{title='title2', url='e2.com', words=[funke]}]", fuzzyHelper.fuzzyLookup("funkr").toString());

    }

    @Test
     // testing operation replacement:
        // one char in str2 has to be replaced in order for the two strings to be similar
    void testEditDistance01() {
        String str1 = "Hallo";
        char[] one = str1.toCharArray();
        String str2 = "Hello";
        char[] two = str2.toCharArray();

        assertEquals(1, editDistance.getEditDistance(one, two));
    }

    @Test
     // testing operation insertion:
        // one char in str2 has to be inserted in order for the two strings to be similar
    void testEditDistance02() {
        String str1 = "Hallo";
        char[] one = str1.toCharArray();
        String str2 = "Hllo";
        char[] two = str2.toCharArray();

        assertEquals(1, editDistance.getEditDistance(one, two));
    }

    @Test
    // testing operation deletion:
        // one char in str2 has to be deleted in order for the two strings to be similar
    void testEditDistance03() {
        String str1 = "Hallo";
        char[] one = str1.toCharArray();
        String str2 = "Halloo";
        char[] two = str2.toCharArray();

        assertEquals(1, editDistance.getEditDistance(one, two));
    }

    @Test
    // testing multiple operations
    void testEditDistance04() {
        String str1 = "Hallo";
        char[] one = str1.toCharArray();
        String str2 = "Kxtlo";
        char[] two = str2.toCharArray();

        assertEquals(3, editDistance.getEditDistance(one, two));
    }


    @Test
    void testEditDistance05() {
        String str1 = "Hallo";
        char[] one = str1.toCharArray();
        String str2 = "Xzftu";
        char[] two = str2.toCharArray();

        assertEquals(5, editDistance.getEditDistance(one, two));
    }

    @Test
    // testing if position of shorter word, char [] one, as first input word matters
    void testEditDistance06() {
        String str1 = "Hallo";
        char[] one = str1.toCharArray();
        String str2 = "Hallodoooooooooo";
        char[] two = str2.toCharArray();

        assertEquals(11, editDistance.getEditDistance(one, two));
    }


    @Test
    // testing if position of longer word, char [] one, as first input word matters
    void testEditDistance07() {
        String str1 = "Hallodoooooooooo";
        char[] one = str1.toCharArray();
        String str2 = "Hallo";
        char[] two = str2.toCharArray();

        assertEquals(11, editDistance.getEditDistance(one, two));
    }

    @Test
        // testing two equal words
    void testEditDistance08() {
        String str1 = "Hallo";
        char[] one = str1.toCharArray();
        String str2 = "Hallo";
        char[] two = str2.toCharArray();

        assertEquals(0, editDistance.getEditDistance(one, two));
    }

    @Test
    // report example, page
    void testEditDistance09() {
        String str1 = "QueryHelper";
        char[] one = str1.toCharArray();
        String str2 = "QeryyHrlper";
        char[] two = str2.toCharArray();

        assertEquals(3, editDistance.getEditDistance(one, two));
    }

}
