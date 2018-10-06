package Scoring;
import Index.*;
import Main.*;

/**
 * Assignment 5: Ranking algorithms
 * Calculates score by evaluating website for their relevance in regards to a specific query.
 ** @Jana Schmurr
 */
public interface Score {

    /**
     * @param word one word of the query.
     * @param site website which contains the word.
     * @param index The currently used index.
     */


    double getScore(String word, Website site, Index index);

}
