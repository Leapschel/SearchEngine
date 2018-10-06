package FuzzySearch;

import Index.*;

import java.util.List;

import Main.Website;

import java.util.ArrayList;

/**
 * @ author Leah Peschel
 * A class for handling fuzzy query words
 */

public class FuzzyHelper {

    ArrayList<String> fuzzyWords;
    InvertedIndex index;
    EditDistance editDistance;

    public FuzzyHelper(InvertedIndex index) {
        fuzzyWords = new ArrayList<>();
        this.index = index;
        editDistance = new EditDistance();

    }

    /**
     * @param queryWord - String input from the QueryHelper class containing a query word that is not indexed
     * @return resultsFuzzySearch - List of Websites containing words similar to the fuzzy queryWord
     * @description - looks up the index for words with great string similarity to the fuzzy queryWord.
     * String similarity is computed by the edit distance method.
     * The  method additionally adds all words with satisfying edit distance to a list of words.
     */

    public List<Website> fuzzyLookup(String queryWord) {
        List<Website> resultsFuzzySearchWord = new ArrayList<>();

        char[] queryWordChar = queryWord.toCharArray();

        for (String w : index.getMap().keySet()) { // would like to do tailMap here - convert treemap to sorted map

            char[] indexedWord = w.toCharArray();

            int editDistanceValue = editDistance.getEditDistance(queryWordChar, indexedWord);

            if (editDistanceValue <= 1) {
                resultsFuzzySearchWord.addAll(index.lookup(w));
                fuzzyWords.add(w);
                }
            }
        return resultsFuzzySearchWord;
    }


    public ArrayList<String> getFuzzyWords() {

        return fuzzyWords;
    }
    public void clearFuzzyWords(){
        fuzzyWords.clear();
    }

}