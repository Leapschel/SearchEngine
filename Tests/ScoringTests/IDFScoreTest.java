package ScoringTests;

import Scoring.IDFScore;
import Scoring.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IDFScoreTest extends ScoringTest {
    double allSites;

    @BeforeEach
    public void setUp(){
        super.setUp();
       allSites = sites.size();
    }

    @Test
    public void test01() {
        Score score = new IDFScore();
        String word = "word1";

        assertEquals((Math.log(allSites / 4)) / (Math.log(2)), score.getScore(word, sites.get(0), indx));
        assertEquals((Math.log(allSites / 4)) / (Math.log(2)), score.getScore(word, sites.get(1), indx));
        assertEquals((Math.log(allSites / 4)) / (Math.log(2)), score.getScore(word, sites.get(2), indx));
        assertEquals((Math.log(allSites / 4)) / (Math.log(2)), score.getScore(word, sites.get(3), indx));
    }

    @Test
    public void test02() {
        Score score = new IDFScore();
        String word = "word2";

        assertEquals((Math.log(allSites / 2)) / (Math.log(2)), score.getScore(word, sites.get(0), indx));
        assertEquals((Math.log(allSites / 2)) / (Math.log(2)), score.getScore(word, sites.get(1), indx));
        assertEquals((Math.log(allSites / 2)) / (Math.log(2)), score.getScore(word, sites.get(2), indx));
        assertEquals((Math.log(allSites / 2)) / (Math.log(2)), score.getScore(word, sites.get(3), indx));
    }

    @Test
    public void test03() {
        Score score = new IDFScore();
        String word = "word3";

        assertEquals((Math.log(allSites / 3)) / (Math.log(2)), score.getScore(word, sites.get(0), indx));
        assertEquals((Math.log(allSites / 3)) / (Math.log(2)), score.getScore(word, sites.get(1), indx));
        assertEquals((Math.log(allSites / 3)) / (Math.log(2)), score.getScore(word, sites.get(2), indx));
        assertEquals((Math.log(allSites / 3)) / (Math.log(2)), score.getScore(word, sites.get(3), indx));
    }

    @Test
    public void test04() {
        Score score = new IDFScore();
        String word = "word4";

        assertEquals((Math.log(allSites / 1)) / (Math.log(2)), score.getScore(word, sites.get(0), indx));
        assertEquals((Math.log(allSites / 1)) / (Math.log(2)), score.getScore(word, sites.get(1), indx));
        assertEquals((Math.log(allSites / 1)) / (Math.log(2)), score.getScore(word, sites.get(2), indx));
        assertEquals((Math.log(allSites / 1)) / (Math.log(2)), score.getScore(word, sites.get(3), indx));
    }

    @Test
    public void test05() {
        Score score = new IDFScore();
        String word = "word5";

        assertEquals((Math.log(allSites / 0)) / (Math.log(2)), score.getScore(word, sites.get(0), indx));
        assertEquals((Math.log(allSites / 0)) / (Math.log(2)), score.getScore(word, sites.get(1), indx));
        assertEquals((Math.log(allSites / 0)) / (Math.log(2)), score.getScore(word, sites.get(2), indx));
        assertEquals((Math.log(allSites / 0)) / (Math.log(2)), score.getScore(word, sites.get(3), indx));
    }

}