package IndexTests;
import Index.*;
import Main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SimpleIndexTest {
    SimpleIndex simpleIndex;

    @BeforeEach
    void setUp(){
        List<Website> sites = new ArrayList<Website>();
        sites.add(new Website("example1.com", "example1", Arrays.asList("word1", "word2")));
        sites.add(new Website("example2.com", "example2", Arrays.asList("word2", "word3")));
        simpleIndex = new SimpleIndex();
        simpleIndex.build(sites);
    }

    @AfterEach
    void tearDown(){
        simpleIndex = null;
    }

    @Test
    void build() {
        //to check whether the website is built the way it should, make a toString() method in the simpleIndex
        //class, capture the string that it creates with the above example websites, and set it to the expected
        assertEquals("class Index.SimpleIndex [Main.Website{title='example1', url='example1.com', words=[word1, word2]}, Main.Website{title='example2', url='example2.com', words=[word2, word3]}]", simpleIndex.toString());

    }

    @Test
    void lookup() {
        //just test whether the length of the returned list is the right number of words
        assertEquals(1, simpleIndex.lookup("word1").size());
        assertEquals(2, simpleIndex.lookup("word2").size());
        assertEquals(0, simpleIndex.lookup("word4").size());

    }

}