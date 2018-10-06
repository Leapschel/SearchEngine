package Main;
import Index.*;
import FuzzySearch.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description A class for handling complex queries
 * @author Anders Holst
 */
public class QueryHelper {

    private Index index;
    private RankHelper rankHelper;
    private FuzzyHelper fuzzyHelper;

    public QueryHelper(Index index) {
        this.index = index;
        rankHelper = new RankHelper(index);
        fuzzyHelper = new FuzzyHelper((InvertedIndex) index);
    }

    /**
     * @param query a String input from the Main.SearchEngine class
     * @return A list of websites matching the query
     * @description Splits the queries into an array of individual queries of n words and calls the evaluateFullQuery method.
     */

    public List<Website> getMatchingWebsites(String query) {

        String[] queries = query.split("\\s+OR\\s+");
        Map<Website, Double> finalScoreMap = evaluateFullQuery(queries);

        // Code from Martin Aumueller, lec6
        // Convert Map < Main.Website , Double > to List < Main.Website > sorted by value
        return finalScoreMap.entrySet().stream()
                .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * @param queries An array of individual queries each of n words length.
     * @return A list of websites matching at least one of the individual queries.
     * @description Iterates over the array, splits each individual query into an array separate words and calls the
     * evaluateSubQuery method to generate a list of websites matching the query.
     * Then it checks whether the websites are already contained in the resulting list, if not, then it is added.
     */

    public Map<Website, Double> evaluateFullQuery(String[] queries) {
        List<Map<Website, Double>> listOfSubQueryMaps = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            String[] words = queries[i].split("\\s+");
            listOfSubQueryMaps.add(evaluateSubQuery(words));
            }
        return rankHelper.constructFinalMap(listOfSubQueryMaps);
    }

    /**
     * @param words An array of strings, each representing a word of a query.
     * @return  A list of websites matching all the words in the array.
     * @description Iterates over the array and uses the provided index to look up whether any websites matches the word.
     * If no websites matches one of the words, an empty ArrayList is returned, otherwise a list is first generated,
     * which holds all matching websites and subsequent lists are compared to this one and only repeating websites are kept.
     */
    public Map<Website, Double> evaluateSubQuery(String[] words) {
        List<Website> subResults = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase().trim();
            if (index.lookup(word).size() == 0) {
                return new HashMap<>();
            } else if (i == 0) {
                subResults.addAll(index.lookup(word));
            } else {
                List<Website> sitesFromNextWord = index.lookup(word);
                subResults.retainAll(sitesFromNextWord);
            }
        }
        if(!fuzzyHelper.getFuzzyWords().isEmpty()){
            ArrayList<String> wordsInclFuzzy = fuzzyHelper.getFuzzyWords();
            wordsInclFuzzy.addAll(Arrays.asList(words));
            words = wordsInclFuzzy.toArray(new String[wordsInclFuzzy.size()]);
            fuzzyHelper.clearFuzzyWords();
        }
        return rankHelper.assignScore(subResults, words);
    }
}