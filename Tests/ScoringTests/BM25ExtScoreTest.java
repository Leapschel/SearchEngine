package ScoringTests;

import Index.*;
import Main.*;
import Scoring.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BM25ExtScoreTest extends ScoringTest{

    @BeforeEach
    void setUp() {
        sites = new ArrayList<>();
        sites.add(new Website("w1.com", "word1", Arrays.asList("word1", "word2", "word2", "word3")));
        sites.add(new Website("w2.com", "word1", Arrays.asList("word1", "word2", "word2")));
        sites.add(new Website("w3.com", "word1 word2", Arrays.asList("word1", "word2", "word2")));
        sites.add(new Website("w4.com", "word1", Arrays.asList("word2", "word3", "word3")));

        indx = new InvertedIndexHashMap();
        indx.build(sites);
    }

    @AfterEach
    void tearDown() {
        indx = null;
    }


    @Test
    public void test01() {
        String word = "word1";

        double scoreBM25 = new BM25Score().getScore(word, sites.get(0), indx);
        double scoreBM25ext = new BM25ExtScore().getScore(word, sites.get(0), indx);
        double idf = new IDFScore().getScore(word, sites.get(1), indx);
        if (idf < 1) {
            assertEquals(scoreBM25, scoreBM25ext);
        } else {
            assertTrue(scoreBM25 < scoreBM25ext);
        }
    }


    @Test
    public void test02() {
        String word = "word3";

        for (int i = 0; i < sites.size(); i++) {
            double scoreBM25 = new BM25Score().getScore(word, sites.get(i), indx);
            double scoreBM25ext = new BM25ExtScore().getScore(word, sites.get(i), indx);
            double idf = new IDFScore().getScore(word, sites.get(i), indx);
            if (idf < 1) {
                assertEquals(scoreBM25, scoreBM25ext);
            }
        }
    }


    @Test
    public void test03() {
        String word = "word5";

        for (int i = 0; i < sites.size(); i++) {
            double scoreBM25 = new BM25Score().getScore(word, sites.get(i), indx);
            double scoreBM25ext = new BM25ExtScore().getScore(word, sites.get(i), indx);
            double idf = new IDFScore().getScore(word, sites.get(i), indx);
            if (idf < 1) {
                assertEquals(scoreBM25, scoreBM25ext);
            }
        }
    }
}












