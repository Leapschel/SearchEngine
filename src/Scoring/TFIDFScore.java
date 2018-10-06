package Scoring;

import Index.*;
import Main.*;

/**
 * The TF-IDF Score (term frequency-inverse document frequency score)
 * is the mathematical product of TF and IDF. A a high score will be achieved by a high TF in the given document
 * and a low IDF (occurrences of the term in the whole set of documents)
 * * @Jana Schmurr
 */

public class TFIDFScore implements Score {
    public double getScore(String word, Website site, Index index) {

        double tfScore = new TFScore().getScore(word, site, index);
        double idfScore = new IDFScore().getScore(word, site, index);
        double tfidfScore = tfScore * idfScore;
        return tfidfScore;
    }
}
