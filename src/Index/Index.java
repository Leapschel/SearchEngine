package Index;
import Main.*;

import java.util.List;
import java.util.Map;

/**
 * @description A class to build an index from a list of websites, and then lookup in that index
 * a list of websites that are associated with a particular query word.
 * */
public interface Index {

    /**
     * @description Build method processes a list of websites and indexes them
     * @param sites List of websites that must be indexed.
     */
    void build(List<Website> sites);
    /**
     * @param query a String word
     * @return List<Website>, the list of websites that contains the query word
     * @description Given a query string, returns a list of all websites that contain
     * the query word.
     */
    List<Website> lookup(String query);

    double getNumOfWebsites();

    List<Website> getAllWebsites();
}
