package ScoringTests;

import Index.*;
import Main.*;
import Scoring.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public abstract class ScoringTest {

    protected Index indx;
   protected List<Website> sites;

    @BeforeEach
    void setUp() {
        sites = new ArrayList<>();
        sites.add(new Website("w1.com", "title1", Arrays.asList("word1", "word1", "word1")));
        sites.add(new Website("w2.com", "title2", Arrays.asList("word1", "word2", "word2", "word3")));
        sites.add(new Website("w3.com", "title3", Arrays.asList("word1", "word3", "word4")));
        sites.add(new Website("w4.com", "title4", Arrays.asList("word1", "word2", "word3", "word3")));

        indx = new InvertedIndexHashMap();
        indx.build(sites);
    }

    @AfterEach
    void tearDown() {
        indx = null;
    }

}


