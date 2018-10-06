package Scoring;
import Index.*;
import Main.*;

/**
 *  The term frequency score is equal to the the frequency of a term on the given website.
 *  * @Jana Schmurr
 */

public class TFScore implements Score {

    public double getScore(String queryWord, Website site, Index index) {

        double tf = 0;
        for (String word : site.getWords()){
            if (word.equals(queryWord)) tf++;
        }
        return tf;
    }
}
