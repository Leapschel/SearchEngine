
import Main.*;
import Index.*;
import Scoring.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryHelperTest {

    /**
     * @ author Leah Peschel
     * A class for unit testing
     */

    Index indx;
    QueryHelper queryHelp;
    Score score;
    List<Website> sites;
    RankHelper rank;

    @BeforeEach
    void setup() {

        indx = new InvertedIndexHashMap();
        score = new TFIDFScore();
        queryHelp = new QueryHelper(indx);
        sites = new ArrayList<>();
        rank = new RankHelper(indx);

        sites.add(new Website("w1.com", "Random", Arrays.asList("Azafata", "Trump", "Dnmrk")));
        sites.add(new Website("w2.com", "Denmark and Uruguay", Arrays.asList("Africa", "Uruguay", "Denmark")));
        // same website added twice
        sites.add(new Website("w3.com", "Denmark", Arrays.asList("Copenhagen", "is", "the", "capital", "of", "Denmark")));
        sites.add(new Website("w3.com", "Denmark", Arrays.asList("Copenhagen", "is", "the", "capital", "of", "Denmark")));

        sites.add(new Website("w4.com", "Brasil", Arrays.asList("Brasília", "is", "the", "capital", "of", "Brasil")));
        sites.add(new Website("w5.com", "Elephant", Arrays.asList("memory", "animal", "Zoo")));

        sites.add(new Website("w6.com", "matches words of 3 websites", Arrays.asList("Copenhagen", "animal", "Brasil", "match")));
        sites.add(new Website("w7.com", "or", Arrays.asList("or", "OR", "Or")));
        sites.add(new Website("w8.com", " ", Arrays.asList(" ", "word", "other word")));


        indx.build(sites);
    }


    @AfterEach
    void tearDown() {

        indx = null;
        score = null;
        queryHelp = null;
        sites.clear();
        //probably not finalMap ! clear
    }

    @Test
    void testSingleWord_MatchingWebsites() {
        String query = "Brasil";
        assertEquals(2, queryHelp.getMatchingWebsites(query).size());

    }

    @Test
    void testMultipleWords_MatchingWebsites() {
        String query = "Copenhagen match"; // Expected: website (ws) containing both words
        assertEquals(1, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[Main.Website{title='matches words of 3 websites', url='w6.com', words=[Copenhagen, animal, Brasil, match]}]", queryHelp.getMatchingWebsites(query).toString());
    }

    @Test
        // single 'OR' in query
    void testSingleORQuery_MatchingWebsites() {
        String query = "Denmark OR Brasil"; // Expected: ws containing one or the other word, ws may contain both words
        assertEquals(4, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[Main.Website{title='Denmark', url='w3.com', words=[Copenhagen, is, the, capital, of, Denmark]}, Main.Website{title='Brasil', url='w4.com', words=[Brasília, is, the, capital, of, Brasil]}, Main.Website{title='matches words of 3 websites', url='w6.com', words=[Copenhagen, animal, Brasil, match]}, Main.Website{title='Denmark and Uruguay', url='w2.com', words=[Africa, Uruguay, Denmark]}]", queryHelp.getMatchingWebsites(query).toString());

    }

    @Test
        // single, lower case 'or'
    void testSingleorQuery_MatchingWebsites() {

        String query = "Denmark or Zoo"; // to improve: should only work with uppercase OR
        assertEquals(0, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[]", queryHelp.getMatchingWebsites(query).toString());

    }

    @Test
        // lower case 'or' surrounded by comma
    void testRandomComma_MatchingWebsites() {
        String query = "animal , or , Zoo";
        assertEquals(0, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[]", queryHelp.getMatchingWebsites(query).toString());

    }

    @Test
        // lower case 'or' surrounded by comma
    void testRandomComma2_MatchingWebsites() {
        String query = "animal, Zoo";
        assertEquals(1, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[Main.Website{title='Elephant', url='w5.com', words=[memory, animal, Zoo]}]", queryHelp.getMatchingWebsites(query).toString()); // add expected result

    }

    @Test

    void testSpaces_MatchingWebsites() {
        String query = "match OR , , OR Zoo";
        assertEquals(2, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[Main.Website{title='matches words of 3 websites', url='w6.com', words=[Copenhagen, animal, Brasil, match]}, Main.Website{title='Elephant', url='w5.com', words=[memory, animal, Zoo]}]", queryHelp.getMatchingWebsites(query).toString());

    }

    @Test

    void testSpaces2_MatchingWebsites() {
        String query = "animal    match";
        assertEquals(1, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[Main.Website{title='matches words of 3 websites', url='w6.com', words=[Copenhagen, animal, Brasil, match]}]", queryHelp.getMatchingWebsites(query).toString());

    }

    @Test
        // mix of lower case letters
    void testLowerUpperCase_MatchingWebsites() {
        String query = "BrAsIl OR zOO";
        assertEquals(3, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[Main.Website{title='Elephant', url='w5.com', words=[memory, animal, Zoo]}, Main.Website{title='matches words of 3 websites', url='w6.com', words=[Copenhagen, animal, Brasil, match]}, Main.Website{title='Brasil', url='w4.com', words=[Brasília, is, the, capital, of, Brasil]}]", queryHelp.getMatchingWebsites(query).toString());

    }

    @Test
        // multiple, repeated OR in query
    void testRepeatedOR_MatchingWebsites() { // to improve: does not find second Word, recognises ORs as word - does that influence scoring?
        String query = "Copenhagen OR OR OR OR Brasil";
        assertEquals(3, queryHelp.getMatchingWebsites(query).size());
        assertEquals("Main.Website{title='Denmark', url='w3.com', words=[Copenhagen, is, the, capital, of, Denmark]}, Main.Website{title='Brasil', url='w4.com', words=[Brasília, is, the, capital, of, Brasil]}, Main.Website{title='matches words of 3 websites', url='w6.com', words=[Copenhagen, animal, Brasil, match]}]", queryHelp.getMatchingWebsites(query).toString());

    }

    @Test
    // single OR in multiple word query
    void testSingleORQuery2_MatchingWebsites() {
        String query = "Copenhagen animal OR word";
        assertEquals(2, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[Main.Website{title=' ', url='w8.com', words=[ , word, other word]}, Main.Website{title='matches words of 3 websites', url='w6.com', words=[Copenhagen, animal, Brasil, match]}]", queryHelp.getMatchingWebsites(query).toString());

    }

    @Test
    // multiple OR in multiple word query
    // two OR finding the same website
    void testMultipleORQuery_MatchingWebsites() {
        String query = "Copenhagen match OR animal match OR Africa";
        assertEquals(2, queryHelp.getMatchingWebsites(query).size());
        assertEquals("[Main.Website{title='matches words of 3 websites', url='w6.com', words=[Copenhagen, animal, Brasil, match]}, Main.Website{title='Denmark and Uruguay', url='w2.com', words=[Africa, Uruguay, Denmark]}]", queryHelp.getMatchingWebsites(query).toString());


    }

    @Test
    void testNotIndexedWord_MatchingWebsites() {
        String query = "Hello";
        assertEquals(0, queryHelp.getMatchingWebsites(query).size());

    }

    @Test
    void testSpecialCharacter_MatchingWebsites() {
        String query = "'match' OR \"Zoo\""; // to improve: handle quotation marks
        assertEquals(2, queryHelp.getMatchingWebsites(query).size());

    }


    // what happens if I take or in query


    // problem: if we look for query denmark - we get one website twice - the one that is added twice because of add all
    // why do we have th final score map as a field?
    // might check for multiple

    /**
     * testing: evaluateFullQuery () of class QueryHelper
     */

    @Test
    void testSingleWordQueryContainingOR() {
        // website(s) must contain or denmark or animal
        String[] queries = {"Denmark", "animal"};

        assertEquals(4, queryHelp.evaluateFullQuery(queries).size());
        assertEquals("{Main.Website{title='Denmark and Uruguay', url='w2.com', words=[Africa, the, Uruguay, Denmark]}=1.5849625007211563, Main.Website{title='Elephant', url='w5.com', words=[memory, animal, Zoo]}=1.5849625007211563, Main.Website{title='matches words of 3 websites', url='w6.com', words=[Copenhagen, animal, Brasil, match]}=1.5849625007211563, Main.Website{title='Denmark', url='w3.com', words=[Copenhagen, is, the, capital, of, Denmark]}=1.5849625007211563}", queryHelp.evaluateFullQuery(queries).toString());
    }


    @Test
    void testSingleWordQueryNotContainingOR() {

        String[] queries3 = {"Denmark"};
        assertEquals(2, queryHelp.evaluateFullQuery(queries3).size());

    }

    @Test
    void testSpacesinQuery() {
        String [] query = {"Denmark       Copenhagen", "  Zoo"};
        assertEquals(2, queryHelp.evaluateFullQuery(query).size());

    }

    @Test
    void testSpecialCharacter() {
        String [] query = {"'match'", "Zoo"};
        assertEquals(2, queryHelp.evaluateFullQuery(query).size());

    }

    @Test
        // website(s) must contain or (Denmark and Copenhagen) or animal
    void testMultipleWordQueryContainingOR() {
        String[] queries = {"Denmark Copenhagen", "animal"};

        assertEquals(3, queryHelp.evaluateFullQuery(queries).size());
        //    assertEquals("{Main.Website{title='Denmark', url='w3.com', words=[Copenhagen, is, the, capital, of, Denmark]}=1.3219280948873624, Main.Website{title='Elephant', url='w5.com', words=[memory, animal, Zoo]}=2.321928094887362, Main.Website{title='Denmark and Uruguay', url='w2.com', words=[Africa, the, Uruguay, Denmark]}=1.3219280948873624}", queryHelp.evaluateFullQuery(queries));
    }


    @Test
        // website(s) must contain both words
    void testMultiWordQueryNotContainingOR() {
        String[] queries1 = {"Denmark Uruguay"};
        assertEquals(1, queryHelp.evaluateFullQuery(queries1).size());

    }


    @Test
    void testSpecialCharacter1() {

        String[] queries4 = {"#metoo OR #denmark OR # denmark"};
        assertEquals(0, queryHelp.evaluateFullQuery(queries4).size());

    }


    @Test
    void testEmptyString1() {

        String[] queries4 = {""};
        assertEquals(0, queryHelp.evaluateFullQuery(queries4).size());
    }

    /**
     * testing: evaluateSubQuery () of class QueryHelper
     */

    @Test
    void testSingleWord() {

        String[] queries1 = {"Denmark"};
        assertEquals(2, queryHelp.evaluateSubQuery(queries1).size());
    }

    @Test
        // testing multiple words - 2 words
    void testMultipleWords2() {

        String[] queries0 = {"Denmark", "Uruguay"};
        assertEquals(1, queryHelp.evaluateSubQuery(queries0).size());
    }


    @Test
        // testing multiple words - 3 words
        // no website with all 3 words in index
    void testMultipleWords3() {

        String[] queries2 = {"Denmark", "Uruguay", "Elephant"}; // try with three words that match
        assertEquals(0, queryHelp.evaluateSubQuery(queries2).size());
    }

    @Test
        // testing multiple words - 3 words
        //    website with all three words exists
    void testMultipleWords3e() {

        String[] queries3 = {"Copenhagen", "animal", "Brasil"}; // try with three words that match
        assertEquals(1, queryHelp.evaluateSubQuery(queries3).size());
    }

    @Test
    void testNotIndexedWords() {

        String[] queries4 = {"Hello"};
        assertEquals(0, queryHelp.evaluateSubQuery(queries4).size());
    }

    @Test
    void testEmptyString2() {
        String[] queries3 = {""};
        assertEquals(0, queryHelp.evaluateSubQuery(queries3).size());
    }


    //       assertEquals("{Main.Website{title='Denmark', url='w3.com', words=[Copenhagen, is, the, capital, of, Denmark]}=1.0, Main.Website{title='Denmark and Uruguay', url='w2.com', words=[Africa, the, Uruguay, Denmark]}=1.0}", queryHelp.evaluateSubQuery(queries1));
    // assertEquals("{Main.Website{title='Denmark and Uruguay', url='w2.com', words=[Africa, the, Uruguay, Denmark]}=1.0}", queryHelp.evaluateSubQuery(queries0));


    /**
     * Corner Cases:
     * UTF-8  - findet er dann auch die SOnderzeichen
     * ASCII not
     *
     */
}







