package Index;

import java.util.HashMap;
/**
* @description A class to implement InvertedIndex. This class instantiates the map field
* in InvertedIndex to be a HashMap.
 * @author Sarah Amick
 */
public class InvertedIndexHashMap extends InvertedIndex {
    public InvertedIndexHashMap(){
        super.map = new HashMap<>();
    }
}
