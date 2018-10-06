package Scoring;
import Main.*;
import Index.*;

/**
 *  implements the term frequency-inverse document score:
 *  relates the number of documents containing the search term to the number of all documents
 *  very common words will receive a lower score compared to word with less occurrences
 *  * @Jana Schmurr
 */

public class IDFScore implements Score {

    public double getScore(String word, Website site, Index index) {
        double numOfAllSites = index.getNumOfWebsites();
        double docFrequency = index.lookup(word).size();

        if(docFrequency>0){
            double idf = (Math.log(numOfAllSites / docFrequency)) / (Math.log(2));
            return idf;
        }
        else{
            return 0.0;
        }
    }
}
