package Index;

import java.util.TreeMap;
/**
* @description A class to implement InvertedIndex. This class instantiates the map field
* in InvertedIndex to be a TreeMap.
 * @author Sarah Amick
 */
public class InvertedIndexTreeMap extends InvertedIndex {
    public InvertedIndexTreeMap(){
        super.map = new TreeMap<>();
    }
}
