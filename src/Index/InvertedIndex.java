package Index;
import Main.*;
import FuzzySearch.*;

import java.util.*;
/**
 * @description A class to build an index from a list of websites, and then lookup in that index
 * a list of websites that are associated with a particular query word.
 * @author Sarah Amick
 * */
abstract public class InvertedIndex implements Index {

    protected Map<String, List<Website>> map;
    protected double numOfWebsites;
    protected List<Website> allWebsites;

    @Override
    /**
     * @param sites list of websites
     * @description The build method takes a list of websites and builds a map that maps
     * a string query word to a list of websites on which the query word appears.
     */
    public void build(List<Website> sites) {
        this.allWebsites = sites;
        numOfWebsites = sites.size();

        for (Website w : sites) {
            for (String word : w.getWords()) {
                if (map.containsKey(word)) {
                    List<Website> listOfAssociatedSites = map.get(word);
                    if (!listOfAssociatedSites.contains(w)) {
                        listOfAssociatedSites.add(w);
                    }
                } else {
                    List<Website> associatedSites = new ArrayList<>();
                    associatedSites.add(w);
                    map.put(word, associatedSites);
                }
            }
        }
    }

    @Override
    /**
     * @param query, a string query word
     * @return List<Website>, a list of websites the query word is found on
     * @description The lookup method takes a string query word and searches the map
     * for that key. It returns the associated list of websites. If the key
     * is not present in the map it returns an empty list. If the key ends in a *,
     * this method looks up all words in the index that begin with the query prefix, and return
     * a list of their websites. If query word begins with "site:", the index returns all websites
     * who's URL contains the following string after ":"
     */
    public List<Website> lookup(String query) {

        if(query.endsWith("*")){
            List<Website> associatedSites = new ArrayList<>();
            for(String word : map.keySet()){
                if(word.startsWith(query.substring(0, query.length()-1))){
                    associatedSites.addAll(map.get(word));
                }
            }
            return associatedSites;
        }
        if(query.matches("(?i)(site:).+")){
            String[] searchSite = query.split(":");
                List<Website> results = new ArrayList<>();
                for(Website site : allWebsites){
                    if(site.getUrl().toLowerCase().contains(searchSite[1].toLowerCase())){
                        results.add(site);
                    }
                }
                return results;
            }
        if (map.get(query) == null) {
            FuzzyHelper fh = new FuzzyHelper(this);
            return fh.fuzzyLookup(query);
        }
        else return map.get(query);
    }

    public String toString(){
        return this.getClass().toString() + " " + map.toString();
    }

    public double getNumOfWebsites(){
        return numOfWebsites;
    }

    @Override
    public List<Website> getAllWebsites() {
        return allWebsites;
    }

    public Map<String,List<Website>> getMap() {
        return map;
    }
}
