package ScoringTests;

import Scoring.Score;
import Scoring.TFScore;
import ScoringTests.ScoringTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TFScoreTest extends ScoringTest {

    @Test
    public void test01() {
        Score score = new TFScore();
        String word = "word1";

        assertEquals(3.0, score.getScore(word, sites.get(0), indx));
        assertEquals(1.0, score.getScore(word, sites.get(1), indx));
        assertEquals(1.0, score.getScore(word, sites.get(2), indx));
        assertEquals(1.0, score.getScore(word, sites.get(3), indx));
    }

    @Test
    public void test02() {
        Score score = new TFScore();
        String word = "word2";

        assertEquals(0.0, score.getScore(word, sites.get(0), indx));
        assertEquals(2.0, score.getScore(word, sites.get(1), indx));
        assertEquals(0.0, score.getScore(word, sites.get(2), indx));
        assertEquals(1.0, score.getScore(word, sites.get(3), indx));
    }

    @Test
    public void test03() {
        Score score = new TFScore();
        String word = "word3";

        assertEquals(0.0, score.getScore(word, sites.get(0), indx));
        assertEquals(1.0, score.getScore(word, sites.get(1), indx));
        assertEquals(1.0, score.getScore(word, sites.get(2), indx));
        assertEquals(2.0, score.getScore(word, sites.get(3), indx));
    }

    @Test
    public void test04() {
        Score score = new TFScore();
        String word = "word4";

        assertEquals(0.0, score.getScore(word, sites.get(0), indx));
        assertEquals(0.0, score.getScore(word, sites.get(1), indx));
        assertEquals(1.0, score.getScore(word, sites.get(2), indx));
        assertEquals(0.0, score.getScore(word, sites.get(3), indx));
    }

    @Test
    public void test05() {
        Score score = new TFScore();
        String word = "word5";

        assertEquals(0.0, score.getScore(word, sites.get(0), indx));
        assertEquals(0.0, score.getScore(word, sites.get(1), indx));
        assertEquals(0.0, score.getScore(word, sites.get(2), indx));
        assertEquals(0.0, score.getScore(word, sites.get(3), indx));
    }

}
