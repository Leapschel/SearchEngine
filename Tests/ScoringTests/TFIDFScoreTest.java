package ScoringTests;

import Scoring.IDFScore;
import Scoring.Score;
import Scoring.TFIDFScore;
import Scoring.TFScore;
import ScoringTests.ScoringTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TFIDFScoreTest extends ScoringTest {

    @Test
    public void test01() {
        Score scoreTFIDF = new TFIDFScore();
        double scoreTF = new TFScore().getScore("word1", sites.get(0), indx);
        double scoreIDF = new IDFScore().getScore("word1", sites.get(0), indx);
        assertEquals(scoreTF * scoreIDF, scoreTFIDF.getScore("word1", sites.get(0), indx));
    }

    @Test
    public void test02() {
        Score scoreTFIDF = new TFIDFScore();
        double scoreTF = new TFScore().getScore("word1", sites.get(3), indx);
        double scoreIDF = new IDFScore().getScore("word1", sites.get(3), indx);
        assertEquals(scoreTF * scoreIDF, scoreTFIDF.getScore("word1", sites.get(3), indx));
    }

    @Test
    public void test03() {
        Score scoreTFIDF = new TFIDFScore();
        double scoreTF = new TFScore().getScore("word3", sites.get(0), indx);
        double scoreIDF = new IDFScore().getScore("word3", sites.get(0), indx);
        assertEquals(scoreTF * scoreIDF, scoreTFIDF.getScore("word3", sites.get(0), indx));
    }


    @Test
    public void test04() {
        Score scoreTFIDF = new TFIDFScore();
        double scoreTF = new TFScore().getScore("word3", sites.get(2), indx);
        double scoreIDF = new IDFScore().getScore("word3", sites.get(2), indx);
        assertEquals(scoreTF * scoreIDF, scoreTFIDF.getScore("word3", sites.get(2), indx));
    }

    @Test
    public void test05() {
        Score scoreTFIDF = new TFIDFScore();
        double scoreTF = new TFScore().getScore("word3", sites.get(3), indx);
        double scoreIDF = new IDFScore().getScore("word3", sites.get(3), indx);
        assertEquals(scoreTF * scoreIDF, scoreTFIDF.getScore("word3", sites.get(3), indx));
    }


    @Test
    public void test06() {
        Score scoreTFIDF = new TFIDFScore();
        double scoreTF = new TFScore().getScore("word5", sites.get(3), indx);
        double scoreIDF = new IDFScore().getScore("word5", sites.get(3), indx);
        assertEquals(scoreTF * scoreIDF, scoreTFIDF.getScore("word5", sites.get(3), indx));
    }
}