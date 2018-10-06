package Scoring;
import Main.*;
import Index.*;

import java.util.List;

/**
 * The BM25 Score (Best Match Score) weights the term frequency tf by the document length (df) in relation to
 * the average document length (AVDL) for all websites in the database.
 * A higher document length will result in lowering the BM25 score for the given website, and vice versa.
 * * @Jana Schmurr
 */

public class BM25Score implements Score {

    // free parameter, usually chosen as k1=1.75
    private static final double K = 1.75;
    // free parameter, usually chosen as b=0.75
    private static final double B = 0.75;


    /**
     * @param word One word from the query
     * @param site Website which contains the word.
     * @param index The currently used index.
     * @return BM25 Score ( bm25 = tf*  * idf )
     */

    public double getScore(String word, Website site, Index index) {
        double idf = new IDFScore().getScore(word, site, index);
        double tf = new TFScore().getScore(word, site, index);

        // determine Document Length of one Website
        double  dl = site.getWords().size();
        // determine Average Document Length of all Websites
        double avdl = computeAvdl(index);
        double tfNormalized = (tf * ((K + 1) / (K * (1 - B + B * dl / avdl) + tf)));
        double BM25Score = tfNormalized * idf;
        return BM25Score;
    }


    private double computeAvdl(Index index) {
        List<Website> listOfAllWebsites = index.getAllWebsites();
        double numAllSites = listOfAllWebsites.size();
        double sumAllWords = 0;
        for (int i = 0; i < numAllSites; i++) {
            Website currentSite = listOfAllWebsites.get(i);
            double dl = currentSite.getWords().size();
            sumAllWords += dl;
        }
        return sumAllWords / numAllSites;
    }

}

