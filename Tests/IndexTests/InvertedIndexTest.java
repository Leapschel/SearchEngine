package IndexTests;

import Main.*;
import Index.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description A class to test the InvertedIndex implementation
 * @author Leah Peschel
 */
abstract class InvertedIndexTest {

       Index invertedIndex;

       public InvertedIndexTest(Index index){
           this.invertedIndex = index;
       }

        @BeforeEach
        void setUp(){
            List<Website> sites = new ArrayList<>();
            sites.add(new Website("e1.com", "titlé1?#", Arrays.asList("word1", "word2")));
            sites.add(new Website("e2.com", "title2", Arrays.asList("word2", "word3")));
            sites.add(new Website("e2.com", "title2", Arrays.asList("word2", "word3")));
            sites.add(new Website("eäöü3.com", "titleäöü3", new ArrayList<>()));
            sites.add(new Website("e4.com", " ", Arrays.asList("word4", "word5")));
            sites.add(new Website(null, "title5", Arrays.asList("word6", "word5")));

            invertedIndex.build(sites);

        }

        @AfterEach
        void tearDown(){
            invertedIndex = null;
        }


        @Test
        void testLookup() {
            //test whether the length of the returned list is the right number of words
            assertEquals(1, invertedIndex.lookup("word1").size());
            assertEquals(2, invertedIndex.lookup("word2").size());
            //tests whether the lookup methods matches the right websites with query word
            assertEquals("[Main.Website{title=' ', url='e4.com', words=[word4, word5]}]",
                    invertedIndex.lookup("word4").toString());
            //test whether the lookup method returns an empty list for a non-existent query word
            assertEquals("[]", invertedIndex.lookup("gyjk").toString());
            assertEquals("[]", invertedIndex.lookup("word1, word9").toString());
            assertEquals(0, invertedIndex.lookup("word9").size());
            assertEquals(0, invertedIndex.lookup("WoRd1").size());
        }

    }

