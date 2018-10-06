package ScoringTests;

import Index.InvertedIndexHashMap;
import Main.Website;
import Scoring.BM25Score;
import Scoring.IDFScore;
import Scoring.TFScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BM25ScoreTest extends ScoringTest {

    protected double K = 1.75;
    protected double B= 0.75;
    double avdl;

    @BeforeEach
    public void setUp(){
        super.setUp();
        double numAllSites=0;
        for(Website w : sites){
            numAllSites += w.getWords().size();
        }
        this.avdl = numAllSites/ sites.size();
        }



    @Test
    public void test01() {
        String word = "word1";
        Website w1= sites.get(0);

        double idf = new IDFScore().getScore(word, w1, indx);
        double tf = new TFScore().getScore(word, w1, indx);
        double tfNormalized = (tf * ((K + 1) / (K * (1 - B + B * 3 / avdl) + tf)));

        assertEquals(tfNormalized * idf, new BM25Score().getScore(word, w1, indx));
    }

    @Test
    public void test02() {
        String word = "word1";
        Website w= sites.get(2);

        double idf = new IDFScore().getScore(word, w, indx);
        double tf = new TFScore().getScore(word, w, indx);
        double tfNormalized = (tf * ((K + 1) / (K * (1 - B + B * 3 / avdl) + tf)));

        assertEquals(tfNormalized * idf, new BM25Score().getScore(word, w, indx));
    }

    @Test
    public void test03() {
        String word = "word3";
        Website w= sites.get(1);

        double idf = new IDFScore().getScore(word, w, indx);
        double tf = new TFScore().getScore(word, w, indx);
        double tfNormalized = (tf * ((K + 1) / (K * (1 - B + B * 4 / avdl) + tf)));

        assertEquals(tfNormalized * idf, new BM25Score().getScore(word, w, indx));
    }

    @Test
    public void test04() {
        String word = "word3";
        Website w= sites.get(2);

        double idf = new IDFScore().getScore(word, w, indx);
        double tf = new TFScore().getScore(word, w, indx);
        double tfNormalized = (tf * ((K + 1) / (K * (1 - B + B * 3 / avdl) + tf)));

        assertEquals(tfNormalized * idf, new BM25Score().getScore(word, w, indx));
    }

    @Test
    public void test05() {
        String word = "word3";
        Website w= sites.get(3);

        double idf = new IDFScore().getScore(word, w, indx);
        double tf = new TFScore().getScore(word, w, indx);
        double tfNormalized = (tf * ((K + 1) / (K * (1 - B + B * 4 / avdl) + tf)));

        assertEquals(tfNormalized * idf, new BM25Score().getScore(word, w, indx));
    }

    @Test
    public void test06() {
        String word = "word5";
        Website w= sites.get(3);

        double idf = new IDFScore().getScore(word, w, indx);
        double tf = new TFScore().getScore(word, w, indx);
        double tfNormalized = (tf * ((K + 1) / (K * (1 - B + B * 4 / avdl) + tf)));

        assertEquals(tfNormalized * idf, new BM25Score().getScore(word, w, indx));
    }

}
