package Scoring;

import Index.Index;
import Main.Website;

/**
 * The BM25ExtScore Score extends the scoring from the content by its title,
 * under the precondition that the queryword appears at least once in the text body.
 * @Jana Schmurr
 */

public class BM25ExtScore implements Score{

    //only create bonus Score if word is not too common (i.e. 1= half of webpages)
    private static double IDF_THRESHOLD = 1.0;


    public double getScore(String word, Website site, Index index) {
        double titleBonus = 1;
        double BM25 = new BM25Score().getScore(word, site, index);
        double idf = new IDFScore().getScore(word, site, index);
        if (idf > IDF_THRESHOLD) {
            titleBonus = calcTitleBonus(word, site);
        }

        return BM25 * titleBonus;
    }

    private double calcTitleBonus(String word, Website site) {
        if (site.getTitle().contains(word)) {
            int length = site.getTitle().length();
            return 1 + (1/(length));
        } else {
            return 1;
        }
    }

}