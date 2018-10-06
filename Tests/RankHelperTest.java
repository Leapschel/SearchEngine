import Index.*;
import Main.*;
import Scoring.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
@author: Sarah Amick
A class to test the functionality of the RankHelper class
 */

public class RankHelperTest {

    Index indx;
    Score score;
    RankHelper rankHelper;
    List<Website> sites;

    @BeforeEach
    void setUp() {
        indx = new InvertedIndexHashMap();
        score = new BM25Score();

        this.sites = new ArrayList<>();
        sites.add(new Website("website1", "title1", Arrays.asList("word1", "word2", "word1", "word1")));
        sites.add(new Website("website2", "title2", Arrays.asList("word3", "word3", "word5", "word1", "word7")));
        sites.add(new Website("website3", "title3", Arrays.asList("word4", "word5", "word8", "word9")));

        indx.build(sites);
        rankHelper = new RankHelper(indx);
    }

    @AfterEach
    void tearDown() {
        indx = null;
        score = null;
        sites.clear();
    }

    @Test
    //test the assignScore method of RankHelper correctly ranks simple searches
    //with a two word Array over a list of 9 websites
    //using BM25, TF, and TFIDF scoring methods
    public void test01() {
        String[] word = new String[] {"word1"};
        Map<Website, Double> results = rankHelper.assignScore(sites, word);
        double scoreForWebsite1 = results.get(sites.get(0));
        double scoreForWebsite2 = results.get(sites.get(1));
        double scoreForWebsite3 = results.get(sites.get(2));
        assertTrue(scoreForWebsite1 > scoreForWebsite2);
        assertTrue(scoreForWebsite1 > scoreForWebsite3);
        assertTrue(scoreForWebsite2 > scoreForWebsite3);

    }
    //Tests that the assignScore method of RankHelper correctly adds the cumulative scores for a website
    //over two query words
    @Test
    public void test02(){
        String[] queryWords = new String[] {"word1", "word2"};

        Map<Website, Double> results = rankHelper.assignScore(sites, queryWords);
        double scoreForWebsite1 = results.get(sites.get(0));
        assertEquals(2.6834207656301383, scoreForWebsite1);
    }

    @Test
    //tests that the constructFinalMap method of RankHelper puts a (Website, rank) pair
    //into the final map if the map is empty
    public void test03() {
        List<Map<Website,Double>> listOfSubQueryMaps = new ArrayList<>();
        String[] queryWord = new String[]{"word1"};
        listOfSubQueryMaps.add(rankHelper.assignScore(sites, queryWord));
        Map<Website,Double> finalScoreMap = rankHelper.constructFinalMap(listOfSubQueryMaps);
        assertEquals(3, finalScoreMap.size());
    }

    @Test
    //tests that the constructFinalMap method of RankHelper puts a (Website, rank) pair
    //into the final map if the map is not empty, but does not yet contain the pair
    public void test04(){
        List<Map<Website, Double>> listOfSubQueryMaps = new ArrayList<>();

        //add the first map to the list:
        String[] queryWord1 = new String[]{"word1"};
        listOfSubQueryMaps.add(rankHelper.assignScore(sites, queryWord1));

        //two of the three websites should have been added to the finalMap:
        Map<Website,Double> finalScoreMap1 = rankHelper.constructFinalMap(listOfSubQueryMaps);
        assertTrue(finalScoreMap1.containsKey(sites.get(0)));
        assertTrue(finalScoreMap1.containsKey(sites.get(1)));

        //add the second map to the list:
        String[] queryWord2 = new String[]{"word9"};
        listOfSubQueryMaps.add(rankHelper.assignScore(sites, queryWord2));

        //the third website should now be added to the finalMap:
        Map<Website,Double> finalScoreMap2 = rankHelper.constructFinalMap(listOfSubQueryMaps);
        assertTrue(finalScoreMap2.containsKey(sites.get(2)));

    }

    @Test
    //test the constructFinalMap method of RankHelper when it is given two subquery maps (i.e. a query divided by OR)
    //and make sure that if a website is include in both subquery maps, that the pair with the higher value is kept
    //in the finalMap
    public void test05() {
        List<Website> sites1 = new ArrayList<>();
        sites1.add(sites.get(0));
        sites1.add(sites.get(1));

        List<Map<Website,Double>> listOfSubQueryMaps = new ArrayList<>();

        String[] queryWord1 = new String[]{"word1"};
        listOfSubQueryMaps.add(rankHelper.assignScore(sites1, queryWord1));

        //the first map should contain the original score for website2:
        Map<Website,Double> finalScoreMap1 = rankHelper.constructFinalMap(listOfSubQueryMaps);

        double firstScoreForWebsite1 = finalScoreMap1.get(sites.get(1));

        List<Website> sites2 = new ArrayList<>();
        sites2.add(sites.get(1));
        sites2.add(sites.get(2));
        String[] queryWord2 = new String[]{"word3"};
        listOfSubQueryMaps.add(rankHelper.assignScore(sites2, queryWord2));

        //the second map should contain the higher score for website2:
        Map<Website,Double> finalScoreMap2 = rankHelper.constructFinalMap(listOfSubQueryMaps);
        double secondScoreForWebsite1 = finalScoreMap2.get(sites1.get(1));

        assertTrue(secondScoreForWebsite1 > firstScoreForWebsite1);
    }

}
