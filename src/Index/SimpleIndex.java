package Index;
import Main.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @description A class to build an index from a list of websites, and then lookup in that index
 * a list of websites that are associated with a particular query word.
 * */
public class SimpleIndex  implements Index {

    private List<Website> sites = null;
    protected double numOfWebsites;
    protected List<Website> allWebsites;

    /**
    * @param sites (list of websites)
    * @description The build method takes a list of websites and assigns it
    * to a field to be used later to look up query words.
     */
    @Override
    public void build(List<Website> sites) {
        this.sites = sites;
        numOfWebsites = sites.size();
    }

    /**
    * @param query query word
    * @return List<Website>
    * @description The lookup method takes a string query word and loops through
    * all the websites in the index. If a website contains the query
    * word, that website is added to list of websites, which is
    * eventually returned. If the word is not present on any websites
    * it returns an empty list.
     */
    @Override
    public List<Website> lookup(String query) {

        List<Website> result = new ArrayList<>();
        for (Website w : sites) {
            if (w.containsWord(query)) {
                result.add(w);
            }
        }
        return result;
    }

    public String toString() {
        return this.getClass() + " " + sites.toString();
    }

    public double getNumOfWebsites(){
        return numOfWebsites;
    }

    public List<Website> getAllWebsites(){
        return allWebsites;
    }

}
