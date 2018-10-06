package Main;
import Index.*;
import Main.*;
import Scoring.BM25Score;
import Scoring.Score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description A class for ranking websites for particular queries.
 * @author Anders Holst, Sarah Amick, Jana Schmurr, Leah Peschel
 */
public class RankHelper {

    private Index index;
    private Score score;
    private Map<Website, Double> finalScoreMap;

    public RankHelper(Index index) {
        this.index = index;
        this.score = new BM25Score();
        this.finalScoreMap = new HashMap<>();
    }

    /**
     * @param subResults List of Websites for a subquery (multiple words with " ")
     * @param words an array containing the words of the subquery
     * @return subQueryMap containing the score for each website in the list of
     * websites
     * @description The assignScore method takes each website in a list of sites and
     * maps it a cumulative score for that website for each query word (a website's
     * score is the sum of its score for each quer word)
     */
    public Map<Website, Double> assignScore(List<Website> subResults, String[] words) {
        Map<Website, Double> subQueryMap = new HashMap<>();
        for (Website w : subResults) {
            double subScore = 0;
            for (int i = 0; i < words.length; i++) {
                double indScore = score.getScore(words[i], w, index);
                subScore = subScore + indScore;
            }
            subQueryMap.put(w, subScore);
        }
        return subQueryMap;
    }

    /**
     *
     * @param listOfMaps the subQuery maps for each subQuery (divided by "OR")
     * @return Map<Website, Double>
     * @description The constructFinalMap takes all the maps for each subQuery search and compiles
     * them into one map. Websites from each map will be placed in the final map according to which
     * website has the highest associated score.
     */
    public Map<Website, Double> constructFinalMap(List<Map<Website, Double>> listOfMaps) {

        for (Map<Website, Double> m : listOfMaps) {
            if (finalScoreMap.isEmpty()) {
                finalScoreMap.putAll(m);
            } else {
                for (Website w : m.keySet()) {
                    if (finalScoreMap.containsKey(w)) {
                        double oldScore = finalScoreMap.get(w);
                        double newScore = m.get(w);
                        if (newScore > oldScore) {
                            finalScoreMap.put(w, newScore);
                        }
                    } else {
                        double score = m.get(w);
                        finalScoreMap.put(w, score);
                    }
                }
            }
        }
        return finalScoreMap;
    }
}






