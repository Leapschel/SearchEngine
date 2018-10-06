package IndexTests;

import Index.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @description A class that extends InvertedIndexTest with a HashMap implementation
 * @author Sarah Amick
 */
public class InvertedHashMapTest extends InvertedIndexTest {

    public InvertedHashMapTest(){
        super(new InvertedIndexHashMap());
    }

    @Test
    void testBuild() {

        //tests whether the index is built correctly:
        assertEquals("class Index.InvertedIndexHashMap {word1=[Main.Website{title='titlé1?#', url='e1.com', words=[word1, word2]}], word3=[Main.Website{title='title2', url='e2.com', words=[word2, word3]}], word2=[Main.Website{title='titlé1?#', url='e1.com', words=[word1, word2]}, Main.Website{title='title2', url='e2.com', words=[word2, word3]}], word5=[Main.Website{title=' ', url='e4.com', words=[word4, word5]}, Main.Website{title='title5', url='null', words=[word6, word5]}], word4=[Main.Website{title=' ', url='e4.com', words=[word4, word5]}], word6=[Main.Website{title='title5', url='null', words=[word6, word5]}]}"
                , invertedIndex.toString());
    }
}
