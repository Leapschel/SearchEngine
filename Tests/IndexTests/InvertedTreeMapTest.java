package IndexTests;

import Index.InvertedIndexHashMap;
import Index.InvertedIndexTreeMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @description A class that extends InvertedIndexTest with a TreeMap implementation
 * @author Sarah Amick
 */
public class InvertedTreeMapTest extends InvertedIndexTest {
    public InvertedTreeMapTest(){
        super(new InvertedIndexTreeMap());
    }

    @Test
    void testBuild() {

        //tests whether the index is built correctly:
        assertEquals("class Index.InvertedIndexTreeMap {word1=[Main.Website{title='titlé1?#', url='e1.com', words=[word1, word2]}], word2=[Main.Website{title='titlé1?#', url='e1.com', words=[word1, word2]}, Main.Website{title='title2', url='e2.com', words=[word2, word3]}], word3=[Main.Website{title='title2', url='e2.com', words=[word2, word3]}], word4=[Main.Website{title=' ', url='e4.com', words=[word4, word5]}], word5=[Main.Website{title=' ', url='e4.com', words=[word4, word5]}, Main.Website{title='title5', url='null', words=[word6, word5]}], word6=[Main.Website{title='title5', url='null', words=[word6, word5]}]}"
                , invertedIndex.toString());
    }
}
